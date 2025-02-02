package com.huchongxern.project_organiser.service;

import com.huchongxern.project_organiser.model.Lesson;
import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.model.Tutorial;
import com.huchongxern.project_organiser.repository.ProjectRepository;
import com.huchongxern.project_organiser.repository.TutorialRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {
    @Autowired
    private TutorialRepository tutorialRepository;

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
}
