package com.huchongxern.project_organiser.model;

import com.huchongxern.project_organiser.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "lessons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    private ObjectId _id;
    @Field("lesson_learnt")
    private String lessonLearnt;
    private String application;
    @Field("created_date")
    private Date createdDate;

    // constructor for automatically creating template Lesson
    public Lesson(ObjectId _id) {
        this._id = _id;
        this.lessonLearnt = "";
        this.application = "";
        this.createdDate = Util.getCurrDate();
    }

    public Lesson(String lessonLearnt, String application) {
        this._id = new ObjectId();
        this.lessonLearnt = lessonLearnt;
        this.application = application;
        this.createdDate = Util.getCurrDate();
    }

    public void set_createdDate() {
        createdDate = Util.getCurrDate();
    }
}