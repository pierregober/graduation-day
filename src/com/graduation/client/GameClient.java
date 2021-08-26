package com.graduation.client;

import com.graduation.elements.Player;
import com.graduation.utils.Grade;
import com.graduation.utils.Prompter;

public class GameClient {
    private final Prompter prompter;
    private Player player;

    public GameClient(Prompter prompter) {
        this.prompter = prompter;
    }

    public void initialize() {
        player = getPlayer();
        System.out.println(player.toString());

    }

    public Player getPlayer() {
        String userName = prompter.prompt("Please enter your name below \n");
        Player player = new Player(userName, 0, 10, Grade.FRESHMAN, "Literature");        //starting point for the player
        return player;
    }
}