package com.huchongxern.project_organiser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
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
@NoArgsConstructor //creates constructor with no args
public class Project {
    @Id
    private Integer _id;
    private String name;
    private Date start_time;
    private List<String> categories;
    private String github_url;
    private String github_last_commit;
    private String status; // make into enum?
    private Date last_updated;
    @DocumentReference
    private List<Todo> todos;
    @DocumentReference
    private List<Tutorial> tutorials;

    public Project(Integer _id, String name, String github_url, String github_last_commit){
        this._id = _id;
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
