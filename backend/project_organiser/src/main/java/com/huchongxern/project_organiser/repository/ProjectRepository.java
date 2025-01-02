package com.huchongxern.project_organiser.repository;

import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.model.ProjectStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends MongoRepository<Project, Integer> {
    //List<Optional<Project>> findProjectsByStatus(String status);
    Optional<Project> findProjectByName(String name);

    Optional<Project> findProjectByGithubUrl(String github_url);
}
