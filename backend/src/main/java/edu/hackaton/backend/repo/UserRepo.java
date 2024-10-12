package edu.hackaton.backend.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import edu.hackaton.backend.model.User;


public interface UserRepo extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User>{
    User findUserById(UUID id);
    Optional<User> findUserByEmail(String email);
    User findUserByUserName(String userName);
}
