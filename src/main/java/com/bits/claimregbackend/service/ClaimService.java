package com.bits.claimregbackend.service;

import com.bits.claimregbackend.entity.Claim;
import com.bits.claimregbackend.entity.ClaimItem;
import com.bits.claimregbackend.repository.ClaimRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {
    @Autowired
    ClaimRespository respository;

    public Claim createClaim(Claim claim) {
        respository.save(claim);
        return claim;
    }

    public Claim addClaimItem(Claim claim, ClaimItem claimItem) {
        return claim;
    }

    public Claim submitClaim(Claim claim) {
        return null;
    }

    public Claim approveClaim(Claim claim) {
        return null;
    }

    public List<Claim> viewSubmittedClaims() {
        return null;
    }

    public List<Claim> viewClaimsForApproval() {
        return null;
    }

    public void deleteClaim() {
    }

    public Claim getClaim(Long id) {
        return new Claim();
    }

    public List<Claim> viewApprovedClaims() {
        return null;
    }

    public List<Claim> viewDraftClaims() {
        return null;
    }
}
