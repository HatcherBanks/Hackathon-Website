package edu.hackaton.backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hackaton.backend.model.Game;
import edu.hackaton.backend.model.User;
import edu.hackaton.backend.repo.GameRepo;
import edu.hackaton.backend.repo.UserRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class GameService {
    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private UserRepo userRepo;

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
