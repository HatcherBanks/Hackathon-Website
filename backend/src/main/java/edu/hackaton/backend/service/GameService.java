package edu.hackaton.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hackaton.backend.model.Game;
import edu.hackaton.backend.repo.GameRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class GameService {
    @Autowired
    private GameRepo gameRepo;

    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    public Game addGame(Game game) {
        if (gameRepo.findByTitle(game.getTitle()) != null) {
            throw new ServiceException("title", "Title already exists");
        }
        return gameRepo.save(game);
    }
}
