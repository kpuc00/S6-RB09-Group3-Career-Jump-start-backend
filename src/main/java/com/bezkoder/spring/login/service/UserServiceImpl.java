package com.bezkoder.spring.login.service;

import com.bezkoder.spring.login.models.ERole;
import com.bezkoder.spring.login.models.Role;
import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.repository.RoleRepository;
import com.bezkoder.spring.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder encoder;

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

    @Override
    public User updateUser(Long id, User u) {
        System.out.println("HIIII");
        Optional<User> user = findById(id);
        System.out.println("user by id: " + user);
        if(user.isPresent()){
            if(!userRepo.existsByEmail(u.getEmail())){
                user.get().setEmail(u.getEmail());
            }
            user.get().setPassword(encoder.encode(u.getPassword()));
            System.out.println("changed user : " + user.get());
            return userRepo.save(user.get());
        }
        else{
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }


}
