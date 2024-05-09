package com.javaproject.guesstheword.guesstheword.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GameService {

    private PreparedStatement stmt;

    private String randomlyChoosenWord = null;
    // private String [] randomWords = {"father", "mother", "sister", "string", "hello", "light", "java"};
    private char [] allCharactersOfTheWord;
    Random random = new Random();
    private Connection conn;

    public GameService() throws SQLException {


        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/word_game_db", "admin", "admin");
            stmt = conn.prepareStatement("INSERT INTO words (word) VALUES (?)");
            storeRandomWord();
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        HttpRequest request = HttpRequest.newBuilder()
//        .uri(URI.create("https://random-word-api.herokuapp.com/word?length=5"))
//        // https://random-word-api.herokuapp.com/word?number=10&length=5
//		.method("GET", HttpRequest.BodyPublishers.noBody())
//		.build();
//    HttpResponse<String> response=null;
//    try {
//        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//    } catch (IOException e) {
//        e.printStackTrace();
//    } catch (InterruptedException e) {
//        e.printStackTrace();
//    }
        String query = "SELECT word FROM words ORDER BY RAND() LIMIT 1";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            randomlyChoosenWord = resultSet.getString("word");
        }
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

    public void storeRandomWord() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://random-word-api.herokuapp.com/word?length=5"))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String randomWord = response.body();
            stmt.setString(1, randomWord);
            stmt.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
