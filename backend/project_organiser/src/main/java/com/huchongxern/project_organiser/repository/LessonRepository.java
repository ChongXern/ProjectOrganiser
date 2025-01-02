package com.huchongxern.project_organiser.repository;

import com.huchongxern.project_organiser.model.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends MongoRepository<Lesson, Integer> {
}
