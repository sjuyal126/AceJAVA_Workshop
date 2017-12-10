package com.java.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.model.Student;
import com.java.repository.StudentRepository;

@Service
public class StudentDAOImpl implements StudentDAO{
	
	private StudentRepository studentRepository;
	
	@Autowired
	public StudentDAOImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}
	
	@Override
	public Student getById(String id) {
		return studentRepository.findOne(id);
	}
	
	@Override
	public void generateJsonReports(Student student) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("reports\\"+student.getId()+"_student.json"), student);
		}
		catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
