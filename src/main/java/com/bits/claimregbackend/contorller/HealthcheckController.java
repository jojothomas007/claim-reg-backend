package com.bits.claimregbackend.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController {

    @GetMapping("/")
    public String isAlive() {
        return "Claim registration application is alive!";
    }

}