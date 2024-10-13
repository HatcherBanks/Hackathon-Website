package edu.hackaton.backend.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
        review.setUser(user);
        review.setGame(game);
        user.getReviews().add(review);
        userRepo.save(user);
        game.getReviews().add(review);
        gameRepo.save(game);
        return reviewRepo.save(review);
    }

    public void deleteReview(UUID reviewId, String email) {
        Review review = reviewRepo.findReviewById(reviewId);
        User user = userRepo.findUserByEmail(email).get();
        Game game = gameRepo.findGameById(review.getGame().getId());

        if (!review.getUser().getEmail().equals(email)) {
            throw new ServiceException("review", "You can't delete a review that you don't own");
        }
        
        user.getReviews().remove(review);
        userRepo.save(user);
        game.getReviews().remove(review);
        gameRepo.save(game);
        reviewRepo.delete(review);
    }

    public Set<Review> getReviewsFromGameById(UUID gameId) {
        return reviewRepo.findReviewsByGameId(gameId);
    }

    public Set<Review> getFriendReviews(String name) {
        Set<User> friends = userRepo.findUserByEmail(name).get().getFriends();
        Set<Review> reviews = friends.stream().map(User::getReviews).flatMap(Set::stream).collect(Collectors.toSet());
        return reviews;
    }
}
