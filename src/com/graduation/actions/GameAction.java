package com.graduation.actions;

import com.graduation.client.GameClient;
import com.graduation.utils.Prompter;
import com.graduation.utils.readMap;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

public class GameAction {
    private static Scanner action = new Scanner(System.in);

    public static void getAction() {
        Prompter.clearScreen();
        System.out.println(GameClient.getPlayer());
        System.out.println(readMap.convertedMap());

        System.out.println("GO " + GameClient.getValidDirections());
        System.out.println("GET " + "[item]");
        System.out.println("USE " + "[item]");
        System.out.println("LOOK");
        System.out.println("H (for help)");

        String move = GameClient.getPrompter().prompt("Enter a move from above options: \n ");
        String[] moveArray = move.toLowerCase().split(" ");

//         validate userInput for valid command length
        while (moveArray.length != 2) {
            move = GameClient.getPrompter().prompt("Enter a move from above options: \n ");
            moveArray = move.toLowerCase().split(" ");
        }

        switch (moveArray[0]) {
            case "go":
                if (moveArray[1].equals("north") || moveArray[1].equals("south") || moveArray[1].equals("east") || moveArray[1].equals("west")) {
                    GameClient.nextLocation(moveArray[1]);
                } else {
                    System.out.println("\n\nAht aht.. you didn't enter a valid cardinal direction");
                    getAction();
                }
                break;
            case "get":
                //Calls the method to initiate the item sequence
                GameClient.getLevelDetails("item");
                break;
            default:
                System.out.println("You entered a an invalid move. Type \"H\" for the instructions");
                getAction();
                break;
        }

    }
}