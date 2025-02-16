package com.huchongxern.project_organiser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

import static com.huchongxern.project_organiser.utils.Util.getCurrDate;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @Field("github_username")
    private String githubUsername;
    @Field("display_name")
    private String displayName;
    @Field("profile_image_url")
    private String profileImageUrl;
    @Field("created_date")
    private Date createdDate;
    private List<Project> projects;

    public User(ObjectId id, String githubUsername, String displayName, String profileImageUrl, List<Project> projects) {
        this.id = id;
        this.githubUsername = githubUsername;
        this.displayName = displayName;
        this.profileImageUrl = profileImageUrl;
        this.createdDate = getCurrDate();
        this.projects = projects;
    }
}
