package com.bits.claimregbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "claim_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "claimant_id", referencedColumnName = "employee_id", nullable = false)
    private Employee claimant;
    @ManyToOne
    @JoinColumn(name = "approver_id", referencedColumnName = "employee_id", nullable = false)
    private Employee approver;
    @Column(nullable = false)
    private String type;
    @OneToMany
    @JoinColumn(name = "claim_id", referencedColumnName = "claim_id")
    private List<ClaimItem> items;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private String status;
}
