package com.bezkoder.spring.login.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.login.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Override
  Optional<User> findById(Long aLong);

  @Query("SELECT u FROM User u where u.type.name = 'TYPE_CANDIDATE' ")
  List<User> findCollectionCandidate();

  @Query("SELECT u FROM User u where u.type.name = 'TYPE_COMPANY' ")
  List<User> findCollectionCompany();

}
