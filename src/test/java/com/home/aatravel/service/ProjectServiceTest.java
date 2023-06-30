package com.home.aatravel.service;

import com.home.aatravel.entity.Project;
import com.home.aatravel.entity.User;
import com.home.aatravel.repository.ProjectRepository;
import com.home.aatravel.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProjectServiceTest {

    private ProjectService projectService;

    private UserRepository userRepository;

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceTest(ProjectService projectService, UserRepository userRepository, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    protected Instant NDaysLaterInUTC(int n) {
        return LocalDate.now().plusDays(3)
                .atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    @Test
    public void testCreateProject() {

        User user = userRepository.save(new User("Fei"));

        Project createdProject = projectService.create(user.getId(), new Project("Project A",
                "This is a new Project.",
                NDaysLaterInUTC(0),
                NDaysLaterInUTC(3)));

        assertNotNull(createdProject);
        assertNotNull(createdProject.getId());
        assertEquals(1L, createdProject.getMembers().size());
        assertEquals(0L, createdProject.getTransactionList().size());

    }

    @Test
    @Transactional
    public void testAddMemberToProject() {
        User user = userRepository.save(new User("Fei"));
        Project createdProject = projectService.create(user.getId(), new Project("Project A",
                "This is a new Project.",
                NDaysLaterInUTC(0),
                NDaysLaterInUTC(3)));

        User userA = userRepository.save(new User("UserA"));
        Project updatedProject = projectService.addMember(createdProject.getId(), userA.getId());

        assertNotNull(updatedProject);
        assertEquals(createdProject.getId(), updatedProject.getId());
        assertEquals(2L, updatedProject.getMembers().size());
        assertEquals(0L, updatedProject.getTransactionList().size());
    }


}
