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
        //Where Hongyi puts his code
        player = getPlayer();
    }



    public Player getPlayer() {
        String userName = prompter.prompt("Please enter your name below \n");
        Player player = new Player(userName, 0, 10, Grade.FRESHMAN);
        return player;
    }
}