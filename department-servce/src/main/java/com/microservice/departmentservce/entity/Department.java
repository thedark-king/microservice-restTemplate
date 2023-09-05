package com.microservice.departmentservce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "department")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "departmentName")
    private String departmentName;
    @Column(name = "departmentAddress")
    private String departmentAddress;
    @Column(name = "departmentCode")
    private String departmentCode;
}
