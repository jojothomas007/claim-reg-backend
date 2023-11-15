package com.bits.claimregbackend.contorller;

import com.bits.claimregbackend.entity.Claim;
import com.bits.claimregbackend.entity.ClaimItem;
import com.bits.claimregbackend.enums.ClaimStatus;
import com.bits.claimregbackend.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ClaimController {
    @Autowired
    ClaimService claimService;

    @PostMapping("/claims")
    public Claim createClaim(@Validated @RequestBody Claim claim) {
        return claimService.createClaim(claim);
    }

    @PutMapping("/claims/{id}/add-item")
    public Claim addClaimItem(@PathVariable("id") Long claimId, @Validated @RequestBody ClaimItem claimItem) throws Exception {
        Claim claim = claimService.getClaim(claimId);
        return claimService.createClaim(claim);
    }

    @PutMapping("/claims/{id}/submit")
    public Claim submitClaim(@PathVariable("id") Long claimId) throws Exception {
        Claim claim = claimService.getClaim(claimId);
        return claimService.updateStatus(claim, ClaimStatus.submitted);
    }

    @PutMapping("/claims/{id}/approve")
    public Claim approveClaim(@PathVariable("id") Long claimId) throws Exception {
        Claim claim = claimService.getClaim(claimId);
        return claimService.updateStatus(claim, ClaimStatus.approved);
    }


    @GetMapping("/claims/{id}")
    public Claim fetchClaimById(@PathVariable("id") Long claimId) throws Exception {
        return claimService.getClaim(claimId);
    }

    @GetMapping("/claims")
    public List<Claim> fetchClaims(@RequestParam String status) {
        System.out.println(status);
        return claimService.getClaimsByStatus(ClaimStatus.valueOf(status));
    }

    @DeleteMapping("/claims/{id}")
    public void deleteClaimById(@PathVariable("id") Long claimId) {
        claimService.deleteClaim(claimId);
    }


}