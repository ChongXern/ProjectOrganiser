package com.huchongxern.project_organiser.model;

import com.huchongxern.project_organiser.utils.Util;
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

@Document(collection = "tutorials")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {
    @Id
    private ObjectId _id;
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

    public Tutorial(ObjectId _id, String name, String tutorial_url) {
        this._id = _id;
        this.name = name;
        this.tutorial_url = tutorial_url;
        this.category = "";
        this.lessons = new ArrayList<>();
        this.is_done = false;
        setDefaultDate();
    }

    public Tutorial(ObjectId _id){
        this._id = _id;
        this.name = "";
        this.tutorial_url = "";
        this.category = "";
        this.lessons = new ArrayList<>();
        this.is_done = false;
        setDefaultDate();
    }

    /*public Tutorial(){
        this._id = Util.generateId();
        this.name = "";
        this.
    }*/
}
