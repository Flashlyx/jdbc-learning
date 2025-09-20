package com.learning.jdbc_demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

@RestController
public class StudentController {

    // 1. We now inject the StudentService
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 2. All methods now call the service layer
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) { // Add @Valid
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("/students/search")
    public ResponseEntity<Student> getStudentByEmail(@RequestParam String email) {
        Optional<Student> studentOptional = studentService.findStudentByEmail(email);
        return studentOptional
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @Valid @RequestBody Student studentDetails) { // Add @Valid
        studentDetails.setId(id);
        Student updatedStudent = studentService.updateStudent(studentDetails);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Student> enrollStudentInCourse(@PathVariable int studentId, @PathVariable int courseId) {
        Student updatedStudent = studentService.enrollStudentInCourse(studentId, courseId);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }
}