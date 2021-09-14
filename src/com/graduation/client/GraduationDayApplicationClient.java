package com.graduation.client;

import java.io.IOException;
import java.util.Scanner;
import com.graduation.utils.Prompter;
import com.graduation.utils.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GraduationDayApplicationClient {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        // display landing screen
            TitleScreen.displayInstructions();

        Prompter prompter = new Prompter(new Scanner(System.in));
        GameClient game = new GameClient(prompter);

        game.initialize();
    }
}


