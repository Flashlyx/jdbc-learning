package com.learning.jdbc_demo;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@Repository // 1. Tells Spring this is a data access component
public class StudentRepository {

    // 2. A field to hold the JdbcTemplate that Spring will give us
    private final JdbcTemplate jdbcTemplate;

    // 3. The constructor where Spring performs "Dependency Injection"
    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> findAll() {
        // 1. The SQL query
        String sql = "SELECT id, name, email FROM student";

        // 2. The RowMapper "translator"
        // For each row (rs), create a new Student object using the data from the columns.
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email")
        );

        // 3. Execute the query using the JdbcTemplate
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int save(Student student){
        // SQL Query
        String sql = "INSERT INTO student (name, email) VALUES (?, ?)";

        // Execute the update, passing in student's data
        return jdbcTemplate.update(sql, student.getName(),student.getEmail());

    }

    public Optional<Student> findById(int id){
        // SQL Query
        String sql = "SELECT id, name, email FROM student WHERE id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email")
        );

        try {
            // If the query finds exactly one row
            Student student = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.of(student);
        }
        catch (EmptyResultDataAccessException e) {
            //If no row is found and exception is thrown. We catch it and return empty
            return Optional.empty();
        }
    }
}