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

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = {"http://localhost:3000", "https://careerjumpapp.com"}, maxAge = 3600, allowCredentials = "true")
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
    return ResponseEntity.status(HttpStatus.OK).body(candidates);
  }

  @GetMapping("/companies")
  @PreAuthorize("hasRole('ADMIN')")
  //TODO: we can also add job matcher role here
  public ResponseEntity<?> getCompanies(){
    List<User> companies = userService.findCompanies();
    return ResponseEntity.status(HttpStatus.OK).body(companies);
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

  @GetMapping(value = "/questionnaire/{username}")
  public ResponseEntity<?> setQuestionnaireTrue(@PathVariable() String username) {
    userService.updateUserQuestionnaire(username);
    return ResponseEntity.status(HttpStatus.OK).body("Answers submitted successfully!");
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
