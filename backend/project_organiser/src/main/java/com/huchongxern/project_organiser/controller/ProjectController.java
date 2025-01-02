package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.service.ProjectService;
import com.huchongxern.project_organiser.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<List<Project>>(projectService.allProjects(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Integer id){
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/tutorials")
    public ResponseEntity<List<Tutorial>> getTutorials(@PathVariable Integer id) {
        return new ResponseEntity<>(projectService.getTutorialsForProject(id), HttpStatus.OK);
    }

    @GetMapping("/{projectId}/tutorials/{tutorialId}/lessons")
    public ResponseEntity<List<Lesson>> getLessons(@PathVariable Integer projectId, @PathVariable Integer tutorialId) {
        return new ResponseEntity<>(projectService.getLessonsForTutorial(projectId, tutorialId), HttpStatus.OK);
    }
}
