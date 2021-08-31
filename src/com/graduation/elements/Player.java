package com.graduation.elements;

import com.graduation.utils.Grade;
import com.graduation.utils.Prompter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Player {
    private String name;
    private int credit;
    private String location;
    private int health;
    private Grade grade;
    private static List<String> inventory = new ArrayList<>(Arrays.asList("notebook"));

    public Player(String name, int credit, int health, Grade grade, String location){
        this.name = name;
        this.credit = credit;
        this.health = health;
        this.grade = grade;
        this.location = location;
        this.inventory = inventory;
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

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
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

    public Grade getGrade() {
        return grade;
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

    public List<String> getInventory() {
        return inventory;
    }
}
