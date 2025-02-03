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
}
