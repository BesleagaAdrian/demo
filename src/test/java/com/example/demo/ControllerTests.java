package com.example.demo;

import com.example.demo.domain.Employee;
import com.example.demo.domain.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTests {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldCreateEntities() throws Exception {
        //GIVEN: A project is created
        Project testProject = new Project(1L, "TestProject");
        String projectAsJson = objectMapper.writeValueAsString(testProject);
        mockMvc.perform(post("/api/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectAsJson))
                .andExpect(status().isOk())
                .andExpect(content().json(projectAsJson));

        //AND: An employee is created and assigned to the previous project
        Employee employee = new Employee(1L, "Test", "a", 100L, testProject);
        String employeeAsJson = objectMapper.writeValueAsString(employee);
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeAsJson))
                .andExpect(status().isOk())
                .andExpect(content().json(employeeAsJson));

        //WHEN: Returning the list of employees
        String expectedResponse = objectMapper.writeValueAsString(List.of(employee));
        mockMvc.perform(get("/api/employees"))
                //THEN: The expected result matches
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse))
                .andReturn().getResponse().getContentAsString();
    }

}
