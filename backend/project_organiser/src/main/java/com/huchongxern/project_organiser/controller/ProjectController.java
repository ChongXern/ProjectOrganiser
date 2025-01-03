package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.service.ProjectService;
import com.huchongxern.project_organiser.model.Tutorial;
import com.huchongxern.project_organiser.service.TutorialService;
import com.huchongxern.project_organiser.utils.Util;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TutorialService tutorialService;

    private ResponseEntity<String> notFoundResponse(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    private String findGithubUrlFromRepoName(String repoName) {
        List<Project> projects = projectService.allProjects();
        // assume that no two projects share the same name
        for (Project project : projects) {
            if (project != null) {
                String githubUrl = project.getGithubUrl();
                if (githubUrl.toLowerCase().contains(repoName.toLowerCase())) {
                    //System.out.println("GITHUB URL FOR " + repoName + " IS " + githubUrl);
                    return githubUrl;
                }
            }
        }
        throw new RuntimeException("Unable to find project with name " + repoName);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> allProjects = projectService.allProjects();
        return ResponseEntity.ok(allProjects);
        //return new ResponseEntity<List<Project>>(projectService.allProjects(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer id){
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping("/{repoName}")
    public ResponseEntity<Optional<Project>> getProjectByRepoName(@PathVariable String repoName) {
        String fullGithubUrl = findGithubUrlFromRepoName(repoName);
        Util.terminal("ls");
        //String fullGithubUrl2 = "https://github.com/ChongXern/" + repoName; // adjust to consider other usernames
        return new ResponseEntity<>(tutorialService.getProjectByGithubUrlName(fullGithubUrl), HttpStatus.OK);
    }

    @GetMapping("/{repoName}/tutorials")
    public ResponseEntity<List<Tutorial>> getTutorials(@PathVariable String repoName) {
        String githubUrl = findGithubUrlFromRepoName(repoName);
        return new ResponseEntity<>(tutorialService.getTutorialsForProject(githubUrl), HttpStatus.OK);
    }

    @GetMapping("/{repoName}/tutorials/{tutorialId}")
    public ResponseEntity<Tutorial> getTutorial(@PathVariable String repoName, @PathVariable Integer tutorialId) {
        String githubUrl = findGithubUrlFromRepoName(repoName);
        return new ResponseEntity<>(tutorialService.getTutorialForProjectFromTutorialId(githubUrl, tutorialId),
                HttpStatus.OK);
    }

    @GetMapping("/{repoName}/tutorials/{tutorialId}/lessons")
    public ResponseEntity<List<Lesson>> getLessons(@PathVariable String repoName, @PathVariable Integer tutorialId) {
        String githubUrl = findGithubUrlFromRepoName(repoName);
        return new ResponseEntity<>(tutorialService.getLessonsForTutorial(githubUrl, tutorialId), HttpStatus.OK);
    }
}
