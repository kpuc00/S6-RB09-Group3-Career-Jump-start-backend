package com.bezkoder.spring.login.repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.bezkoder.spring.login.models.ERole;
import com.bezkoder.spring.login.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.login.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Optional<User> findById(Long id);

  List<User> findAll();

  List<User> findAllByRolesIn(Set<Role> roles);

  @Override
  <S extends User> S save(S entity);

  @Override
  void deleteById(Long aLong);
}
