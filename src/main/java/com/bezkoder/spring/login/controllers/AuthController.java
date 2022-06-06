package com.bezkoder.spring.login.controllers;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bezkoder.spring.login.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import com.bezkoder.spring.login.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.login.payload.request.LoginRequest;
import com.bezkoder.spring.login.payload.request.SignupRequest;
import com.bezkoder.spring.login.payload.response.UserInfoResponse;
import com.bezkoder.spring.login.payload.response.MessageResponse;
import com.bezkoder.spring.login.repository.RoleRepository;
import com.bezkoder.spring.login.repository.UserRepository;
import com.bezkoder.spring.login.security.jwt.JwtUtils;
import com.bezkoder.spring.login.security.services.UserDetailsImpl;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  private UserDetails getUserFromToken(String token) {
    try {
      if (token != null && jwtUtils.validateJwtToken(token)) {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        return userDetailsService.loadUserByUsername(username);
      }
    } catch (Exception e) {
      logger.error("Cannot set user authentication: {}", e);
    }

    return null;
  }

  @RabbitListener(queues = "${queue.name}")
  public String getUserDetailsFromToken(String token) {
    UserDetails userDetails = getUserFromToken(token);
    System.out.println("Received Token:" + token);
    assert userDetails != null;
    return userDetails.getUsername();
  }

  @RequestMapping(value = "/checkadmin", method = RequestMethod.GET)
  @ResponseBody
  public Object checkIfAdmin(Authentication authentication) {
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    return authentication.getAuthorities();
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(new UserInfoResponse(userDetails.getUsername(),
            userDetails.getFirstName(),
            userDetails.getLastName(),
            userDetails.getPhoneNumber(),
            userDetails.getDob(),
            userDetails.getEmail(),
            userDetails.getQuestionnaireAnswered(),
            roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
        signUpRequest.getFirstName(),
        signUpRequest.getLastName(),
        signUpRequest.getPhoneNumber(),
        signUpRequest.getDob(),
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      throw new RuntimeException("Error: Set role! ");
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            break;
          case "company":
            Role companyRole = roleRepository.findByName(ERole.ROLE_COMPANY)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(companyRole);
            break;
          case "candidate":
            Role candidateRole = roleRepository.findByName(ERole.ROLE_CANDIDATE)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(candidateRole);
            break;
          case "matcher":
            Role matcherRole = roleRepository.findByName(ERole.ROLE_MATCHER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(matcherRole);
            break;
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}
