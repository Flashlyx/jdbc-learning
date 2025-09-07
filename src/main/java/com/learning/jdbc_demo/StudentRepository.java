package com.learning.jdbc_demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // That's it! All CRUD methods are inherited from JpaRepository.


    Optional<Student> findByEmail(String email);
}