package com.huchongxern.project_organiser.model;

import com.huchongxern.project_organiser.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
import java.util.Objects;

@Document(collection = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    private ObjectId _id;
    private String desc;
    @Field("created_date")
    private Date createdDate;
    @Field("start_time")
    private Date startTime;
    private Date deadline;
    @Field("is_done")
    private boolean isDone;
    private int priority;

    private void setDefaultDates() {
        LocalDate today = LocalDate.now();
        this.createdDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.startTime = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.deadline = Date.from(today.plusDays(4).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // constructor for setting dates based on today, and sets isDone to false
    public Todo(ObjectId _id, String desc, int priority) {
        this._id = _id;
        this.desc = desc;
        this.priority = priority;

        setDefaultDates();
        this.isDone = false;
    }

    // even more watered down constructor for template item
    public Todo(ObjectId _id){
        this._id = _id;
        setDefaultDates();
        this.isDone = false;
        this.desc = "";
        this.priority = 1;
    }

    /*public Todo() {
        this._id = Util.generateId();
        setDefaultDates();
        this.isDone = false;
        this.text = "";
        this.priority = 1;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo)) return false;
        Todo todo = (Todo)o;
        return Objects.equals(this._id, todo._id) && Objects.equals(this.desc, todo.desc); //consider nlp
    }
}
