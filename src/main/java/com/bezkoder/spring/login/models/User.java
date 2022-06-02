package com.bezkoder.spring.login.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  private String firstName;

  @NotBlank
  @Size(max = 50)
  private String lastName;

  @NotBlank
  @Size(max = 50)
  private String phoneNumber;

  @DateTimeFormat
  private Date dob;

  private boolean questionnaireAnswered;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", 
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @Column(columnDefinition = "varchar(32) default 'UNASSIGNED'")
  @Enumerated(value = EnumType.STRING)
  private EStatus status = EStatus.UNASSIGNED;

  public User() {

  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", dob=" + dob +
            ", questionnaireAnswered=" + questionnaireAnswered +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", roles=" + roles +
            ", status=" + status +
            '}';
  }

  public boolean isQuestionnaireAnswered() {
    return questionnaireAnswered;
  }

  public void setQuestionnaireAnswered(boolean questionnaireAnswered) {
    this.questionnaireAnswered = questionnaireAnswered;
  }

  public User(String username, String firstName, String lastName, String phoneNumber, Date dob, String email, String password) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.dob = dob;
    this.email = email;
    this.password = password;
    this.questionnaireAnswered = false;
  }



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName(){
    return lastName;
  }

  public void setLastName(String lastName){
    this.lastName = lastName;
  }

  public String getPhoneNumber(){
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
  }

  public Date getDob(){
    return dob;
  }

  public void setDob(Date dob){
    this.dob = dob;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public EStatus getStatus() {
    return status;
  }

  public void setStatus(EStatus status) {
    this.status = status;
  }

}
