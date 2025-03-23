package com.huchongxern.project_organiser.model;

import com.huchongxern.project_organiser.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Document(collection = "projects")
@Data //Takes care of getters, setters and to string
@AllArgsConstructor //uses all args to create constructor
@NoArgsConstructor //creates constructor with no args
public class Project {
    @Id
    @JsonSerialize(using = ToStringSerializer.class) // forces json serialisation as string
    private ObjectId _id;
    private String name;
    @Field("start_time")
    private Date startTime;
    private List<String> categories;
    @Field("github_url")
    private String githubUrl;
    @Field("github_user")
    private String githubUsername;
    @Field("github_last_commit")
    private String githubLastCommit;
    private String status;
    @Field("last_updated")
    private Date lastUpdated;
    @DocumentReference
    private List<Todo> todos;
    @DocumentReference
    private List<Tutorial> tutorials;
    private String image;

    // no args constructor considering string status
    /*public Project(){
        this._id = Util.generateId();
        this.categories = new ArrayList<>();
        this.todos = new ArrayList<>();
        this.tutorials = new ArrayList<>();
        this.startTime = Util.getCurrDate();
        this.lastUpdated = this.startTime;
        this.status = "NOT_STARTED";
    }*/

    public Project(ObjectId _id, String name, String githubUsername, String githubUrl,
                   String githubLastCommit) {
        this._id = _id;
        this.name = name;
        this.githubUrl = githubUrl;
        this.githubUsername = githubUsername;
        this.githubLastCommit = githubLastCommit;

        LocalDate today = LocalDate.now();
        this.startTime = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.lastUpdated = this.startTime;
        this.status = "NOT_STARTED";
        this.todos = new ArrayList<>();
        this.tutorials = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.image = "";
    }
}
