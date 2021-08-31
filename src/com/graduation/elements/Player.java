package com.graduation.elements;

import com.graduation.utils.Grade;
import com.graduation.utils.Prompter;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private double credit;
    private String location;
    private int health;
    private Grade grade;

    public List<String> getSubjectTaken() {
        return subjectTaken;
    }

    public void setSubjectTaken(List<String> subjectTaken) {
        this.subjectTaken = subjectTaken;
    }

    private List<String> subjectTaken=new ArrayList<>();

    public Player(String name, int credit, int health, Grade grade, String location){
        this.name = name;
        this.credit = credit;
        this.health = health;
        this.grade = grade;
        this.location = location;
    }

    public String answerQuestion(Prompter prompter){
        String answer = prompter.prompt(
                "Answer with A,B,C, or D \n",
                "[A-D]|[a-d]",
                "A,B,C or D ONLY");
        return answer;
    }

    public void setCredit(double credit) {
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

    public double getCredit() {
        return credit;
    }

    public String getName() {
        return name;
    }
}
