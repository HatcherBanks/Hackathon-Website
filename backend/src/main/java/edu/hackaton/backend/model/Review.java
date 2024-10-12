package edu.hackaton.backend.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder @Getter @Setter
@Entity
@Table(name="reviews")
public class Review {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    public UUID id;

    @ManyToOne
    @JsonIgnoreProperties("reviews")
    private Game game;

    @ManyToOne
    @JsonIgnoreProperties("reviews")
    private User user;

    @NotBlank(message = "review is required")
    private String review;

    @Max(value = 5, message = "stars must be between 0 and 5")
    @Min(value = 0, message = "stars must be between 0 and 5")
    @NotNull(message = "stars is required")
    private int stars;
}
