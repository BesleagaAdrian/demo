package com.example.demo.repository;

import com.example.demo.domain.Employee;
import org.springframework.data.repository.ListCrudRepository;

public interface EmployeeRepository extends ListCrudRepository<Employee, Long> {
    Employee findByUsername(String username);
}
