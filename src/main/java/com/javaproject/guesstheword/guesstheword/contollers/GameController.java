package com.javaproject.guesstheword.guesstheword.contollers;


import com.javaproject.guesstheword.guesstheword.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    @Autowired
    GameService gameService;


    @GetMapping("/game-home")
    public String showGameHomePage(){
        
        String randomWord = gameService.toString();
        return "game-home-page";
    }


}
