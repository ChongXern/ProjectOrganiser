package com.huchongxern.project_organiser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "projects")
@Data //Takes care of getters, setters and to string
@AllArgsConstructor //uses all args to create constructor
@NoArgsConstructor //creates constructor with no args
public class Project {
    @Id
    private ObjectId id;
    private String name;
    private Date start_time;
    private List<String> categories;
    private String github_url;
    private String github_last_commit;
    private String status; // make into enum?
    private Date last_updated;
    private List<Todo> todos;
    private List<Tutorial> tutorials;

    public Project(ObjectId id, String name, String github_url, String github_last_commit, Date last_updated, List<Todo> todos, List<Tutorial> tutorials){
        this.id = id;
        this.name = name;
        this.github_url = github_url;
        this.github_last_commit = github_last_commit;

        LocalDate today = LocalDate.now();
        this.start_time = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.last_updated = this.start_time;
        this.status = "Not Started";
        this.todos = new ArrayList<>();
        this.tutorials = new ArrayList<>();
        this.categories = new ArrayList<>();
    }
}
