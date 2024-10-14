package edu.hackaton.backend.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.hackaton.backend.model.Game;
import edu.hackaton.backend.model.Genre;
import edu.hackaton.backend.model.Role;
import edu.hackaton.backend.model.Type;
import edu.hackaton.backend.model.User;
import edu.hackaton.backend.repo.GameRepo;
import edu.hackaton.backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataInserter implements ApplicationRunner{
    private final UserRepo userRepo;
    private final GameRepo gameRepo;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("check if admin user exists ...");
        Optional<User> adminUserOptional = userRepo.findUserByEmail("admin@gmail.com");
        if(adminUserOptional.isEmpty()){
            log.info("Saving admin user ...");
            userRepo.save(User.builder().firstName("Admin").lastName("Admin").email("admin@gmail.com").password("{noop}admin").role(Role.ADMIN).birthDate(LocalDate.now().minusYears(1)).userName("admin").phoneNumber("911").build());
        }
        else {
            log.info("Admin user already exists: {}", adminUserOptional);
        }
        Game game = gameRepo.findByTitle("CatSim");
        if(game == null) {
            gameRepo.save(Game.builder().title("CatSim").description("Description 1").price(10.0).image(null).genre(Genre.Simulation).type(Type.ThreeD).build());
        }
        Game game2 = gameRepo.findByTitle("Game1");
        if(game2 == null) {
            gameRepo.save(Game.builder().title("Game1").description("Description 1").price(10.0).image(null).genre(Genre.Simulation).type(Type.ThreeD).build());
        }
    }
}
