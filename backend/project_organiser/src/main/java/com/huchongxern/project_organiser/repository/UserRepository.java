package com.huchongxern.project_organiser.repository;

import com.huchongxern.project_organiser.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Object> {
}
