package edu.hackaton.backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import edu.hackaton.backend.model.Game;
import edu.hackaton.backend.model.Review;
import edu.hackaton.backend.model.User;
import edu.hackaton.backend.repo.GameRepo;
import edu.hackaton.backend.repo.ReviewRepo;
import edu.hackaton.backend.repo.UserRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    public Review addReview(Review review, UUID gameId, String email) {
        User user = userRepo.findUserByEmail(email).get();
        Game game = gameRepo.findGameById(gameId);
        user.getReviews().add(review);
        userRepo.save(user);
        game.getReviews().add(review);
        gameRepo.save(game);
        return reviewRepo.save(review);
    }

    public void deleteReview(UUID reviewId) {
        Review review = reviewRepo.findReviewById(reviewId);
        User user = userRepo.findUserByEmail(review.getUser().getEmail()).get();
        Game game = gameRepo.findGameById(review.getGame().getId());
        
        user.getReviews().remove(review);
        userRepo.save(user);
        game.getReviews().remove(review);
        gameRepo.save(game);
        reviewRepo.delete(review);
    }
}
