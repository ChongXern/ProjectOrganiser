package com.huchongxern.project_organiser.model;

import com.huchongxern.project_organiser.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
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

    // no args constructor considering string status
    public Project(){
        this._id = Util.generateId();
        this.categories = new ArrayList<>();
        this.todos = new ArrayList<>();
        this.tutorials = new ArrayList<>();
        this.start_time = Util.getCurrDate();
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
