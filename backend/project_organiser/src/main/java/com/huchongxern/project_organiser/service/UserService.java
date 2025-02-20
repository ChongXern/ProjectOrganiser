package com.huchongxern.project_organiser.service;

import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.model.User;
import com.huchongxern.project_organiser.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.*;

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

    public User createUser(User user) {
        return userRepository.save(user);
    }

    /*
    * private String githubUsername;
    @Field("display_name")
    private String displayName;
    @Field("profile_image_url")
    private String profileImageUrl;
    @Field("created_date")
    private Date createdDate;
    private List<Project> projects;*/
    public User updateUser(ObjectId userId, User newUser) {
        User existingUser = getUserById(userId);

        existingUser.setGithubUsername(newUser.getGithubUsername());
        existingUser.setDisplayName(newUser.getDisplayName());
        existingUser.setProfileImageUrl(newUser.getProfileImageUrl());
        existingUser.setCreatedDate(newUser.getCreatedDate());
        existingUser.setProjects(newUser.getProjects());

        return userRepository.save(existingUser);
    }

    public void deleteUser(ObjectId userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User with id " + userId + " does not exist.");
        }
        userRepository.deleteById(userId);
    }

    public User updateUserDisplayName(ObjectId userId, String displayName) {
        User user = getUserById(userId);
        user.setDisplayName(displayName);
        return userRepository.save(user);
    }

    public User updateProfileImageUrl(ObjectId userId, String profileImageUrl) {
        User user = getUserById(userId);
        user.setProfileImageUrl(profileImageUrl);
        return userRepository.save(user);
    }

    @SuppressWarnings("unchecked")
    public User patchUser(ObjectId userId, Map<String, Object> updates) {
        User user = getUserById(userId);
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            switch (field) {
            case "githubUsername":
                user.setGithubUsername((String) value);
                break;
            case "displayName":
                user.setDisplayName((String) value);
                break;
            case "profileImageUrl":
                user.setProfileImageUrl((String) value);
                break;
            case "createdDate":
                user.setCreatedDate((Date) value);
                break;
            case "projects":
                user.setProjects((List<Project>) value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
            }
        }
        return userRepository.save(user);
    }
}
