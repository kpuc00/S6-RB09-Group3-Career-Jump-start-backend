package com.bezkoder.spring.login.service;

import com.bezkoder.spring.login.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {

    Optional<User> findById(Long aLong);


}
