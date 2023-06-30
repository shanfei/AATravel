package com.home.aatravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_members_tbl")
@Data
public class ProjectMember {

    public ProjectMember() {
    }

    public ProjectMember(ProjectMemberCompositeKey id, ProjectRole role, User user, Project project) {
        this.id = id;
        this.role = role;
        this.user = user;
        this.project = project;
    }

    public ProjectMember(ProjectRole role, User user, Project project) {
        this.role = role;
        this.user = user;
        this.project = project;
        this.id = new ProjectMemberCompositeKey(user.getId(), project.getId());
    }

    @EmbeddedId
    private ProjectMemberCompositeKey id;

    @Enumerated(EnumType.STRING)
    private ProjectRole role;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("projectId")
    private Project project;


}
