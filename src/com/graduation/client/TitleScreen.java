package com.graduation.client;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.PseudoText;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPairGenerator;
import java.util.Scanner;


class TitleScreen {
    //Fields
    private static String banner;
    Scanner titleObj = new Scanner(System.in);
    String keystroke = titleObj.nextLine().toLowerCase();
    String credits = "This game was produced by Hongyi Qu, Pierre Gober, Stephen Yeboah\n" +
            " Jauric Flowers.\n";

    String help = "You must answer a series of questions to get a passing grade.\n" +
            "You need 4 badges in order to graduate high school. Also,\n" +
            "watch out for the bully and collect hidden items.\n" +
            "Actions:\n" +
            "\n" +
            "GO [north, south, east, west, up, down]\n" +
            "\n" +
            "GET [item]\n" +
            "\n" +
            "INV/INVENTORY";

    public void inputOptions(String keystroke) {
        //We will use this later during the game as an option to bring
        System.out.println("Type 'Q' to quit the game." +
                "Type 'Z' to look at the credits. Type 'H' to look at the help tutorial");
        switch (keystroke) {
            case "q":
                System.exit(0);
                break;
            case "z":
                System.out.println(credits);
                break;
            case "h":
                System.out.println(help);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
//        System.out.println(keystroke);
    }


    //Displays title and instructions
    public static void displayInstructions()  {
//        System.out.println(TitleScreen.readTXT("Welcome_Grad_Day"));
//        System.out.println(TitleScreen.readTXT("Instructions"));

        //Use ascii-render to display welcome to graduation day!
        IRender render3 = new Render();
        IContextBuilder builder3 = render3.newBuilder();
        builder3.width(140).height(10);
        builder3.element(new PseudoText("WELCOME TO GRADUATION DAY!", false));
        ICanvas canvas3 = render3.render(builder3.build());
        String s3 = canvas3.getText();
        System.out.println(s3);
        System.out.println();
        System.out.println();

        System.out.println("*************************************************************");
        System.out.println("*********************** INSTRUCTIONS ************************");
        System.out.println("*************************************************************\n");
        System.out.println("        The next four years of your life will be the most exciting times of your life.");
        System.out.println("        Your first task is to answer as many questions correctly as you can.");
        System.out.println("        You need a 2.0 to graduate.");


        }


//    public static String readTXT(String name) {
//        try {
//            banner = Files.readString(Path.of("Banner/" + name + ".txt"));
//        } catch (IOException e) {
//
//        }
//        return banner;
//    }

}