package com.learning.jdbc_demo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Course(){
    }

    public Integer getID() {
        return id;
    }

    public void setId(Integer id){
        this.id =id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    public Set<Student> getStudents(){
        return students;
    }

    public void setStudents(Set<Student> students){
        this.students = students;
    }
}
