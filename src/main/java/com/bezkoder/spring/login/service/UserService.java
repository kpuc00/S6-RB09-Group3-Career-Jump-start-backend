package com.bezkoder.spring.login.service;

import com.bezkoder.spring.login.models.ERole;
import com.bezkoder.spring.login.models.Role;
import com.bezkoder.spring.login.models.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService  {

    Optional<User> findById(Long aLong);

    List<User> findCandidates();

    List<User> findCompanies();

    Set<Role> roleToSet(ERole eRole);

    Optional<Role> findRoleByName(ERole eRole);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
