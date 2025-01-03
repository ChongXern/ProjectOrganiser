package com.huchongxern.project_organiser.model;

import com.huchongxern.project_organiser.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "lessons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    private ObjectId _id;
    private String lesson_learnt;
    private String application;
    private Date created_date;

    // constructor for automatically creating template Lesson
    public Lesson(ObjectId _id) {
        this._id = _id;
        this.lesson_learnt = "";
        this.application = "";
        this.created_date = Util.getCurrDate();
    }
}