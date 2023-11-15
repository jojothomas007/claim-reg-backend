package com.bits.claimregbackend.contorller;

import com.bits.claimregbackend.entity.Claim;
import com.bits.claimregbackend.entity.ClaimItem;
import com.bits.claimregbackend.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
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
    public Claim addClaimItem(@PathVariable("id") Long claimId, @Validated @RequestBody ClaimItem claimItem) {
        Claim claim = claimService.getClaim(claimId);
        return claimService.createClaim(claim);
    }

    @PutMapping("/claims/{id}")
    public Claim submitClaim(@PathVariable("id") Long claimId, @RequestParam String status) {
        Claim claim = claimService.getClaim(claimId);
        return claimService.submitClaim(claim);
    }

    @GetMapping("/claims/{id}")
    public Claim fetchClaimById(@PathVariable("id") Long claimId) {
        return claimService.getClaim(claimId);
    }
    
    @GetMapping("/claims")
    public List<Claim> fetchClaims(@RequestParam String status) {
        List<Claim> claimList;
        switch (status) {
            case "created":
                claimList = claimService.viewCreatedClaims();
                break;
            case "submitted":
                claimList = claimService.viewSubmittedClaims();
                break;
            case "approved":
                claimList = claimService.viewApprovedClaims();
                break;
            default:
                throw new InvalidParameterException("Invalid status!");
        }
        return claimList;
    }

    @DeleteMapping("/claims/{id}")
    public void deleteClaimById(@PathVariable("id") Long claimId) {
        claimService.deleteClaim();
    }


}