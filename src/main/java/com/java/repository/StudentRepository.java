package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.java.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

}
