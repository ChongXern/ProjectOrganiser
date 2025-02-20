package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.model.User;
import com.huchongxern.project_organiser.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/githubUsernames")
    public ResponseEntity<List<String>> getAllGithubUsernames() {
        List<String> allGithubUsernames = userService.getAllGithubUsernames();
        return new ResponseEntity<>(allGithubUsernames, HttpStatus.OK);
    }

    @GetMapping("/id/{githubUsername}/{id}")
    public ResponseEntity<User> getUserByGithubUsernameAndId(@PathVariable String githubUsername,
                                                             @PathVariable ObjectId id) {
        User user = userService.getUserByGithubUsernameAndId(githubUsername, id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/createUser/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable ObjectId userId, @RequestBody User newUser){
        return new ResponseEntity<>(userService.updateUser(userId, newUser), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable ObjectId userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updateUser/displayName/{userId}")
    public ResponseEntity<User> updateUserDisplayName(@PathVariable ObjectId userId,
                                                      @RequestParam String displayName) {
        return new ResponseEntity<>(userService.updateUserDisplayName(userId, displayName), HttpStatus.OK);
    }

    @PatchMapping("/updateUser/profileImageUrl/{userId}")
    public ResponseEntity<User> updateProfileImageUrl(@PathVariable ObjectId userId,
                                                      @RequestParam String profileImageUrl) {
        return new ResponseEntity<>(userService.updateProfileImageUrl(userId, profileImageUrl), HttpStatus.OK);
    }

    @PatchMapping("/updateUser/any/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable ObjectId userId, @RequestBody Map<String, Object> updates) {
        try {
            return new ResponseEntity<>(userService.patchUser(userId, updates), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
