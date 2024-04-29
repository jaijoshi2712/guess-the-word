package com.javaproject.guesstheword.guesstheword.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
public class GameService {

    private String randomlyChoosenWord = null;
    private String [] randomWords = {"father", "mother", "sister", "string", "hello", "light", "java"};
    private char [] allCharactersOfTheWord;
    Random random = new Random();

    public GameService() {
        randomlyChoosenWord = randomWords[random.nextInt(randomWords.length)];
        allCharactersOfTheWord = new char[randomlyChoosenWord.length()];
    }

    @Override
    public String toString() {

        String ret = "";

        for(char c: allCharactersOfTheWord){
            if (c=='\u0000'){
                ret = ret+ "_";
            }
            ret = ret + ' ';
        }

        return ret;
    }
}
