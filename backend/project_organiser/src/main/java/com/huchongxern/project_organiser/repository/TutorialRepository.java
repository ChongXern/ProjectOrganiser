package com.huchongxern.project_organiser.repository;

import com.huchongxern.project_organiser.model.Tutorial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends MongoRepository<Tutorial, Integer> {
}
