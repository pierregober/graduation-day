package com.graduation.actions;

import java.util.Scanner;

public class GameAction {
    Scanner action = new Scanner(System.in);

    public void getAction() {
        System.out.println("Enter a move");
        String move = this.action.nextLine();
        String[] moveArray = move.toLowerCase().split(" ");
        if (moveArray[0].equals("go")) {
//            currentLocation = "Gym";
        }if(moveArray[0].equals("go")){
            //steps for go
        }else if(moveArray[0].equals("get")){

        }else if(moveArray[0].equals("look")){

        }else if(moveArray[0].equals("use")){

       }else{
            //sout something
            getAction();
        }
    }
}