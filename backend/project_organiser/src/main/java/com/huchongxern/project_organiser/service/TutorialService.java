package com.huchongxern.project_organiser.service;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.model.Tutorial;
import com.huchongxern.project_organiser.repository.ProjectRepository;
import com.huchongxern.project_organiser.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {
    @Autowired
    private TutorialRepository tutorialRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;

    public Optional<Project> getProjectByGithubUrlName(String githubUrl) {
        return projectRepository.findProjectByGithubUrl(githubUrl);
    }

    public List<Tutorial> getTutorialsForProject(String githubUrl) {
        Optional<Project> project = getProjectByGithubUrlName(githubUrl);
        if (project.isPresent()) {
            return project.get().getTutorials();
        }
        throw new RuntimeException("Project not found for given Github URL");
    }

    public Tutorial getTutorialForProjectFromTutorialId(String githubUrl, Integer tutorialId){
        List<Tutorial> tutorialList = getTutorialsForProject(githubUrl);
        for (Tutorial tutorial : tutorialList) {
            if (tutorial.get_id().equals(tutorialId)) {
                System.out.println("FOUND TUTORIAL WITH ID: " + tutorialId);
                return tutorial;
            }
        }
        throw new RuntimeException("Tutorial not found for tutorial id: " + tutorialId);
    }

    public List<Lesson> getLessonsForTutorial(String githubUrl, Integer tutorialId) {
        List<Tutorial> tutorialList = getTutorialsForProject(githubUrl);
        Optional<Tutorial> tutorial = tutorialRepository.findById(tutorialId);
        if (tutorial.isPresent()){
            return tutorial.get().getLessons();
        } else {
            throw new RuntimeException("Tutorial Not Found");
        }
    }
}
