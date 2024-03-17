package com.foodhero.notification.notificationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class notificationController {

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> test(){

        return new ResponseEntity<>("test bien passe",HttpStatus.OK);
    }
    @GetMapping("/testAdmin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> testAdmin(){

        return new ResponseEntity<>("testADMIN bien passe",HttpStatus.OK);
    }

    @GetMapping("/mySession")
    public Authentication authentification(Authentication authentication){
        return authentication;
    }
}
