package com.graduation.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduation.actions.GameAction;
import com.graduation.actions.SourceData;
import com.graduation.elements.Player;
import com.graduation.pointsystem.PointSystem;
import com.graduation.utils.Grade;
import com.graduation.utils.Prompter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameClient {
    private final Prompter prompter;
    private static Player player;
    private static ObjectMapper mapper = new ObjectMapper();
    private static JsonNode data;
    private static JsonNode prevRoom;
    private static List<String> notSubject = new ArrayList<>(Arrays.asList("gym", "cafeteria", "hallway"));

    public GameClient(Prompter prompter) {
        this.prompter = prompter;
    }
    public void initialize(){
        player = getPlayer();
        //Step 1 -- Generate the location info from the json
        getLevelDetails("desc");
        System.out.println("You are somehow in the computer lab... Mr.Tindall stares you down to ask you a question. Your body is locked. You are forced to stay\n\n");
        //Step 2a -- Some conditional seeing if its is a subject
        if(player.getLocation().equals("cafeteria") || player.getLocation().equals("gym") || player.getLocation().equals("hallway")){
            System.out.println("Pierre fgiure out what we do here -- possible item grab"); //TO-DO
        }else{
            //Step 2b -- Call method to initalize the question sequence
            PointSystem.teacherQuestions(player.getLocation().toLowerCase());
        }
    }

    public static void nextLocation(String location){
        //Grab the previous and read the location according to direction within it's JSON properties
        try{
            String nextLoc = prevRoom.get(location).textValue();
            player.setLocation(nextLoc);
            getLevelDetails("desc");
            //Determine if it's a subject room
            if(!notSubject.contains(nextLoc)){
                PointSystem.teacherQuestions(player.getLocation().toLowerCase());
            }else{
                //What to do if you are in these areas? -- capture the action
                continueJourney();
            }
            //Catch if the direction is null
        }catch(NullPointerException e){
            System.out.println("You can't go that direction! Quick Try a different cardinal direction please");
            GameAction.getAction();
        }
    }

    public static void getLevelDetails(String key){
        try{
            data = mapper.readTree(SourceData.asString());
            prevRoom = getLastRoom(data, player.getLocation(), player.getGrade());
            JsonNode filteredData = getDetails(data, player.getLocation(), player.getGrade(), key);
            if(key.equals("item")){
                //if the room does have an item check if player already has it!
                if(player.getInventory().contains(filteredData.asText())){
                    //View to tell the user that they grabbed the room item already
                    System.out.println("There are no more items to grab from this room...\nremember you grabbed the " + filteredData + "\n");
                    continueJourney();
                }else{
                    //Method to add the item to the player's bookbag
                    List<String> items = player.getInventory();
                    items.add(filteredData.textValue());
                    System.out.println("Sucessfully added " + filteredData + " to your backpack!");
                    continueJourney();
                }
            }else{
                System.out.println(filteredData);
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public static void nextLevel(){
        //Step 1: set the new grade of the player
        //Step 2: clear the taken courses
        //Step 3: call continue journey
    }

    public static void continueJourney(){
        System.out.println("Whats your next move?");
        GameAction.getAction();
    }

    private static JsonNode getDetails(JsonNode node, String location, Grade grade, String key) {
         return node.get(String.valueOf(grade)).get(location).get(key);
    }

    private static JsonNode getLastRoom(JsonNode node, String location, Grade grade) {
        return node.get(String.valueOf(grade)).get(location);
    }

    public Player getPlayer() {
        String userName = prompter.prompt("Please enter your name below \n");
        return new Player(userName, 0, 10, Grade.FRESHMAN, "Computers");
    }
}