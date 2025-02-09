package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.service.LessonService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        return new ResponseEntity<>(lesson, HttpStatus.CREATED);
    }

    @PutMapping("/updateLesson/{lessonId}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable ObjectId lessonId, @RequestBody Lesson newLesson){
        return new ResponseEntity<>(lessonService.updateLesson(lessonId, newLesson), HttpStatus.OK);
    }

    @DeleteMapping("/deleteLesson/{lessonId}")
    public ResponseEntity<Lesson> deleteLesson(@PathVariable ObjectId lessonId) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updateLesson/lessonLearnt/{lessonId}")
    public ResponseEntity<Lesson> updateLessonLessonLearnt(@PathVariable ObjectId lessonId,
                                                           @RequestParam String lessonLearnt) {
        return new ResponseEntity<>(lessonService.updateLessonLessonLearnt(lessonId, lessonLearnt), HttpStatus.OK);
    }

    @PatchMapping("/updateLesson/application/{lessonId}")
    public ResponseEntity<Lesson> updateLessonApplication(@PathVariable ObjectId lessonId,
                                                          @RequestParam String application) {
        return new ResponseEntity<>(lessonService.updateLessonApplication(lessonId, application), HttpStatus.OK);
    }

    @PatchMapping("/updateLesson/createdDate/{lessonId}")
    public ResponseEntity<Lesson> updateLessonCreatedDate(@PathVariable ObjectId lessonId,
                                                          @RequestParam Date createdDate) {
        return new ResponseEntity<>(lessonService.updateLessonCreatedDate(lessonId, createdDate), HttpStatus.OK);
    }

    @PatchMapping("/updateLesson/any/{lessonId}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable ObjectId lessonId,
                                               @RequestBody Map<String, Object> updates) {
        try {
            return new ResponseEntity<>(lessonService.patchLesson(lessonId, updates), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
