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
    private Player player;
    // ObjectMapper is stateless and thread-safe so it's OK to create one like this
    private static final ObjectMapper mapper = new ObjectMapper();

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
            JsonNode data = mapper.readTree(SourceData.asString());
            JsonNode filteredData = getRoomDesc(data, player.getLocation(), "desc");
            System.out.println(filteredData);
        }catch(IOException e){
            System.out.println(e);
        }

        //Step 2 -- use stephens questions to advance
        System.out.println("The teacher stares you down to ask you a question. Your body is locked. You are forced to stay\n\n");
        PointSystem.teacherQuestions();

    }

    public static void continueJourney(){
        System.out.println("Your body is unfrozen!!! You can now move!");
        GameAction.getAction();
    }

    private static JsonNode getRoomDesc(JsonNode neoJsonNode, String location, String field) {
        JsonNode requestedData = neoJsonNode.get(location).get(field);
        return requestedData;
    }



    public Player getPlayer() {
        String userName = prompter.prompt("Please enter your name below \n");
        Player player = new Player(userName, 0, 10, Grade.FRESHMAN, "Literature");        //starting point for the player
        return player;
    }
}