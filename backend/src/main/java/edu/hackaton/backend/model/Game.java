package edu.hackaton.backend.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder @Getter @Setter
@Entity
@Table(name="games")
public class Game {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    public UUID id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Genre is required")
    private Genre genre;

    @NotNull(message = "Type is required")
    private Type type; 

    @NotNull(message = "Price is required")
    private double price;

    private Game() {}
}
