package com.dada.Srt.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<com.dada.Srt.employee.Employee,Integer> {
    Optional<com.dada.Srt.employee.Employee>findEmployeeByAddress(String email);

}
