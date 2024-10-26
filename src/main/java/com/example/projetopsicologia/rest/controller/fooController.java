package com.example.projetopsicologia.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController


    public class fooController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRout(){
        return ResponseEntity.ok("Public rout Ok");
    }
    @GetMapping("/private")
    public ResponseEntity<String> privateRout(Authentication authentication){
        return ResponseEntity.ok("private rout Ok");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminRout(Authentication authentication){
        return ResponseEntity.ok("admin rout Ok");
    }

}
