package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.service.ProjectService;
import com.huchongxern.project_organiser.service.TutorialService;
import com.huchongxern.project_organiser.service.TodoService;
import com.huchongxern.project_organiser.model.Tutorial;
import com.huchongxern.project_organiser.utils.Util;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
Resources operations corresponding to CRUD:
1. POST to create new resources in db
2. GET to read or retrieve resources from db
3. PUT to update existing resources or completely replace a resource
4. PATCH to partially update resource
5. DELETE to delete resource from db via id
 */

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    private ResponseEntity<String> notFoundResponse(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    private String findGithubUrlFromRepoName(String repoName) {
        List<Project> projects = projectService.getAllProjects();
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

    private void validateTutorialBelongsToProject(String repoName, ObjectId tutorialId) {
        String githubUrl = findGithubUrlFromRepoName(repoName);
        if (!projectService.doesProjectContainTutorial(githubUrl, tutorialId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutorial not found in this project.");
        }
    }

    // GET mappings
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> allProjects = projectService.getAllProjects();
        return ResponseEntity.ok(allProjects);
        //return new ResponseEntity<List<Project>>(projectService.allProjects(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable ObjectId id){
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping("/{repoName}")
    public ResponseEntity<Optional<Project>> getProjectByRepoName(@PathVariable String repoName) {
        String fullGithubUrl = findGithubUrlFromRepoName(repoName);
        Util.terminal("ls");
        return new ResponseEntity<>(projectService.getProjectByGithubUrlName(fullGithubUrl), HttpStatus.OK);
        //String fullGithubUrl2 = "https://github.com/ChongXern/" + repoName; // adjust to consider other usernames
        //return new ResponseEntity<>(tutorialService.getProjectByGithubUrlName(fullGithubUrl), HttpStatus.OK);
    }

    @GetMapping("/{repoName}/tutorials")
    public ResponseEntity<List<Tutorial>> getTutorials(@PathVariable String repoName) {
        String githubUrl = findGithubUrlFromRepoName(repoName);
        return new ResponseEntity<>(projectService.getTutorialsForProject(githubUrl), HttpStatus.OK);
    }

    // POST (Creating a resource)
    @PostMapping("/createProject")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return new ResponseEntity<>(projectService.createProject(project), HttpStatus.CREATED);
    }

    // PUT (Putting a resource)
    @PutMapping("/updateProject/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable ObjectId projectId, @RequestBody Project newProject) {
        return new ResponseEntity<>(projectService.updateProject(projectId, newProject), HttpStatus.OK);
    }

    // DELETE (deleting resource)
    @DeleteMapping("/deleteProject/{projectId}")
    public ResponseEntity<Project> deleteProject(@PathVariable ObjectId projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    // PATCH (Update partially)
    @PatchMapping("/updateProject/name/{projectId}")
    public ResponseEntity<Project> updateProjectName(@PathVariable ObjectId projectId, @RequestParam String name) {
        return new ResponseEntity<>(projectService.updateProjectName(projectId, name), HttpStatus.OK);
    }

    @PatchMapping("/updateProject/status/{projectId}")
    public ResponseEntity<Project> updateProjectStatus(@PathVariable ObjectId projectId, @RequestParam String status){
        return new ResponseEntity<>(projectService.updateProjectStatus(projectId, status), HttpStatus.OK);
    }

    @PatchMapping("/updateProject/last_updated/{projectId}")
    public ResponseEntity<Project> updateProjectLastUpdated(@PathVariable ObjectId projectId) {
        return new ResponseEntity<>(projectService.updateProjectLastUpdated(projectId), HttpStatus.OK);
    }

    @PatchMapping("/updateProject/any/{projectId}") // remember to document key and val formats
    public ResponseEntity<Project> updateProject(@PathVariable ObjectId projectId,
                                                 @RequestBody Map<String, Object> updates){
        return new ResponseEntity<>(projectService.patchProject(projectId, updates), HttpStatus.OK);
    }
}
