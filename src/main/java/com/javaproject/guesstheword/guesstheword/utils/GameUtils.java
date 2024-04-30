package com.javaproject.guesstheword.guesstheword.utils;

import org.springframework.stereotype.Component;

@Component
public class GameUtils {


    private int MAX_TRIES = 5;

    public int reduceTry(){
        MAX_TRIES-=1;
        return MAX_TRIES;
    }

    public int getTriesRemaining(){
        return MAX_TRIES;
    }
}
