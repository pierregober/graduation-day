package com.graduation.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduation.client.GameClient;
import com.graduation.elements.Bully;
import com.graduation.elements.Player;
import com.graduation.utils.ConsoleColor;
import com.graduation.utils.Prompter;
import com.graduation.utils.TextFileReader;
import com.graduation.utils.readMap;

//import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class GameCombat {
    private static Scanner action = new Scanner(System.in);
    private static int bullyHitPoints = (int) (Math.random() * 25);
    private static JsonNode data;

    private GameCombat() {
    }

    public static void initializeCombatScene() throws Exception {

        TextFileReader tfr = new TextFileReader();
        ArrayList<String> fL = tfr.readFightFile();
        Prompter.clearScreen();
        System.out.println(GameClient.getPlayer());
        System.out.println(readMap.convertedMap());
        System.out.println(ConsoleColor.GREEN_BOLD
                + "                                   *******************************************************************************************");
        System.out.println("                                   " + GameClient.getPlayer());
        System.out.println(
                "                                   ******************************************************************************************* "
                        + ConsoleColor.RESET);
        System.out.println(fL.get(0));
        System.out.println(Bully.getName() + " has spotted you!");
        fight();
    }

    public static void fight() throws Exception {
        TextFileReader tfr = new TextFileReader();
        ArrayList<String> fL = tfr.readFightFile();
        if (Player.getHealth() <= 0) {
            // Step 1: You get a report card stolen
            String reportCard = Player.getSubjectTaken().remove(0); // remove first entry, store the string for later
            Player.setSubjectTaken(Player.getSubjectTaken()); // pass back the List to the method
            // Step 2: Dialogue for the user
            System.out.println(fL.get(2) + " " + reportCard + " again.");
            // Step 3: Remove the bully for this level
            Bully.setPresence(false);

        } else {
            Prompter.clearScreen();
            System.out.println(readMap.convertedMap());
            System.out.println(fL.get(3));
            System.out.println(fL.get(4) + " " + Player.getHealth());
            System.out.println(fL.get(5) + " " + Bully.getHealth());
            System.out.println(fL.get(6));

            System.out.println(Player.getName() + " " + fL.get(9));
            for (String item : Player.getInventory()) {
                System.out.println(item);
            }
            System.out.println(fL.get(10));

            System.out.println(fL.get(7));
            System.out.println(fL.get(8));

            System.out.println(ConsoleColor.YELLOW + fL.get(11) + ConsoleColor.RESET);
            String move = action.nextLine();
            String[] moveArray = move.toLowerCase().split(" ");

            switch (moveArray[0]) {
                case "run":
                    // Step 1: RNG to find out if you can run successfully -- you have a 12% chance
                    if ((int) (Math.random() * 100) >= 12) {
                        System.out.println(fL.get(12));
                        GameAction.getAction();
                    } else {
                        // Massive damage if caught trying to run
                        int rightHook = Player.getHealth() - 50;
                        Player.setHealth(rightHook);
                        System.out.println(fL.get(13));
                        fight();
                    }
                    break;
                case "fight":
                    // Step 1: RNG for number with just your fists && Negate the points from the
                    // Bully's health-- allows for a one hit kill
                    System.out.println(fL.get(30));
                    Thread.sleep(3000);
                    int kick = (int) (Math.random() * 100);
                    Bully.setHealth(Bully.getHealth() - kick);
                    // Step 2: Conditional dialogue
                    if (kick > 80) {
                        System.out.println(fL.get(14) + " " + kick + " damage!!");
                        Thread.sleep(3000);
                    } else if (kick > 50) {
                        System.out.println(fL.get(15) + " " + kick + " to the bully!!");
                        Thread.sleep(3000);
                    } else {
                        System.out.println(fL.get(16) + " " + kick + " to the bully!!");
                        Thread.sleep(3000);
                    }
                    // Step 3: Recursion if needed
                    if (Bully.getHealth() <= 0) {
                        System.out.println(fL.get(17) + Bully.getName() + " " + fL.get(18));
                        Thread.sleep(3000);
                        GameAction.getAction();
                    } else {
                        // Bully's turn;
                        bullyAttack();
                        fight();
                    }
                    break;
                case "use":
                    if (Player.getInventory().contains("Red Bull")) {
                        Player.setHealth(Player.getHealth() + 100);
                        GameClient.items.remove("Red Bull");
                        fight();
                    }
                    else {
                        System.out.println("You do not have Red Bull in your bookbag items");
                        fight();
                    }
                    break;
                default:
                    System.out.println(fL.get(23));
                    fight();
                    break;
            }
        }
    }

    private static void getItemDesc(String item) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            data = mapper.readTree(Files.readAllBytes(Paths.get("Banner/items.json")));
            JsonNode filteredData = data.get(String.valueOf(item));
            System.out.println(filteredData.asText());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void bullyAttack() throws Exception {
        TextFileReader tfr = new TextFileReader();
        ArrayList<String> fL = tfr.readFightFile();
        // Step 1: Set the player health minus the bully strike
        int punch = Player.getHealth() - bullyHitPoints;
        Player.setHealth(punch);
        // Step 2: Conditional dialogue
        if (bullyHitPoints > 50) {
            System.out.println(fL.get(24) + " " + bullyHitPoints + " " + fL.get(25));
        } else if (bullyHitPoints > 25) {
            System.out.println(fL.get(26) + " " + bullyHitPoints + " " + fL.get(27));
        } else {
            System.out.println(fL.get(28) + " " + bullyHitPoints + " " + fL.get(29));
        }
    }
}