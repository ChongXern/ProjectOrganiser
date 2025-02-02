package com.huchongxern.project_organiser.repository;

import com.huchongxern.project_organiser.model.Tutorial;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorialRepository extends MongoRepository<Tutorial, ObjectId> {
}
