package edu.hackaton.backend.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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

    @Lob
    @Column(name = "image", columnDefinition="BLOB")
    private byte[] image;

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

    @OneToMany(mappedBy = "game")
    @JsonIgnoreProperties("game")
    private List<Review> reviews;

    private Game() {}
}
