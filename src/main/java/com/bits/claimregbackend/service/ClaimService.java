package com.bits.claimregbackend.service;

import com.bits.claimregbackend.entity.Claim;
import com.bits.claimregbackend.entity.ClaimItem;
import com.bits.claimregbackend.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {
    @Autowired
    ClaimRepository repository;

    public Claim createClaim(Claim claim) {
        repository.save(claim);
        return claim;
    }

    public Claim addClaimItem(Claim claim, ClaimItem claimItem) {
        return claim;
    }

    public Claim submitClaim(Claim claim) {
        return null;
    }

    public void deleteClaim() {
    }

    public Claim getClaim(Long id) {
        return new Claim();
    }

    public List<Claim> viewCreatedClaims() {
        return repository.findAll();
    }

    public List<Claim> viewSubmittedClaims() {
        return repository.findAll();
    }

    public List<Claim> viewApprovedClaims() {
        return repository.findAll();
    }

}
