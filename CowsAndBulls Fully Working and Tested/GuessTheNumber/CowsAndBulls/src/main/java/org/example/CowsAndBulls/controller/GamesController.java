package org.example.CowsAndBulls.controller;

import org.example.CowsAndBulls.model.Games;
import org.example.CowsAndBulls.model.Rounds;
import org.example.CowsAndBulls.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GamesController {
    @Autowired
    GamesService service;

    // Create (in CRUD)
    // Works
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame() {
        return service.newGame();
    }

    // Create/Update (in CRUD)
    // Works
    @PostMapping("/guess")
    public Rounds makeGuess(@RequestBody Rounds round) {
        return service.makeGuess(round);
    }

    // Retrieve (in CRUD)
    // Works
    @GetMapping("/game")
    public List<Games> getAllGames() {
        return service.getAllGames();
    }

    // Retrieve (in CRUD)
    // Works
    @GetMapping("/game/{game_id}")
    public Games getGameById(@PathVariable("game_id") int gameId) {
        return service.getGameById(gameId);
    }

    // Retrieve (in CRUD)
    // Works
    @GetMapping("/rounds/{game_id}")
    public List<Rounds> getRoundsForGame(@PathVariable("game_id") int gameId) {
        return service.getAllRoundsByGameId(gameId);
    }

    // Retrieve (in CRUD)
    // Works
    @GetMapping("/rounds")
    public List<Rounds> getAllRounds() {
        return service.getAllRounds();
    }

    // Update (in CRUD)
    @PutMapping ("/game/{gameId}")
    public ResponseEntity update(@PathVariable int gameId, @RequestBody Games game) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (gameId != service.getGameId(game)) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (service.manualUpdateGame(game)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    // Delete (in CRUD)
    @DeleteMapping("game/{gameId}")
    public ResponseEntity deleteByGameId(@PathVariable int gameId) {
        return service.deleteByGameId(gameId);
    }


}