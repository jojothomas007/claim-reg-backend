package com.bits.claimregbackend.repository;

import com.bits.claimregbackend.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByStatus(String status);
    // custom query to search to blog post by title or content
//    List<Employee> findByTitleContainingOrContentContaining(String text, String textAgain);
}