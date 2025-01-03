package com.huchongxern.project_organiser.model;

import com.huchongxern.project_organiser.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;

@Document(collection = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    private ObjectId _id;
    private String desc;
    private Date created_date;
    private Date start_time;
    private Date deadline;
    private boolean is_done;
    private int priority;

    private void setDefaultDates() {
        LocalDate today = LocalDate.now();
        this.created_date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.start_time = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.deadline = Date.from(today.plusDays(4).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // constructor for setting dates based on today, and sets is_done to false
    public Todo(ObjectId _id, String desc, int priority) {
        this._id = _id;
        this.desc = desc;
        this.priority = priority;

        setDefaultDates();
        this.is_done = false;
    }

    // even more watered down constructor for template item
    public Todo(ObjectId _id){
        this._id = _id;
        setDefaultDates();
        this.is_done = false;
        this.desc = "";
        this.priority = 1;
    }

    /*public Todo() {
        this._id = Util.generateId();
        setDefaultDates();
        this.is_done = false;
        this.text = "";
        this.priority = 1;
    }*/
}
