package com.bezkoder.spring.login.service;

import com.bezkoder.spring.login.models.ERole;
import com.bezkoder.spring.login.models.Role;
import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.repository.RoleRepository;
import com.bezkoder.spring.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Override
    public Optional<User> findById(Long aLong) {
        return userRepo.findById(aLong);
    }

    @Override
    public List<User> findCandidates() {
        Optional<Role> r = roleRepo.findByName(ERole.ROLE_CANDIDATE);
        Set<Role> roleSet = new HashSet<>();
        r.ifPresent(roleSet::add);

        return userRepo.findAllByRolesIn(roleSet);
    }

    @Override
    public List<User> findCompanies() {
        Optional<Role> r = roleRepo.findByName(ERole.ROLE_COMPANY);
        Set<Role> roleSet = new HashSet<>();
        r.ifPresent(roleSet::add);

        return userRepo.findAllByRolesIn(roleSet);
    }


}
