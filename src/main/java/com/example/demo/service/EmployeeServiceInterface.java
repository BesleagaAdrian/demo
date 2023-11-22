package com.example.demo.service;

import com.example.demo.domain.Employee;

import java.util.List;

public interface EmployeeServiceInterface {

    Employee getEmployeeByUsername(String userName);

    Employee createEmployee(Employee employee);

    List<Employee> getEmployees();
}
