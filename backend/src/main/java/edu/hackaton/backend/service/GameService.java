package edu.hackaton.backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hackaton.backend.model.Game;
import edu.hackaton.backend.model.Review;
import edu.hackaton.backend.model.User;
import edu.hackaton.backend.repo.GameRepo;
import edu.hackaton.backend.repo.ReviewRepo;
import edu.hackaton.backend.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class GameService {
    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    public Game addGame(Game game) {
        if (gameRepo.findByTitle(game.getTitle()) != null) {
            throw new ServiceException("title", "Title already exists");
        }
        return gameRepo.save(game);
    }

    public void deleteGame(UUID gameId) {
        Game game = gameRepo.findGameById(gameId);
        if (game == null) {
            throw new ServiceException("game", "Game with given id doesn't exist");
        }
        game.getReviews().forEach(review -> {
            review.deleteUser();
            reviewRepo.delete(review);
        });
        gameRepo.delete(game);
    }
}
