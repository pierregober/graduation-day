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

        System.out.println("*****************************************************************************************");
        System.out.println("************************************ INSTRUCTIONS ***************************************");
        System.out.println("*                                                                                       *");
        System.out.println("*        The next four years of your life will be the most exciting times of your life. *      ******************");
        System.out.println("*        1. Your first task is to answer as many questions correctly as you can.        *      *    COMMANDS:   *");
        System.out.println("*        2. You need at least 2.0 GPA to pass each class and graduate.                  *      *    go east     *");
        System.out.println("*        3. Go around each classroom and answer the questions.                          *      *    go west     *");
        System.out.println("*        4. If you fail a class, you will retake it until you pass.                     *      *    go south    *");
        System.out.println("*        5. You may be rewarded with special items. Also, avoid the bathroom.           *      *    go north    *");
        System.out.println("*        6. At any point in the game, you can press 'S', 'H', or 'Q.' :                 *      *    run         *");
        System.out.println("*                'S' ---> will give the players status.                                 *      *    fight       *");
        System.out.println("*                'H' ---> will give helpful tips.                                       *      *    cheat       *");
        System.out.println("*                'Q' ---> Will force the game to quit.                                  *      ******************");
        System.out.println("*                                                                                       *");
        System.out.println("*****************************************************************************************\n");


        }

}