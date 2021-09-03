package com.graduation.actions;

import com.graduation.utils.readMap;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SourceData {

    public static String asString(){
        try {
            Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource("rooms.json").toURI()));
            String some = lines.collect(Collectors.joining());
            System.out.println(some);
            return some;
        } catch (Exception exception) {
            return readMap.importTXT("Banner/rooms.txt");
        }
    }

}
