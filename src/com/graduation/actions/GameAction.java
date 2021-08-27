package com.graduation.actions;

import com.graduation.client.GameClient;

import java.util.Scanner;

public class GameAction {
    private static Scanner action = new Scanner(System.in);;

    public static void getAction() {
        System.out.println("Enter a move: ");
        String move = action.nextLine();
        String[] moveArray = move.toLowerCase().split(" ");

        switch (moveArray[0]) {
            case "go":
                if(moveArray[1].equals("north") || moveArray[1].equals("south") || moveArray[1].equals("east") || moveArray[1].equals("west")){
                    System.out.println("you went " + moveArray[1] + "!");
                    GameClient.nextLocation(moveArray[1]);
                }else{
                    System.out.println("\n\nAht aht.. you didn't enter a valid cardinal direction");
                    getAction();
                }
                break;
            case "get":
                System.out.println("you got  " + moveArray[1] + "!");

                break;
            case "look":
                System.out.println("you looked " + moveArray[1] + "!");
                break;
            case "use":
                System.out.println("you used " + moveArray[1] + "!");
                break;
            default:
                System.out.println("You entered a an invalid move. Type \"help\" for the instructions");
                getAction();
                break;
        }

    }
}