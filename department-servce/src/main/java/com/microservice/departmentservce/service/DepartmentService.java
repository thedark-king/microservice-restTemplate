package com.microservice.departmentservce.service;

import com.microservice.departmentservce.entity.Department;

public interface DepartmentService {

    Department saveDepartment(Department department);

    Department getDepartmentById(Long departmentId);

}
