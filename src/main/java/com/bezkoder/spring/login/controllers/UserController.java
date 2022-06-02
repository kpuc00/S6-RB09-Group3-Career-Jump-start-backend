package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/admin/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  //TODO: we can also add job matcher role here
  public ResponseEntity<?> getUser(@PathVariable Long id) {
    Optional<User> user = userService.findById(id);
    if(user.isPresent()){
        return ResponseEntity.status(HttpStatus.FOUND).body(user.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
  }

  @GetMapping("/candidates")
  @PreAuthorize("hasRole('ADMIN')")
  //TODO: we can also add job matcher role here
  public ResponseEntity<?> getCandidates(){
    List<User> candidates = userService.findCandidates();
    if(!candidates.isEmpty()){
      return ResponseEntity.status(HttpStatus.FOUND).body(candidates);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    //TODO: this is unnecessary -> empty array instead
  }

  @GetMapping("/companies")
  @PreAuthorize("hasRole('ADMIN')")
  //TODO: we can also add job matcher role here
  public ResponseEntity<?> getCompanies(){
    List<User> companies = userService.findCompanies();
    if(!companies.isEmpty()){
      return ResponseEntity.status(HttpStatus.FOUND).body(companies);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    //TODO: this is unnecessary -> empty array instead
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> editUserDetails(@RequestBody User user, @PathVariable Long id){
      if(userService.findById(id).isPresent()){
        userService.updateUserDetails(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("Details Successfully changed");
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
  }

  @PutMapping("/status/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> editUserStatus(@RequestBody User user, @PathVariable Long id){
    if(userService.findById(id).isPresent()){
      userService.updateUserStatus(id, user);
      return ResponseEntity.status(HttpStatus.OK).body("Status Successfully changed");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> deleteUser(@PathVariable Long id){
    if(userService.findById(id).isPresent()){
      userService.deleteUser(id);
      return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
  }

}
