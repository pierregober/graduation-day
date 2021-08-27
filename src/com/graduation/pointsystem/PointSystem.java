package com.graduation.pointsystem;

import com.graduation.client.GameClient;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PointSystem {
    private static List<String> notSubject = new ArrayList<>(Arrays.asList("gym", "cafeteria", "hallway"));
    private static final int GRADE = 4;
    private static double player_total_grade = 0;
    private static int counter =0;

    private double getScore(int correct) {
        double current_class = 0;
        current_class += ((correct / (double) 5) * GRADE);
        return current_class;
    }

    public double getCumulativeScore(int eachScore, int numberOfSubjects){
        System.out.println("Counter = "+numberOfSubjects);
        player_total_grade+=(getScore(eachScore));
        return Double.parseDouble(new DecimalFormat("#.##").format(player_total_grade/(double)numberOfSubjects));
    }

    public static void teacherQuestions(String subject) {

        Questions questions = new Questions();
        PointSystem pointSystem=new PointSystem();
       // int counter = 0;
        int score = 0;
//        while(true) {
           counter++;

        if (!notSubject.contains(subject.toLowerCase())) {
            score = questions.generateQuestions(subject);
            while (pointSystem.getScore(score) < 2){
                System.out.println("Your score of "+pointSystem.getScore(score)+" is less than 2.0, you need to take "+subject+" again");
                score=questions.generateQuestions(subject);
            }

            System.out.println(pointSystem.getCumulativeScore(score,counter));
//        }


        }
        GameClient.continueJourney();
    }
}
