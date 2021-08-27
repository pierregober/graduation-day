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

    private double getScore(int correct) {
        player_total_grade += ((correct / (double) 5) * GRADE);
        return player_total_grade;
    }

    public static void teacherQuestions(String subject) {

        Questions questions = new Questions();
        int counter = 0;
        int score = 0;
//        while(true) {
           counter++;

        if (!notSubject.contains(subject.toLowerCase())) {
            score = questions.generateQuestions(subject);

            System.out.println(new DecimalFormat("#.##").format(new PointSystem().getScore(score) / counter));
//        }

           GameClient.continueJourney();
        }
    }
}
