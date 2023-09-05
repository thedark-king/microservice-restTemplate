package com.microservice.departmentservce.controller;

import com.microservice.departmentservce.entity.Department;
import com.microservice.departmentservce.service.DepartmentService;
import com.microservice.departmentservce.service.impl.DepartmentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentServiceImpl departmentService;


    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
        Department saveDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long departmentId){
        Department department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }
}
