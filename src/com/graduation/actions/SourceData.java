package com.graduation.actions;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SourceData {

    public static String asString(String dataset){
        try {
            Stream<String> lines = Files.lines(
                    Paths.get(ClassLoader.getSystemResource(dataset).toURI()));

            return lines.collect(Collectors.joining());

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}