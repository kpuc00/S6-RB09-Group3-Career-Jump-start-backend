package com.bezkoder.spring.login.service;

import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public Optional<User> findById(Long aLong) {
        return userRepo.findById(aLong);
    }

    @Override
    public List<User> findCandidates() {
        List<User> candidates = new ArrayList<>();
        try{
            for (User u:
                 userRepo.findCollectionCandidate()) {
               candidates.add(u);
            }
        } catch(Exception ex){
            ex.getMessage();
        }
        return candidates;
    }

    @Override
    public List<User> findCompanies() {
        List<User> companies = new ArrayList<>();
        try{
            for (User u:
                    userRepo.findCollectionCompany()) {
                companies.add(u);
            }
        } catch(Exception ex){
            ex.getMessage();
        }
        return companies;
    }

}
