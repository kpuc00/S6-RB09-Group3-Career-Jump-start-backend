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
        return userRepo.findAllByRolesIn(roleToSet(ERole.ROLE_CANDIDATE));
    }

    @Override
    public List<User> findCompanies() {
        return userRepo.findAllByRolesIn(roleToSet(ERole.ROLE_COMPANY));
    }

    @Override
    public Optional<Role> findRoleByName(ERole eRole) {
        return roleRepo.findByName(eRole);
    }

    public Set<Role> roleToSet(ERole role){
        Set<Role> roleSet = new HashSet<>();
        Optional<Role> r = findRoleByName(role);
        r.ifPresent(roleSet::add);
        return roleSet;
    }


    @Override
    public User updateUserDetails(Long id, User u) {
        Optional<User> user = findById(id);
        if(user.isPresent()){
            if(!userRepo.existsByEmail(u.getEmail())){
                user.get().setEmail(u.getEmail());
            }
            user.get().setPhoneNumber(u.getPhoneNumber());
            user.get().setPassword(encoder.encode(u.getPassword()));

            return userRepo.save(user.get());
        }
        else{
            return null;
        }
    }

    @Override
    public User updateUserStatus(Long id, User u){
        Optional<User> user = findById(id);
        user.get().setStatus(u.getStatus());
        return userRepo.save(user.get());
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }


}
