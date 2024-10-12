package edu.hackaton.backend.repo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hackaton.backend.model.Game;
import edu.hackaton.backend.model.Genre;

public interface GameRepo extends JpaRepository<Game, UUID> {
    Game findByTitle(String title);
    List<Game> findByGenre(Genre genre);
    List<Game> findByType(Type type);
    List<Game> findByTitleAndGenre(String title, Genre genre);
    List<Game> findByTitleAndType(String title, Type type);
    List<Game> findByGenreAndType(Genre genre, Type type);
    List<Game> findByTitleAndGenreAndType(String title, Genre genre, Type type);
}
