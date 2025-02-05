package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.service.LessonService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> allLessons = lessonService.getAllLesssons();
        return new ResponseEntity<>(allLessons, HttpStatus.OK);
    }

    // single post method
    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Map<String, String> payload){
        String lessonLearnt = payload.get("lesson_learnt");
        String application = payload.get("application");
        Lesson lesson = lessonService.createLesson(lessonLearnt, application);
        return new ResponseEntity<Lesson>(lesson, HttpStatus.CREATED);
    }

    @PutMapping("/updateLesson/{lessonId}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable ObjectId lessonId, @RequestBody Lesson newLesson){
        return new ResponseEntity<>(lessonService.updateLesson(lessonId, newLesson), HttpStatus.OK);
    }

    @DeleteMapping("/deleteLesson/{lessonId}")
    public ResponseEntity<Lesson> deleteLesosn(@PathVariable ObjectId lessonId) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.noContent().build();
    }
}
