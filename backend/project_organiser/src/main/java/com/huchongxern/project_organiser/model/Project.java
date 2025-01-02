package com.huchongxern.project_organiser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "projects")
@Data //Takes care of getters, setters and to string
@AllArgsConstructor //uses all args to create constructor
//@NoArgsConstructor //creates constructor with no args
public class Project {
    @Id
    private Integer _id;
    private String name;
    private Date start_time;
    private List<String> categories;
    private String github_url;
    private String github_last_commit;
    //private String status; // make into enum?
    private ProjectStatus status;
    private Date last_updated;
    @DocumentReference
    private List<Todo> todos;
    @DocumentReference
    private List<Tutorial> tutorials;

    private String repoName;

    public String parseRepoName(String github_url) {
        String[] splitItems = github_url.split("/");
        return splitItems[splitItems.length - 1];
    }

    // no args constructor considering string status
    public Project(){
        this.categories = new ArrayList<>();
        this.todos = new ArrayList<>();
        this.tutorials = new ArrayList<>();
        LocalDate today = LocalDate.now();
        this.start_time = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.last_updated = this.start_time;
        this.status = ProjectStatus.NOT_STARTED;
    }

    /*@PersistenceCreator
    // all args constructor considering string status
    public Project(Integer _id, String name, Date start_time, List<String> categories, String github_url, String github_last_commit, String status, Date last_updated, List<Todo> todos, List<Tutorial> tutorials) {
        this._id = _id;
        this.name = name;
        this.start_time = start_time;
        this.categories = categories;
        this.github_url = github_url;
        this.github_last_commit = github_last_commit;

        // Safely handle invalid statuses
        if (status != null) {
            System.out.println("Status IS NOT NULL AMAMAMA");
            try {
                this.status = ProjectStatus.valueOf(status);
            } catch (IllegalArgumentException e) {
                this.status = ProjectStatus.NOT_STARTED;  // Default if status is invalid
                System.err.println("Invalid status value: " + status);  // Log the invalid value
            }
        } else {
            System.out.println("Status IS NOT NULL AMAMAMA");
            this.status = ProjectStatus.NOT_STARTED;  // Default to 'NOT_STARTED' if status is null
        }

        this.last_updated = last_updated;
        this.todos = todos;
        this.tutorials = tutorials;
        this.repoName = parseRepoName(github_url);
    }
    
     */

    public Project(Integer _id, String name, String github_url, String github_last_commit) {
        this._id = _id;
        this.name = name;
        this.github_url = github_url;
        this.github_last_commit = github_last_commit;

        LocalDate today = LocalDate.now();
        this.start_time = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.last_updated = this.start_time;
        this.status = ProjectStatus.NOT_STARTED;
        this.todos = new ArrayList<>();
        this.tutorials = new ArrayList<>();
        this.categories = new ArrayList<>();

        this.repoName = parseRepoName(github_url);
    }
}
