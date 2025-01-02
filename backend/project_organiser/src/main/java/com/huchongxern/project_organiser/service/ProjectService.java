package com.huchongxern.project_organiser.service;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.model.Tutorial;
import com.huchongxern.project_organiser.repository.LessonRepository;
import com.huchongxern.project_organiser.repository.ProjectRepository;
import com.huchongxern.project_organiser.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired //lets framework know to instantiate repo class
    private ProjectRepository projectRepository;

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private LessonRepository lessonRepository;


    public List<Project> allProjects() {
        return projectRepository.findAll();
    }

    // Business logic to fetch a project by its ID
    public Project getProjectById(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Optional<Project> getProjectByName(String name) {
        return projectRepository.findProjectByName(name);
    }

    public List<Tutorial> getTutorialsForProject(Integer id) {
        Project project = getProjectById(id);
        //List<Tutorial> tutorials = tutorialRepository.findAllById(project.getTutorials());
        return project.getTutorials();
    }

    public List<Lesson> getLessonsForTutorial(Integer projectId, Integer tutorialId) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(tutorialId);
        if (tutorial.isPresent()){
            return tutorial.get().getLessons();
        } else {
            throw new RuntimeException("Tutorial Not Found");
        }
    }

    public Optional<Project> getProjectByGithubUrlName(String githubUrl) {
        return projectRepository.findProjectByGithubUrl(githubUrl);
    }
}
