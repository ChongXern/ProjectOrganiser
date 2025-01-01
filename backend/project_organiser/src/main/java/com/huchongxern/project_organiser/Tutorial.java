package com.huchongxern.project_organiser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Document(collection = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {
    @Id
    private Integer _id;
    private String name; // attempt web crawling?
    private String tutorial_url;
    private boolean is_done;
    private String category; // should be made into enum?
    private Date created_date;
    @DocumentReference
    private List<Lesson> lessons;

    private void setDefaultDate(){
        LocalDate today = LocalDate.now();
        this.created_date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Tutorial(Integer _id, String name, String tutorial_url) {
        this._id = _id;
        this.name = name;
        this.tutorial_url = tutorial_url;
        this.category = "";
        this.lessons = new ArrayList<>();
        this.is_done = false;
        setDefaultDate();
    }

    public Tutorial(Integer _id){
        this._id = _id;
        this.name = "";
        this.tutorial_url = "";
        this.category = "";
        this.lessons = new ArrayList<>();
        this.is_done = false;
        setDefaultDate();
    }
}
