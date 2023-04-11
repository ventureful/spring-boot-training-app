package com.example.employee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue
    private BigInteger id;

    @Column(name = "name", unique = true, length = 45, nullable = false)
    private String name;

    @Column(name = "description", unique = true, length = 150)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
