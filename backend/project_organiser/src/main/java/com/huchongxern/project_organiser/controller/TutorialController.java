package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.service.TutorialService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import com.huchongxern.project_organiser.model.Tutorial;
import com.huchongxern.project_organiser.model.Lesson;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/tutorials")
public class TutorialController {
    @Autowired
    private TutorialService tutorialService;

    private void validateTutorial(ObjectId tutorialId) {
        if (!tutorialService.existsById(tutorialId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutorial not found.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Tutorial>> getAllTutorials(){
        List<Tutorial> allTutorials = tutorialService.getAllTutorials();
        return new ResponseEntity<>(allTutorials, HttpStatus.OK);
    }

    @GetMapping("/{tutorialId}")
    public ResponseEntity<Tutorial> getTutorial(@PathVariable ObjectId tutorialId) {
        validateTutorial(tutorialId);
        return new ResponseEntity<>(tutorialService.getTutorialFromTutorialId(tutorialId), HttpStatus.OK);
    }

    @GetMapping("/{tutorialId}/lessons")
    public ResponseEntity<List<Lesson>> getLessonsForTutorial(@PathVariable ObjectId tutorialId) {
        validateTutorial(tutorialId);
        return new ResponseEntity<>(tutorialService.getLessonsForTutorial(tutorialId), HttpStatus.OK);
    }

    @PostMapping("/createTutorial")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        return new ResponseEntity<>(tutorialService.createTutorial(tutorial), HttpStatus.OK);
    }

    @PutMapping("/updateTutorial/{tutorialId}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable ObjectId tutorialId,
                                                   @RequestBody Tutorial newTutorial) {
        return new ResponseEntity<>(tutorialService.updateTutorial(tutorialId, newTutorial), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTutorial/{tutorialId}")
    public ResponseEntity<Tutorial> deleteTutorial(@PathVariable ObjectId tutorialId) {
        tutorialService.deleteTutorial(tutorialId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updateTutorial/any/{tutorialId}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable ObjectId tutorialId,
                                                   @RequestBody Map<String, Object> updates) {
        try {
            return new ResponseEntity<>(tutorialService.patchTutorial(tutorialId, updates), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/updateTutorial/name/{tutorialId}")
    public ResponseEntity<Tutorial> updateTutorialName(@PathVariable ObjectId tutorialId, @RequestParam String name) {
        return new ResponseEntity<>(tutorialService.updateTutorialName(tutorialId, name), HttpStatus.OK);
    }

    @PatchMapping("/updateTutorial/tutorialUrl/{tutorialId}")
    public ResponseEntity<Tutorial> updateTutorialTutorialUrl(@PathVariable ObjectId tutorialId,
                                                             @RequestParam String tutorialUrl) {
        return new ResponseEntity<>(tutorialService.updateTutorialTutorialUrl(tutorialId, tutorialUrl), HttpStatus.OK);
    }

    @PatchMapping("/updateTutorial/isDone/{tutorialId}")
    public ResponseEntity<Tutorial> updateTutorialIsDone(@PathVariable ObjectId tutorialId,
                                                         @RequestParam boolean isDone) {
        return new ResponseEntity<>(tutorialService.updateTutorialIsDone(tutorialId, isDone), HttpStatus.OK);
    }

    @PatchMapping("/updateTutorial/category/{tutorialId}")
    public ResponseEntity<Tutorial> updateTutorialCategory(@PathVariable ObjectId tutorialId,
                                                           @RequestParam String category) {
        return new ResponseEntity<>(tutorialService.updateTutorialCategory(tutorialId, category), HttpStatus.OK);
    }
}
