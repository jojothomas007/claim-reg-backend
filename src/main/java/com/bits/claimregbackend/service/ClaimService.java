package com.bits.claimregbackend.service;

import com.bits.claimregbackend.entity.Claim;
import com.bits.claimregbackend.entity.ClaimItem;
import com.bits.claimregbackend.enums.ClaimStatus;
import com.bits.claimregbackend.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {
    @Autowired
    ClaimRepository repository;

    public Claim createClaim(Claim claim) {
        return repository.save(claim);
    }

    public Claim addClaimItem(Claim claim, ClaimItem claimItem) {
        List<ClaimItem> claimItems = claim.getItems();
        claimItems.add(claimItem);
        return repository.save(claim);
    }

    public Claim updateStatus(Claim claim, ClaimStatus claimStatus) {
        claim.setStatus(claimStatus.name());
        return repository.save(claim);
    }

    public void deleteClaim(Long id) {
        repository.deleteById(id);
    }

    public Claim getClaim(Long id) throws Exception {
        Optional<Claim> claim = repository.findById(id);
        if (claim.isEmpty()) {
            throw new Exception("Claim not found");
        } else {
            return claim.get();
        }
    }

    public List<Claim> getClaimsByStatus(ClaimStatus status) {
        return repository.findByStatus(status.name());
    }
}
