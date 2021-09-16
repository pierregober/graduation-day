package com.graduation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TextFileReader {

    // C:\graduation-day\Banner\fight.txt

    private String urlPath;

    public TextFileReader() {

    }

    public ArrayList<String> readFightFile() throws Exception {
        ArrayList<String> fightList = new ArrayList<String>();
        File file = new File("Banner/fight.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            fightList.add(st);
        }
        return fightList;
    }

    public ArrayList<String> readDashBoard() throws Exception {
        ArrayList<String> dashBoardList = new ArrayList<String>();
        File file = new File("Banner/dashBoard.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            dashBoardList.add(st);
        }
        return dashBoardList;
    }

}
