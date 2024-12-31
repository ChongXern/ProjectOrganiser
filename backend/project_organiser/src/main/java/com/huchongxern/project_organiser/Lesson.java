package com.huchongxern.project_organiser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Document(collection = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    private ObjectId id;
    private String lesson_learnt;
    private String application;
    private Date created_date;

    // constructor for automatically creating template Lesson
    public Lesson(ObjectId id) {
        this.id = id;
        this.lesson_learnt = "";
        this.application = "";
        LocalDate today = LocalDate.now();
        this.created_date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}