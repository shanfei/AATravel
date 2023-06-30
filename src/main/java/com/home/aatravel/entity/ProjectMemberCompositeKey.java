package com.home.aatravel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProjectMemberCompositeKey implements Serializable {

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "user_id")
    private Long userId;

}
