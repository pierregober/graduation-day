package com.graduation.pointsystem;

import com.graduation.client.GameClient;
import com.graduation.elements.Bully;
import com.graduation.elements.Player;
import com.graduation.utils.ConsoleColor;
import com.graduation.utils.Grade;
import com.graduation.utils.Prompter;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PointSystem {
    public static List<String> getNotSubject() {
        return notSubject;
    }

    private static List<String> notSubject = new ArrayList<>(Arrays.asList("gym", "cafeteria", "hallway"));
    private static final int GRADE = 4;
    private static double player_total_grade = 0;
    private static final List<String> core = new ArrayList<>(
            Arrays.asList("maths", "computers", "geography", "history"));
    private static boolean isNewLevel = false;
    public static Player currentPlayer = null;

    private double getScore(int correct) {
        double current_class = 0;
        current_class += ((correct / (double) 5) * GRADE);
        return current_class;
    }

    public double getCumulativeScore(int eachScore, int numberOfSubjects) {
        player_total_grade += (getScore(eachScore));
        return Double.parseDouble(new DecimalFormat("#.##").format(player_total_grade / (double) numberOfSubjects));
    }


    public static void teacherQuestions(String subject, Grade level, Player player) throws Exception {

        currentPlayer = player;

        // check if a subject already passed and continue loading questions accordingly
        if (!player.getSubjectTaken().contains(subject)) {
            Question questions = new Question();
            if (player.getSubjectTaken().size() == 0) {
                isNewLevel = false;
            }
            PointSystem pointSystem = new PointSystem();
            // initialize classroom score and class fail counter before starting question
            int score = 0;
            int classFailCount = 0;
            if (!notSubject.contains(subject.toLowerCase())) {
                score = questions.generateQuestions(subject, level);
                if (score == -1) {
                    GameClient.continueJourney(isNewLevel);
                } else {
                    while (pointSystem.getScore(score) < 2) {
                        classFailCount++;
                        if (classFailCount == 3) {
                            System.out.println(ConsoleColor.RED_BOLD + "\n \n You have failed this class 3 times and hence " +
                                    "lost the game. You will need to re-apply for the school and re-start the game."
                                    + ConsoleColor.RESET);
                            Thread.sleep(5000);
                            System.exit(0);
                        }
                        System.out.println(ConsoleColor.RED + "\n\n    Your GPA of " + pointSystem.getScore(score)
                                + " is less than 2.0, you need to take " + subject + " again" + ConsoleColor.RESET);
                        System.out.println();
                        score = questions.generateQuestions(subject, level);
                    }
                    // add the passed subject to the list of taken subject
                    player.getSubjectTaken().add(subject);
                    // set the player's GPA
                    player.setCredit(pointSystem.getCumulativeScore(score, player.getSubjectTaken().size()));

                    System.out.println(player.getCredit());
                    // determine if the player has meet the criteria to change its level
                    // from freshman->sophomore->junior->senior
                    // based on a gpa greater than or equal to 2.0 and having taken all the core
                    // subjects i.e. maths,computers,history and geography
                    // reset the taken subject list
                    changePlayerGrade(player);
                    System.out.println("Grade now: " + Player.getGrade());

                }
            }

        } else {
            System.out.println("You have already passed " + subject);
            // GameClient.continueJourney(isNewLevel);
        }

        GameClient.continueJourney(isNewLevel);
    }

    public static void changePlayerGrade(Player player) {
        // Step 1: Determine if we can go to the next grade level
        if (player.getSubjectTaken().containsAll(core) && player.getCredit() >= 2.0) {
            // display a congratulation message on moving to the next grade
            System.out.println("congratulations, you've passed " + player.getGrade());
            isNewLevel = true;
            switch (player.getGrade()) {
                case FRESHMAN:
                    player.setGrade(Grade.SOPHOMORE);
                    break;
                case SOPHOMORE:
                    player.setGrade(Grade.JUNIOR);
                    break;
                case JUNIOR:
                    player.setGrade(Grade.SENIOR);
            }
            // Step 2: Clear the subjects that we passed from the player
            player.getSubjectTaken().clear();
            // Step 3: Get the first location of the next level
            player.setLocation(GameClient.getFirstLocation());
            // reset the GPA for the new level to zero
            player_total_grade = 0;
            // Step 4: Toggle the bully
            Bully.setPresence(true);
            Bully.setHealth(100);
        }
    }
}
