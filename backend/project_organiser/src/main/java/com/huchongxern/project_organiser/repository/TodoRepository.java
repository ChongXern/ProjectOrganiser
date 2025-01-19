package com.huchongxern.project_organiser.repository;

import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.model.Todo;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<Todo, ObjectId> {
    Optional<List<Todo>> findTodoByIs_done(boolean is_done);
    @NotNull
    List<Todo> findAll(@NotNull Sort sort);
}
