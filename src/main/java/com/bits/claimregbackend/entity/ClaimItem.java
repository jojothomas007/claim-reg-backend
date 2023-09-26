package com.bits.claimregbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaimItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//  @GeneratedValue(generator="my_seq")
//  @SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize=1)
    @Column(name = "claim_item_id")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate billDate;
    @Column(nullable = false)
    private String billNumber;
    @Column(nullable = false)
    private String expenseCode;
    @Column(nullable = false)
    private Long costCenter;
    @Column(nullable = false)
    private Double amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate subscriptionStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate subscriptionEndDate;
}