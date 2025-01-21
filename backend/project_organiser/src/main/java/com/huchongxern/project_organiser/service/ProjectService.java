package com.huchongxern.project_organiser.service;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.model.Todo;
import com.huchongxern.project_organiser.model.Tutorial;
import com.huchongxern.project_organiser.repository.LessonRepository;
import com.huchongxern.project_organiser.repository.ProjectRepository;
import com.huchongxern.project_organiser.repository.TutorialRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.huchongxern.project_organiser.utils.Util.getCurrDate;

@Service
public class ProjectService {
    @Autowired //lets framework know to instantiate repo class
    private ProjectRepository projectRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TodoService todoService;
    @Autowired
    private TutorialService tutorialService;

    private Project fetchProjectOrThrow(ObjectId id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + id));
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
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
            throw new RuntimeException("Project not found with ID: " + id);
        }
        projectRepository.deleteById(id);
    }

    @SuppressWarnings("unchecked")
    public Project patchProject(ObjectId projectId, Map<String, Object> updates) {
        Project project = fetchProjectOrThrow(projectId);

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            switch (field) {
                case "name":
                    project.setName((String) value);
                    break;
                case "github_last_commit":
                    project.setGithub_last_commit((String) value);
                    break;
                case "status":
                    String status = (String)value;
                    String newStatus = status.replace(' ', '_');
                    project.setStatus(newStatus.toUpperCase());
                    break;
                case "last_updated":
                    project.setLast_updated(getCurrDate()); // assume updating to now
                    break;
                case "todos":
                    project.setTodos((List<Todo>) value); // assume cast is safe, suppress warning
                    break;
                case "tutorials":
                    project.setTutorials((List<Tutorial>) value); // assume cast is safe
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + field);
            }
        }
        return projectRepository.save(project);
    }

    public Project updateProjectName(ObjectId projectId, String name) {
        Project project = fetchProjectOrThrow(projectId);

        project.setName(name);
        return projectRepository.save(project);
    }

    public Project updateProjectGithub_last_commit(ObjectId projectId, String github_last_commit) {
        Project project = fetchProjectOrThrow(projectId);
        project.setGithub_last_commit(github_last_commit);
        return projectRepository.save(project);
    }

    public Project updateProjectStatus(ObjectId projectId, String status) {
        Project project = fetchProjectOrThrow(projectId);
        project.setStatus(status);
        return projectRepository.save(project);
    }

    public Project updateProjectLastUpdated(ObjectId projectId) { // assume changing last updated to now
        Project project = fetchProjectOrThrow(projectId);
        project.setLast_updated(getCurrDate());
        return projectRepository.save(project);
    }

    public Project updateProjectTodos(ObjectId projectId, List<Todo> todos) {
        Project project = fetchProjectOrThrow(projectId);
        project.setTodos(todos);
        return projectRepository.save(project);
    }

    public Project updateProjectTutorials(ObjectId projectId, List<Tutorial> tutorials) {
        Project project = fetchProjectOrThrow(projectId);
        project.setTutorials(tutorials);
        return projectRepository.save(project);
    }
}
