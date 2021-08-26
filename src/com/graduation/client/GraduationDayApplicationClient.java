package com.graduation.client;

import com.graduation.utils.Prompter;

import java.util.Scanner;

class GraduationDayApplicationClient {
    public static void main(String[] args) {
        System.out.println("\nGRADUATION DAY");    //Please someone make this fancy later -- pierre
        System.out.println( "Team Members:\n" +
                " Hongyi Qu\n" +
                " Jauric Flowers\n" +
                " Pierre Gober\n" +
                " Stephen Yeboah\n\n");
//        MyClassName client = new MyClassName();
//        client.initialize();                      //Hongyi this will redirect to a method called initialize and then you instantiate a Player and add inputs in the Class
        Prompter prompter = new Prompter(new Scanner(System.in));
        GameClient game = new GameClient(prompter);
        game.initialize();
    }
}


