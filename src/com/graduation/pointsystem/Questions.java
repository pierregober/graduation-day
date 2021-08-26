package com.graduation.pointsystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Questions {
    public static final Map<String,Integer> categories=
            Map.of("maths",19,"history",23,"geography",22,"sports",21,"general knowledge",9);

    private List<Breakdown> getQuestions(String type) throws JsonProcessingException, ExecutionException, InterruptedException {
        //make a client object
        HttpClient client= HttpClient.newHttpClient();
        //create a request object
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create("https://opentdb.com/api.php?amount=5&category="+categories.get(type.toLowerCase())+"&difficulty=easy"))
                .build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String text = response.thenApply(HttpResponse::body).join();
        ObjectMapper questions= new ObjectMapper();
        Trivial category=questions.readValue(text,Trivial.class);
        List<Breakdown> lists= category.getResults();
        return lists;
    }

    public int generateQuestions(String type){
        List<Breakdown> samples=null;
        Map<Character, String> possible_answers=new LinkedHashMap<>();
        try{
            samples=getQuestions(type);
        }
        catch (ExecutionException | JsonProcessingException | InterruptedException ex){
            ex.printStackTrace();
        }
        int counter = 0;
        Scanner answer=new Scanner(System.in);
        for(Breakdown sample:samples){
            System.out.println(Jsoup.parse(sample.getQuestion()).text());
            List<String> answers=new ArrayList<>();
            answers.add(sample.getCorrect_answer());
            for(Object incorrect:sample.getIncorrect_answers()){
                answers.add(incorrect.toString());
            }
            Collections.shuffle(answers);
            char option='A';
            for(String possible_answer:answers){
                possible_answers.put(option++,Jsoup.parse(possible_answer).text());
            }
            for(Map.Entry<Character,String> options: possible_answers.entrySet()){
                System.out.println(options.getKey()+") "+options.getValue());
            }
            char chosen =answer.nextLine().trim().toUpperCase().charAt(0);
            if(possible_answers.get(chosen).compareTo(Jsoup.parse(sample.getCorrect_answer()).text())==0){
                System.out.println("correct");
                counter+=1;
            }
            else{
                System.out.println("Incorrect");
            }
            System.out.println();
        }
       return counter;
    }

}
