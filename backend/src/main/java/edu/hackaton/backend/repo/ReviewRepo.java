package edu.hackaton.backend.repo;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hackaton.backend.model.Review;

public interface ReviewRepo extends JpaRepository<Review, UUID> {
    Review findReviewById(UUID id);
    Set<Review> findReviewsByGameId(UUID gameId);
}
