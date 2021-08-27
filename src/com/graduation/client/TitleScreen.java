package com.graduation.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


class TitleScreen {
    //Fields
    Scanner titleObj = new Scanner(System.in);

    private String banner;
    String credits = "This game was produced by Hongyi Qu, Pierre Gober, Stephen Yeboah\n" +
            " Jauric Flowers.\n";

    String help = "You must answer a series of questions to collect badges.\n" +
            "You need 4 badges in order to graduate high school. Also,\n" +
            "watch out for the bully and collect hidden items.\n" +
            "Actions:\n" +
            "\n" +
            "GO [north, south, east, west, up, down]\n" +
            "\n" +
            "GET [item]\n" +
            "\n" +
            "USE [item]\n" +
            "\n" +
            "LOOK\n" +
            "\n" +
            "INV/INVENTORY"
            ;

    public void title() {




//      System.out.println("\nGraduation-Day!");
        System.out.println("Type 'S' to start.");
        System.out.println(readTXT());
        String startRequest = titleObj.nextLine().toLowerCase();
        if (startRequest == "s") {
            //code to go to GameClient and initialize
        }

        System.out.println("Type 'Q' to quit the game.");
        //bring down
        String quitRequest = titleObj.nextLine().toLowerCase();
        if (quitRequest == "q") {
            //Exit JVM
            System.exit(0);

        }
        System.out.println("Type 'C' to look at the credits.");
        String creditsRequest = titleObj.nextLine().toLowerCase();
        if (creditsRequest == "c") {
            System.out.println(credits);
        }
        System.out.println("Type 'H' to look at the help tutorial.");
        String helpRequest = titleObj.nextLine().toLowerCase();
        if (helpRequest == "h") {
            System.out.println(help);

        }
    }

    public String readTXT() {
        try {
            banner = Files.readString(Path.of("Banner/Welcome_Grad_Day .txt"));
        } catch (IOException e) {

        }
        return banner;
    }

}