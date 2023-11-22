package com.example.demo.controller;

import com.example.demo.domain.Project;
import com.example.demo.service.ProjectServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    private ProjectServiceInterface projectServiceInterface;

    @GetMapping(path = "/projects")
    public List<Project> getProjects() {
        return projectServiceInterface.getProjects();
    }

    @PostMapping(path = "/projects")
    public Project createProject(@RequestBody Project project) {
        return projectServiceInterface.createProject(project);
    }
}
