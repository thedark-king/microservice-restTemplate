package com.microservice.departmentservce.repository;

import com.microservice.departmentservce.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
