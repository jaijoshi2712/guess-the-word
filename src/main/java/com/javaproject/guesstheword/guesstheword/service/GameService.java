package com.javaproject.guesstheword.guesstheword.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GameService {

    private String randomlyChoosenWord = null;
    // private String [] randomWords = {"father", "mother", "sister", "string", "hello", "light", "java"};
    private char [] allCharactersOfTheWord;
    Random random = new Random();

    public GameService() {
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://random-word-api.herokuapp.com/word?length=5"))
        // https://random-word-api.herokuapp.com/word?number=10&length=5
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
    HttpResponse<String> response=null;
    try {
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("Length of Response body " +response.body());
    String responseString = response.body().toString();
        randomlyChoosenWord =responseString.substring(2, responseString.length() - 2);
        // randomlyChoosenWord = randomWords[random.nextInt(randomWords.length)];
        allCharactersOfTheWord = new char[randomlyChoosenWord.length()];
    }

    @Override
    public String toString() {

        String ret = "";

        for(char c: allCharactersOfTheWord){
            if (c=='\u0000'){
                ret = ret+ "_";
            }
            else{
                ret = ret + c;
            }
            ret = ret + ' ';
        }

        return ret;
    }

    public boolean addGuess(char guessedChar) {

        boolean isGuessCorrect = false;


        for (int i=0;i<randomlyChoosenWord.length();i++) {

            if (guessedChar == randomlyChoosenWord.charAt(i)) {
                allCharactersOfTheWord[i] = guessedChar;
                isGuessCorrect = true;

            }
        }

        return isGuessCorrect;






    }
}
