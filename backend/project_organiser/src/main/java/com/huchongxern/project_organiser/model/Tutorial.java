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

@Document(collection = "tutorials")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {
    @Id
    private ObjectId _id;
    private String name; // attempt web crawling?
    @Field("tutorial_url")
    private String tutorialUrl;
    @Field("is_done")
    private boolean isDone;
    private String category; // should be made into enum?
    @Field("created_date")
    private Date createdDate;
    @DocumentReference
    private List<Lesson> lessons;

    private void setDefaultDate(){
        LocalDate today = LocalDate.now();
        this.createdDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Tutorial(ObjectId _id, String name, String tutorialUrl) {
        this._id = _id;
        this.name = name;
        this.tutorialUrl = tutorialUrl;
        this.category = "";
        this.lessons = new ArrayList<>();
        this.isDone = false;
        setDefaultDate();
    }

    public Tutorial(ObjectId _id){
        this._id = _id;
        this.name = "";
        this.tutorialUrl = "";
        this.category = "";
        this.lessons = new ArrayList<>();
        this.isDone = false;
        setDefaultDate();
    }
}
