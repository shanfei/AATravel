package com.home.aatravel.service;

import com.home.aatravel.entity.*;
import com.home.aatravel.repository.ProjectRepository;
import com.home.aatravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    private UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Project create(long uid, Project project) {

        project = projectRepository.save(project);

        assert(project.getId() != null);

        User user = userRepository.findById(uid).orElse(null);

        ProjectMember projectMember = new ProjectMember(ProjectRole.OWNER, user, project);

        project.getMembers().add(projectMember);

        return projectRepository.save(project);

    }


    @Transactional
    public Project addMember(long pid, long uid) {

        User user = userRepository.findById(uid).orElse(null);

        Project project = projectRepository.findById(pid).orElse(null);

        ProjectMember projectMember = new ProjectMember(ProjectRole.MEMBER, user, project);

        project.getMembers().add(projectMember);

        return projectRepository.save(project);

    }
}
