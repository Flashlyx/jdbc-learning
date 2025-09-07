package com.learning.jdbc_demo;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @PostMapping("/students")
    public  ResponseEntity<String> createStudent(@RequestBody Student student) {

        studentRepository.save(student);
        return new ResponseEntity<>("Student created successfuly", HttpStatus.CREATED);
    }

    // In StudentController.java
    @PutMapping("/students/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        studentRepository.save(student); // Use save() for updates
        return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/students/search")
    public ResponseEntity<Student> getStudentByEmail(@RequestParam String email) {
        // Call our new custom finder method
        Optional<Student> studentOptional = studentRepository.findByEmail(email);

        // Check if a student was found and return the appropriate response
        return studentOptional
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

