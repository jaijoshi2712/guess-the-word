package com.javaproject.guesstheword.guesstheword.contollers;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.javaproject.guesstheword.guesstheword.service.GameService;
import com.javaproject.guesstheword.guesstheword.utils.GameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameUtils gameUtils;


    @GetMapping("/game-home")
    public String showGameHomePage(@RequestParam(value = "guessedChar", required = false) String guessedChar,  Model model){

        String randomWord = gameService.toString();


        if (guessedChar != null){
            boolean isGuessCorrect = gameService.addGuess(guessedChar.charAt(0));
            randomWord = gameService.toString();
            if (!isGuessCorrect){
                gameUtils.reduceTry();

            }
        }


        model.addAttribute( "wordToDisplay", randomWord);
        model.addAttribute("triesLeft", gameUtils.getTriesRemaining());

        return "game-home-page";
    }


}
