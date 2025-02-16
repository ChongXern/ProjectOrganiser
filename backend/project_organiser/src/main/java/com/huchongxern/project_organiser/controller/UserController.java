package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.model.User;
import com.huchongxern.project_organiser.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    //get post put delete patch
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
}
