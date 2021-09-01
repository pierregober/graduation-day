package com.graduation.utils;

import com.graduation.client.GameClient;
import com.graduation.elements.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;

/**
 * <p>
 * General-purpose prompter mechanism that delegates to a {@code Scanner}, which
 * must be provided in the constructor.  The {@code Scanner} passed to the constructor
 * must be initialized by the client, and allows for console-based or file-based input.
 * </p>
 *
 * <p>
 * Console-based input would typically be used for running the actual application.
 * File-based input can be used to automate unit testing without human involvement.
 * </p>
 *
 * <pre>
 * <code>
 *     // To use with a human user at the console:
 *     Prompter prompter = new Prompter(new Scanner(System.in));
 *
 *     // To facilitate automated unit testing without human involvement:
 *     Prompter prompter = new Prompter(new Scanner(new File("responses.txt")));
 * </code>
 * </pre>
 *
 * @author Jay Rostosky
 * @version 1.0
 */
public class Prompter {
    private Scanner scanner;

    /**
     * Creates a {@code Scanner}-based prompter object, that delegates to the {@code Scanner}
     * for all input.  All input is read (and returned) as a single line of text.
     *
     * @param scanner delegate object used by this prompter for reading input.
     */
    public Prompter(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Outputs provided text.  Simply calls {@code System.out.println(info)}.
     *
     * @param info informative text to show the user, not a prompt message.
     * @return the provided text - most applications will ignore this return value.
     */
    public String info(String info) {
        System.out.println(info);
        return info;
    }

    /**
     * Prompts for input, and returns the line of text entered, as a string.
     *
     * @param promptText prompt message.
     * @return the line of text that was input, as a string.
     */
    public String prompt(String promptText) {
        String response;
        while (true) {
            System.out.print(promptText);
            response = scanner.nextLine().toLowerCase();
            if (response.matches("s") && GameClient.getPlayer() != null) {
                //add function to show player status
                System.out.println(displayMAP());
                //blank line
                System.out.println(
                        "Grade: "+Player.getGrade()+" | "+
                        "Credit: "+Player.getCredit()+" | \n"+
                        "Location: "+Player.getLocation()+"\n"+
                        "###################################");
                //give player a helpful message
            }
            else if (response.matches("s") ) {
                System.out.println("No player!!!");
            }
            else if (response.matches("h")) {
                System.out.println(
                        "Use the following actions:" +
                        "GO [north, south, east, west, up, down]\n" +
                        "GET/USE [item]\n"+
                        "Look") ;
                //blank line
                System.out.println();
                //quit the game by inputting Q/q
            } else if (response.matches("q")) {
                System.exit(0);
            }
            else{
                return response;
            }
        }


    }

    /**
     * <p>
     * Prompts for input, validates it against the regex pattern provided, and returns
     * the line of text entered, as a string.  If the input does not match the pattern,
     * the error message text is displayed, and then the prompt text is displayed again.
     * The input is validated against the regex pattern via {@code String.matches()}.
     * </p>
     *
     * <p>
     * The prompt text and error message text are output verbatim, exactly as provided.
     * To add a blank line or two between the user input line, the error message line,
     * and the follow-on user prompt, just include {@code "\n"} in the {@code retryText} value,
     * as appropriate.  For example:
     * </p>
     *
     * <pre>
     * <code>
     *     prompter.prompt("Please enter your age: ", "\\d+", "\nThat is not a valid age!\n");
     * </code>
     * </pre>
     *
     * @param promptText prompt message.
     * @param pattern    regex pattern, used to validate the input string.
     * @param retryText  error message displayed when input string does not match regex pattern.
     * @return the line of text that was input, as a string.
     */
    public String prompt(String promptText, String pattern, String retryText) {
        String response = null;
        boolean validResponse = false;

        while (!validResponse) {
            System.out.print(promptText);
            response = scanner.nextLine();
            if (response.matches("help")) {
                System.out.println("helps on the way");
                break;
            }


            //check user input if it match pattern
            validResponse = response.matches(pattern);
            if (!validResponse) {
                System.out.println(retryText);
            } else {
                break;
            }
        }
        return response;
    }

    public static String displayMAP() {
        String result = null;
        try {
            result = Files.readString(Path.of("Banner/map-" + Player.getGrade().toString() + ".txt"));
        } catch (IOException e) {
        }
        return result;
    }

}