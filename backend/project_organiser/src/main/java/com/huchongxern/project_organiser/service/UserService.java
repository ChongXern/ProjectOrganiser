package com.huchongxern.project_organiser.service;

import com.huchongxern.project_organiser.model.User;
import com.huchongxern.project_organiser.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<String> getAllGithubUsernames() {
        List<String> allGithubUsernames = new ArrayList<>();
        List<User> allUsers = getAllUsers();

        for (User user : allUsers) {
            String githubUsername = user.getGithubUsername();
            allGithubUsernames.add(githubUsername);
        }

        return allGithubUsernames;
    }

    public User getUserById(ObjectId id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new RuntimeException("User not found for given id: " + id);
    }

    public User getUserByGithubUsernameAndId(String githubUsername, ObjectId id) {
        User user = getUserById(id);
        if (user.getGithubUsername().equals(githubUsername)) {
            return user;
        }
        throw new RuntimeException("Access denied. Github username " + githubUsername + " does not match");
    }
}
