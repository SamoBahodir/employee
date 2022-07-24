package com.example.employee.rest;

import com.example.employee.ResourceNotFoundException;
import com.example.employee.domen.Employee;
import com.example.employee.repo.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
@Tag(name = "Employee controller")
public class EmployeeResource {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @Operation(
            description = "Employee get", summary = "Employee get", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Operation",
                    content = @Content(mediaType = "application/json ",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Font", content = @Content)
    }
    )
    @GetMapping("/get")
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Operation(
            description = "Employee get", summary = "Employee get", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Operation",
                    content = @Content(mediaType = "application/json ",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Font", content = @Content)
    }
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getAllId(@PathVariable Long id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for " + id));
        return ResponseEntity.ok().body(employee);
    }

    @Operation(description = "Product post", summary = "Product post", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/create")
    public Employee createProduct(@Valid @RequestBody Employee user) {
        Employee employee = new Employee();
        employee.setEmail(user.getEmail());
        employee.setLastName(user.getLastName());
        employee.setName(user.getName());
        employee.setPhone(user.getPhone());
        employee.setVersion(user.getVersion());
        return employeeRepository.save(employee);
    }

    @Operation(description = "Product post", summary = "Product post", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/list")
    public List<Employee> addList(@Valid @RequestBody List<Employee> employee) {
        return employeeService.saveEmployee(employee);
    }

    @Operation(description = "Product put", summary = "Product put", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id,
                                           @Valid @RequestBody Employee user) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id " + id));
        employeeRepository.deleteById(id);
        employee.setEmail(user.getEmail());
        employee.setLastName(user.getLastName());
        employee.setName(user.getName());
        employee.setPhone(user.getPhone());
        employee.setVersion(user.getVersion());
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(employee);
    }

    @Operation(description = "Product Delete", summary = "Product delete", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFoundException {
        employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id " + id));
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
