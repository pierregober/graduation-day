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

public class GameClient {
    private final Prompter prompter;
    private static Player player;
    private static ObjectMapper mapper = new ObjectMapper();
    private static JsonNode data;
    private static JsonNode prevRoom;
    public GameClient(Prompter prompter) {
        this.prompter = prompter;
    }
    public void initialize(){
        player = getPlayer();
        //if it's a subject room then go to questions
        //after questions the player can move. -- GameAction move = new GameAction
        //move()
        //would have to set the location of the player

        //Step 1 -- first generate the location info from the json

        try{
            data = mapper.readTree(SourceData.asString());
            prevRoom = getLastRoom(data, player.getLocation(), player.getGrade());
            JsonNode filteredData = getRoomDesc(data, player.getLocation(), player.getGrade(),"desc");
            System.out.println(filteredData);
        }catch(IOException e){
            System.out.println(e);
        }

        //Step 2 -- use stephens questions to advance
        System.out.println("The teacher stares you down to ask you a question. Your body is locked. You are forced to stay\n\n");
       // String user = player.getLocation().;
       PointSystem.teacherQuestions(player.getLocation().toLowerCase(),player.getGrade(),player);
        //PointSystem.teacherQuestions("");
    }

    public static void nextLocation(String location){
        //need to grab the previous and read the location according to direction
        System.out.println(location + " \n\n" + prevRoom);
        String nextLoc = prevRoom.get(location).textValue();
        player.setLocation(nextLoc);

        //looks the same could seperate in one method but theres a slight difference I can fix later -- pierre
        try{
            data = mapper.readTree(SourceData.asString());
            prevRoom = getLastRoom(data, player.getLocation(), player.getGrade());
            JsonNode filteredData = getRoomDesc(data, player.getLocation(), player.getGrade(),"desc");
            System.out.println(filteredData);
            //Some conditional seeing if its is a subject
            //but for now will will continue -- assuming its to a subject class
            PointSystem.teacherQuestions(player.getLocation().toLowerCase(),player.getGrade(),player);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static void continueJourney(){
        System.out.println("You can now move! Where to go next?");
        GameAction.getAction();
    }

    private static JsonNode getRoomDesc(JsonNode node, String location, Grade grade, String field) {
         return node.get(String.valueOf(grade)).get(location).get(field);
    }

    private static JsonNode getLastRoom(JsonNode node, String location, Grade grade) {
        return node.get(String.valueOf(grade)).get(location);
    }

    public Player getPlayer() {
        String userName = prompter.prompt("Please enter your name below \n");
        return new Player(userName, 0, 10, Grade.FRESHMAN, "Computers");
    }

}