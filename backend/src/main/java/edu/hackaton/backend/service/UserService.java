package edu.hackaton.backend.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hackaton.backend.model.Game;
import edu.hackaton.backend.model.Role;
import edu.hackaton.backend.model.User;
import edu.hackaton.backend.repo.GameRepo;
import edu.hackaton.backend.repo.UserRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    GameRepo gameRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserByUserName(String userName){
        return userRepo.findUserByUserName(userName);
    }

    public Optional<User> findUserByEmail(String email){
        return userRepo.findUserByEmail(email);
    }

    public User addUser(User newUser){
        if(newUser.getRole() == Role.ADMIN){
            throw new ServiceException("User", "You can't add a user with an admin role only an admin can do this");
        }
        if (userRepo.findUserByEmail(newUser.getEmail()) == null){
            throw new ServiceException("User", "A user with this email already exists.");
        }
        return userRepo.save(newUser);
    }

    public User updateUser(UUID userId, User newUser){
        User userWithId = userRepo.findUserById(userId);
        if (userWithId == null){
            throw new ServiceException("User", "User with given id doesn't exist");
        }
        if (userRepo.findUserByEmail(newUser.getEmail()).get().getEmail().equals(newUser.getEmail())){
            throw new ServiceException("User", "User with email already present!");
        }
        userWithId.setFirstName(newUser.getFirstName());
        userWithId.setLastName(newUser.getLastName());
        userWithId.setEmail(newUser.getEmail());
        userWithId.setPassword(newUser.getPassword());
        userWithId.setBirthDate(newUser.getBirthDate());
        userWithId.setUserName(newUser.getUserName());
        userWithId.setPhoneNumber(newUser.getPhoneNumber());
        userWithId.setRole(newUser.getRole());

        return userWithId;
    }

    public User updateRole(UUID userID, Role role) {
        User userToUpdate = userRepo.findUserById(userID);

        if (userToUpdate == null) {
            throw new ServiceException("Uses","User with ID " + userID + " not found");
        }
        userToUpdate.setRole(role);
        return userRepo.save(userToUpdate);
    }

    @Transactional
    public void deleteUser(UUID id){
        User userToDelete = userRepo.findUserById(id);
        if (userToDelete == null){
            throw new ServiceException("User", "User with given ID not found!");
        }
        userRepo.deleteById(id);
    }

    public User addToWantToPlay(UUID gameId, String email) {
        Game game = gameRepo.findById(gameId).get();
        User user = userRepo.findUserByEmail(email).get();
        user.getWantToPlay().add(game);
        return userRepo.save(user);
    }
    
    public User removeFromWantToPlay(UUID gameId, String email) {
        Game game = gameRepo.findById(gameId).get();
        User user = userRepo.findUserByEmail(email).get();
        user.getWantToPlay().remove(game);
        return userRepo.save(user);
    }

    public User addToCurrentlyPlaying(UUID gameId, String email) {
        Game game = gameRepo.findById(gameId).get();
        User user = userRepo.findUserByEmail(email).get();
        user.getCurentlyPlaying().add(game);
        return userRepo.save(user);
    }

    public User removeFromCurrentlyPlaying(UUID gameId, String email) {
        Game game = gameRepo.findById(gameId).get();
        User user = userRepo.findUserByEmail(email).get();
        user.getCurentlyPlaying().remove(game);
        return userRepo.save(user);
    }

    public User addToCompleted(UUID gameId, String email) {
        Game game = gameRepo.findById(gameId).get();
        User user = userRepo.findUserByEmail(email).get();
        user.getCompleted().add(game);
        return userRepo.save(user);
    }

    public User removeFromCompleted(UUID gameId, String email) {
        Game game = gameRepo.findById(gameId).get();
        User user = userRepo.findUserByEmail(email).get(); 
        user.getCompleted().remove(game);
        return userRepo.save(user);
    }

    public User addFriend(UUID friendId, String email) {
        User user = userRepo.findUserByEmail(email).get();
        User friend = userRepo.findUserById(friendId);
        user.getFriends().add(friend);
        return userRepo.save(user);
    }

    public User removeFriend(UUID friendId, String email) {
        User user = userRepo.findUserByEmail(email).get();
        User friend = userRepo.findUserById(friendId);
        user.getFriends().remove(friend);
        return userRepo.save(user);
    }
}