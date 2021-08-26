package com.graduation.elements;

import com.graduation.utils.Prompter;

public class Player {
    private String name;
    private int credit;
    private String location;
    private int health;

    public Player(String name, int credit, int health){
        this.name = name;
        this.credit = credit;
        this.health = health;
    }

    public String answerQuestion(Prompter prompter){
        String answer = prompter.prompt(
                "Answer with A,B,C, or D \n",
                "[A-D]|[a-d]",
                "A,B,C or D ONLY");

        return answer;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }



    public String getLocation() {
        return location;
    }

    public int getCredit() {
        return credit;
    }

    public String getName() {
        return name;
    }


}
