package com.javaproject.guesstheword.guesstheword.utils;

import com.javaproject.guesstheword.guesstheword.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GameUtils {

    @Autowired
    ConfigurableApplicationContext applicationContext;


    private int MAX_TRIES = 5;

    public int reduceTry(){
        MAX_TRIES-=1;
        return MAX_TRIES;
    }

    public int getTriesRemaining(){
        return MAX_TRIES;
    }

    public void resetTries(){
        MAX_TRIES = 5;
    }

    public GameService reload(){
        GameService gameService = applicationContext.getBean(GameService.class);

        return gameService;
    }
}
