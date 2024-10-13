package edu.hackaton.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.hackaton.backend.model.Game;
import edu.hackaton.backend.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("") 
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @PostMapping("")
    public Game addGame(@RequestBody Game game) {
        return gameService.addGame(game);
    }

    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable UUID gameId) {
        gameService.deleteGame(gameId);
    }
}
