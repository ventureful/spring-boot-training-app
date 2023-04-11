package com.example.employee.repository;

import com.example.employee.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, BigInteger> {
}
