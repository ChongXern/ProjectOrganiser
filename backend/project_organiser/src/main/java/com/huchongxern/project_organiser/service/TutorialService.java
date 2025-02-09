package com.huchongxern.project_organiser.service;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.model.Tutorial;
import com.huchongxern.project_organiser.repository.ProjectRepository;
import com.huchongxern.project_organiser.repository.TutorialRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TutorialService {
    @Autowired
    private TutorialRepository tutorialRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Tutorial getTutorialFromTutorialId(ObjectId tutorialId) {
        return tutorialRepository.findById(tutorialId)
                .orElseThrow(() -> new RuntimeException("Tutorial not found with ID: " + tutorialId));
    }

    public List<Lesson> getLessonsForTutorial(ObjectId tutorialId) {
        Optional<Tutorial> tutorial = tutorialRepository.findById(tutorialId);
        if (tutorial.isPresent()){
            return tutorial.get().getLessons();
        } else {
            throw new RuntimeException("Tutorial Not Found");
        }
    }

    public boolean existsById(ObjectId tutorialId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(tutorialId));
        return mongoTemplate.exists(query, Tutorial.class);
    }

    public Tutorial createTutorial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }

    private Tutorial fetchTutorialOrThrow(ObjectId id) {
        return tutorialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutorial not found with ID: " + id));
    }

    public Tutorial updateTutorial(ObjectId tutorialId, Tutorial newTutorial) {
        Tutorial existingTutorial = fetchTutorialOrThrow(tutorialId);

        existingTutorial.setName(newTutorial.getName());
        existingTutorial.setTutorialUrl(newTutorial.getTutorialUrl());
        existingTutorial.setDone(newTutorial.isDone());
        existingTutorial.setCategory(newTutorial.getCategory());
        existingTutorial.setCreatedDate(newTutorial.getCreatedDate());
        existingTutorial.setLessons(newTutorial.getLessons());

        return tutorialRepository.save(existingTutorial);
    }

    public void deleteTutorial(ObjectId tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new RuntimeException("Tutorial item not found with ID: " + tutorialId);
        }
        tutorialRepository.deleteById(tutorialId);
    }

    public Tutorial updateTutorialName(ObjectId id, String name){
        Tutorial tutorial = fetchTutorialOrThrow(id);
        tutorial.setName(name);
        return tutorialRepository.save(tutorial);
    }

    public Tutorial updateTutorialTutorialUrl(ObjectId id, String tutorialUrl) {
        Tutorial tutorial = fetchTutorialOrThrow(id);
        tutorial.setTutorialUrl(tutorialUrl);
        return tutorialRepository.save(tutorial);
    }

    public Tutorial updateTutorialIsDone(ObjectId id, boolean isDone) {
        Tutorial tutorial = fetchTutorialOrThrow(id);
        tutorial.setDone(isDone);
        return tutorialRepository.save(tutorial);
    }

    public Tutorial updateTutorialCategory(ObjectId id, String category) {
        Tutorial tutorial = fetchTutorialOrThrow(id);
        tutorial.setCategory(category);
        return tutorialRepository.save(tutorial);
    }

    public Tutorial updateTutorialCreatedDate(ObjectId id, Date createdDate) {
        Tutorial tutorial = fetchTutorialOrThrow(id);
        tutorial.setCreatedDate(createdDate);
        return tutorialRepository.save(tutorial);
    }

    public Tutorial updateTutorialLessons(ObjectId id, List<Lesson> lessons) {
        Tutorial tutorial = fetchTutorialOrThrow(id);
        tutorial.setLessons(lessons);
        return tutorialRepository.save(tutorial);
    }

    @SuppressWarnings("unchecked")
    public Tutorial patchTutorial(ObjectId id, Map<String, Object> updates) {
        Tutorial tutorial = fetchTutorialOrThrow(id);
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            switch (field) {
            case "name":
                tutorial.setName((String)value);
                break;
            case "tutorialUrl":
                tutorial.setTutorialUrl((String)value);
                break;
            case "isDone":
                tutorial.setDone((boolean)value);
                break;
            case "category":
                tutorial.setCategory((String) value);
                break;
            case "createdDate":
                tutorial.setCreatedDate((Date) value);
                break;
            case "lessons":
                tutorial.setLessons((List<Lesson>) value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
            }
        }
        return tutorialRepository.save(tutorial);
    }
}
