package edu.hackaton.backend.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.hackaton.backend.model.Review;
import edu.hackaton.backend.repo.UserRepo;
import edu.hackaton.backend.service.ReviewService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("getReviewsFromGameById/{gameId}")
    public Set<Review> getReviewsFromGameById(@PathVariable("gameId") UUID gameId) {
        return reviewService.getReviewsFromGameById(gameId);
    }

    @PostMapping("addReview/{gameId}")
    public Review addReview(@RequestBody Review review, @PathVariable("gameId") UUID gameId, Authentication auth) {
        return reviewService.addReview(review, gameId, auth.getName());
    }

    @DeleteMapping("deleteReview/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") UUID reviewId, Authentication auth) {
        reviewService.deleteReview(reviewId, auth.getName());
    }

    @GetMapping("friendReviews")
    public Set<Review> getFriendReviews(Authentication auth) {
        return reviewService.getFriendReviews(auth.getName());
    }
}
