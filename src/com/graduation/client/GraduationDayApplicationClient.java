package com.graduation.client;

import java.io.IOException;
import java.util.Scanner;
import com.graduation.utils.Prompter;

public class GraduationDayApplicationClient {
    public static void main(String[] args) {
            TitleScreen.displayInstructions();

        Prompter prompter = new Prompter(new Scanner(System.in));
        GameClient game = new GameClient(prompter);
        game.initialize();
    }
}


