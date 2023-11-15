package com.bits.claimregbackend.contorller;

import com.bits.claimregbackend.entity.ClaimItem;
import com.bits.claimregbackend.service.ClaimItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ClaimItemController {
    @Autowired
    ClaimItemService claimItemService;

    @PostMapping("/claim-items")
    public ClaimItem createClaimItem(@Validated @RequestBody ClaimItem claimItem) {
        return claimItemService.createClaimItem(claimItem);
    }

    @GetMapping("/claim-items/{id}")
    public ClaimItem fetchClaimItemById(@PathVariable("id") Long id) {
        return claimItemService.getClaimItem(id);
    }

    @GetMapping("/claim-items/findByClaim")
    public ClaimItem fetchClaimItemByClaimId(@RequestParam Long claimId) {
        return claimItemService.getClaimItemByClaim(claimId);
    }

    @DeleteMapping("/claim-items/{id}")
    public void deleteClaimItemById(@Validated @RequestBody ClaimItem claimItem) {
        claimItemService.deleteClaimItem(claimItem);
    }
}