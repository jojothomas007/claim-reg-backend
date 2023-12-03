package com.bits.claimregbackend.repository;

import com.bits.claimregbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByIsApprover(Boolean isApprover);

    // custom query to search to blog post by title or content
//    List<Employee> findByTitleContainingOrContentContaining(String text, String textAgain);
}