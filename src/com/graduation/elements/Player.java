package com.graduation.elements;

import com.graduation.utils.Grade;
import com.graduation.utils.Prompter;

public class Player {
    private String name;
    private int credit;
    private String location;
    private int health;
    private Grade grade;

    public Player(String name, int credit, int health, Grade grade){
        this.name = name;
        this.credit = credit;
        this.health = health;
        this.grade = grade;
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


}
