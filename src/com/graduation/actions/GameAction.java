package com.graduation.actions;

import java.util.Scanner;

public class GameAction {
    private static Scanner action = new Scanner(System.in);;

    public static void getAction() {
        System.out.println("Enter a move");
        String move = action.nextLine();
        String[] moveArray = move.toLowerCase().split(" ");
        if (moveArray[0].equals("go")) {
//            currentLocation = "Gym";
        }if(moveArray[0].equals("go")){
            System.out.println("you went " + moveArray[1] + "!" ); // pierre is still working on these featrures
            //change the location
        }else if(moveArray[0].equals("get")){
            System.out.println("you got  " + moveArray[1] + "!" );
        }else if(moveArray[0].equals("look")){
            System.out.println("you looked " + moveArray[1] + "!" );
        }else if(moveArray[0].equals("use")){
            System.out.println("you used " + moveArray[1] + "!" );
       }else{
            System.out.println("You entered a an invalid move. Type \"help\" for the instructions");
            getAction();
        }
    }
}