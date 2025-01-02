package com.huchongxern.project_organiser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

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
    @Field("github_url")
    private String githubUrl;
    private String github_last_commit;
    private String status;
    private Date last_updated;
    @DocumentReference
    private List<Todo> todos;
    @DocumentReference
    private List<Tutorial> tutorials;

    /*@PersistenceCreator
    public Project(Integer _id, String name, Date start_time, List<String> categories, String githubUrl, String github_last_commit, String status, Date last_updated, List<Todo> todos, List<Tutorial> tutorials) {
        this._id = _id;
        this.name = name;
        this.start_time = start_time;
        this.categories = categories;
        this.githubUrl = githubUrl;
        this.github_last_commit = github_last_commit;
        this.status = status;
        this.last_updated = last_updated;
        this.todos = todos;
        this.tutorials = tutorials;
    }

    public String parseRepoName(String githubUrl) {
        String[] splitItems = githubUrl.split("/");
        return splitItems[splitItems.length - 1];
    }*/

    // no args constructor considering string status
    public Project(){
        this.categories = new ArrayList<>();
        this.todos = new ArrayList<>();
        this.tutorials = new ArrayList<>();
        LocalDate today = LocalDate.now();
        this.start_time = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.last_updated = this.start_time;
        this.status = "NOT_STARTED";
    }

    public Project(Integer _id, String name, String githubUrl, String github_last_commit) {
        this._id = _id;
        this.name = name;
        this.githubUrl = githubUrl;
        this.github_last_commit = github_last_commit;

        LocalDate today = LocalDate.now();
        this.start_time = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.last_updated = this.start_time;
        this.status = "NOT_STARTED";
        this.todos = new ArrayList<>();
        this.tutorials = new ArrayList<>();
        this.categories = new ArrayList<>();
    }
}
