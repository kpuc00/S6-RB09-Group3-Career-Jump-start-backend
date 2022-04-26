package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/test")
public class UserController {

  @Autowired
  UserRepository userRepo;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

//  @GetMapping("/all/candidates")
//  @PreAuthorize("hasRole('ADMIN')")
//  public String getCandidates(){
//
//  }
//
//  @GetMapping("/all/companies")
//  @PreAuthorize("hasRole('ADMIN')")
//  public String getCandidates(){
//
//  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
