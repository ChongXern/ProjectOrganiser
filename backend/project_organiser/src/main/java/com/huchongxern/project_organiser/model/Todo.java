package com.huchongxern.project_organiser.model;

import com.huchongxern.project_organiser.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;

@Document(collection = "todos")
@Data
//@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    private Integer _id;
    private String text;
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
    public Todo(Integer _id, String text, int priority) {
        this._id = _id;
        this.text = text;
        this.priority = priority;

        setDefaultDates();
        this.is_done = false;
    }

    // even more watered down constructor for template item
    public Todo(Integer _id){
        this._id = _id;
        setDefaultDates();
        this.is_done = false;
        this.text = "";
        this.priority = 1;
    }

    public Todo() {
        this._id = Util.generateId();
        setDefaultDates();
        this.is_done = false;
        this.text = "";
        this.priority = 1;
    }
}
