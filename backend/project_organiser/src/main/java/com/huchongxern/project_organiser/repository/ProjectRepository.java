package com.huchongxern.project_organiser.repository;

import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.model.ProjectStatus;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends MongoRepository<Project, ObjectId> {

    //List<Optional<Project>> findProjectsByStatus(String status);
    Optional<Project> findProjectByName(String name);

    Optional<Project> findProjectByGithubUrl(String github_url);

    @NotNull
    List<Project> findAll(@NotNull Sort sort);

    //Optional<List<Project>> findProjectByCategory(String category);
}
