package edu.hackaton.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.hackaton.backend.model.User;
import edu.hackaton.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(Authentication auth) {
        userService.deleteUser(auth.getName());
    }

    @GetMapping("/getUserByUserName/{userName}")
    public User getUserByUserName(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }

    @GetMapping("/getUserByEmail")
    public Optional<User> getUserByEmail(Authentication auth){
        return userService.findUserByEmail(auth.getName());
    }

    @PostMapping("/addGameToWantToPlay/{gameId}")
    public User addGameToWantToPlay(@PathVariable UUID gameId, Authentication auth){
        return userService.addToWantToPlay(gameId, auth.getName());
    }

    @DeleteMapping("/removeGameFromWantToPlay/{gameId}")
    public User removeGameFromWantToPlay(@PathVariable UUID gameId, Authentication auth){
        return userService.removeFromWantToPlay(gameId, auth.getName());
    }

    @PostMapping("/addGameToCurentlyPlaying/{gameId}")
    public User addGameToCurentlyPlaying(@PathVariable UUID gameId, Authentication auth){
        return userService.addToCurrentlyPlaying(gameId, auth.getName());
    }

    @DeleteMapping("/removeGameFromCurrentlyPlaying/{gameId}")
    public User removeGameFromCurrentlyPlaying(@PathVariable UUID gameId, Authentication auth){
        return userService.removeFromCurrentlyPlaying(gameId, auth.getName());
    }

    @PostMapping("/addGameToCompleted/{gameId}")
    public User addGameToCompleted(@PathVariable UUID gameId, Authentication auth){
        return userService.addToCompleted(gameId, auth.getName());
    }

    @DeleteMapping("/removeGameFromCompleted/{gameId}")
    public User removeGameFromCompleted(@PathVariable UUID gameId, Authentication auth){
        return userService.removeFromCompleted(gameId, auth.getName());
    }

    @GetMapping("/getFriends")
    public Set<User> getFriends(Authentication auth){
        return userService.getFriends(auth.getName());
    }

    @PostMapping("/addFriend/{friendId}")
    public User addFriend(@PathVariable UUID friendId, Authentication auth){
        return userService.addFriend(friendId, auth.getName());
    }

    @DeleteMapping("/removeFriend/{friendId}")
    public User removeFriend(@PathVariable UUID friendId, Authentication auth){
        return userService.removeFriend(friendId, auth.getName());
    }
}
