package com.huchongxern.project_organiser.service;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.model.Tutorial;
import com.huchongxern.project_organiser.repository.LessonRepository;
import com.huchongxern.project_organiser.repository.ProjectRepository;
import com.huchongxern.project_organiser.repository.TutorialRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired //lets framework know to instantiate repo class
    private ProjectRepository projectRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Project> allProjects() {
        return projectRepository.findAll();
    }

    // Business logic to fetch a project by its ID
    public Project getProjectById(ObjectId id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Optional<Project> getProjectByName(String name) {
        return projectRepository.findProjectByName(name);
    }

    public List<Project> getProjectsByStatus(String status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(status));
        return mongoTemplate.find(query, Project.class);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(ObjectId id, Project newProject) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + id));

        // set new attributes one by one
        existingProject.setName(newProject.getName());
        existingProject.setStart_time(newProject.getStart_time());
        existingProject.setCategories(newProject.getCategories());
        existingProject.setGithubUrl(newProject.getGithubUrl());
        existingProject.setGithub_last_commit(newProject.getGithub_last_commit());
        existingProject.setStatus(newProject.getStatus());
        existingProject.setLast_updated(newProject.getLast_updated());
        existingProject.setTodos(newProject.getTodos());
        existingProject.setTutorials(newProject.getTutorials());

        return projectRepository.save(existingProject);
    }

    public void deleteProject(ObjectId id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found with ID: " + id)
        }
        projectRepository.deleteById(id);
    }
}
