package com.bits.claimregbackend.service;

import com.bits.claimregbackend.entity.ClaimItem;
import com.bits.claimregbackend.repository.ClaimItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClaimItemService {
    @Autowired
    ClaimItemRepository repository;
    private static final String ENTITY_DOES_NOT_EXIST = "ENTITY DOES NOT EXIST!";

    public ClaimItem createClaimItem(ClaimItem claimItem) {
        return repository.save(claimItem);
    }

    public ClaimItem getClaimItem(Long id) {
        try {
            return repository.getReferenceById(id);
        } catch (Exception e) {
            throw new RuntimeException(String.format("%s : %s", HttpStatus.NOT_FOUND, ENTITY_DOES_NOT_EXIST));
        }
    }

    public ClaimItem getClaimItemByClaim(Long claimId) {
        return new ClaimItem();
    }

    public void deleteClaimItem(ClaimItem claimItem) {
        repository.delete(claimItem);
    }

}
