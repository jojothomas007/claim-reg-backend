package com.bits.claimregbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String department;
    @Column(nullable = false)
    private String team;
    @Column(nullable = false)
    private Boolean isApprover;
}
