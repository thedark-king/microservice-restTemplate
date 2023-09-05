package com.microservice.departmentservce.service.impl;

import com.microservice.departmentservce.entity.Department;
import com.microservice.departmentservce.repository.DepartmentRepository;
import com.microservice.departmentservce.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
    log.info("Saving the department details request is {} ", department);

        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long departmentId) {
        log.info("getting the department details for departmentId is {} ", departmentId);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return departmentRepository.findById(departmentId).get();
    }


}
