package com.huchongxern.project_organiser;

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
    public Project getProject(@PathVariable Integer id){
        return projectService.getProjectById(id);
    }

    @GetMapping("/{id}/tutorials")
    public List<Tutorial> getTutorials(@PathVariable Integer id) {
        return projectService.getTutorialsForProject(id);
    }

    @GetMapping("/{projectId}/tutorials/{tutorialId}/lessons")
    public List<Lesson> getLessons(@PathVariable Integer projectId, @PathVariable Integer tutorialId) {
        return projectService.getLessonsForTutorial(projectId, tutorialId);
    }
}
