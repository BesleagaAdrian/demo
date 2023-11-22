package com.example.demo.service;

import com.example.demo.domain.Project;

import java.util.List;

public interface ProjectServiceInterface {
    Project createProject(Project project);

    List<Project> getProjects();
}
