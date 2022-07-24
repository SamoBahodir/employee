package com.example.employee.rest;

import com.example.employee.domen.Employee;
import com.example.employee.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public List<Employee> saveEmployee(List<Employee> employeeList) {
        return employeeRepository.saveAll(employeeList);
    }
}
