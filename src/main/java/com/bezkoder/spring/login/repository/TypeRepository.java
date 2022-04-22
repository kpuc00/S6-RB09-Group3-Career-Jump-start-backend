package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.EType;
import com.bezkoder.spring.login.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByName(EType name);
}
