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
        //Step 1 -- Generate the location info from the json
        getLevelDetails();
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
        //need to grab the previous and read the location according to direction
        String nextLoc = prevRoom.get(location).textValue();
        player.setLocation(nextLoc);
        getLevelDetails();
        PointSystem.teacherQuestions(player.getLocation().toLowerCase());
    }

    public static void getLevelDetails(){
        try{
            data = mapper.readTree(SourceData.asString());
            prevRoom = getLastRoom(data, player.getLocation(), player.getGrade());
            JsonNode filteredData = getRoomDesc(data, player.getLocation(), player.getGrade(),"desc");
            System.out.println(filteredData);
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