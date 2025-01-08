package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/projects")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    // single post method
    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Map<String, String> payload){
        String lessonLearnt = payload.get("lesson_learnt");
        String application = payload.get("application");
        Lesson lesson = lessonService.createLesson(lessonLearnt, application);
        return new ResponseEntity<Lesson>(lesson, HttpStatus.CREATED);
    }

}
