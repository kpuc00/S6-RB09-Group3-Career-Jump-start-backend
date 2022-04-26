package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/admin")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/candidates")
  @PreAuthorize("hasRole('ADMIN')")
  public List<User> getCandidates(){
      return userService.findCandidates();
  }

  @GetMapping("/companies")
  @PreAuthorize("hasRole('ADMIN')")
  public List<User> getCompanies(){
      return userService.findCompanies();
  }

  @PutMapping("/user/{:id}")
  @PreAuthorize("hasRole('ADMIN')")
  public User editUser(@RequestBody User user, @PathVariable Long id){
      return userService.updateUser(id, user);
  }
  @DeleteMapping("/delete/{:id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteUser(@PathVariable Long id){
     userService.deleteUser(id);
  }
}
