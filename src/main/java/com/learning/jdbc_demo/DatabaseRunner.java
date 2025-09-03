package com.learning.jdbc_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component // 1. Tells Spring to create and manage this class as a component.
public class DatabaseRunner implements CommandLineRunner {

    // 2. A logger to print information to the console cleanly.
    private static final Logger logger = LoggerFactory.getLogger(DatabaseRunner.class);

    // 3. We ask Spring to inject our repository here.
    private final StudentRepository studentRepository;

    public DatabaseRunner(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("---- EXECUTING DATABASE RUNNER ----");

        // Let's search for the student with ID = 1
        logger.info("--> Searching for student with ID 1...");
        Optional<Student> studentOptional = studentRepository.findById(3);

        // The modern way to handle an Optional result
        studentOptional.ifPresentOrElse(
                student -> logger.info("--> Found Student: {}", student),
                () -> logger.info("--> Student with ID 1 not found.")
        );

        // Let's try searching for a student that doesn't exist
        logger.info("--> Searching for student with ID 99...");
        Optional<Student> notFoundOptional = studentRepository.findById(99);
        notFoundOptional.ifPresentOrElse(
                student -> logger.info("--> Found Student: {}", student),
                () -> logger.info("--> Student with ID 99 not found, as expected.")
        );

        logger.info("---- DATABASE RUNNER FINISHED ----");
    }
}