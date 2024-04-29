package com.javaproject.guesstheword.guesstheword.contollers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {


    @GetMapping("/game-home")
    public String showGameHomePage(){
        return "game-home-page";
    }


}
