package com.java.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	public void generateJsonReports(List<Student> students) {
		
		ObjectMapper mapper = new ObjectMapper();
		ExecutorService executors = Executors.newFixedThreadPool(5);
		executors.execute(() -> {
			for(int i=0; i<=students.size()-1; i++) {
				Student student = studentRepository.findOne(students.get(i).getId());
				try {
					mapper.writerWithDefaultPrettyPrinter().writeValue(new File("reports\\"+student.getId()+"_student.json"), student);
					System.out.println(Thread.currentThread().getName());
					}
				catch(JsonGenerationException e) {
					
				}
				catch(JsonMappingException e) {
					
				}
				catch(IOException e) {
					
				}
			}
		});
		
	}
	@Override
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		studentRepository.findAll()
		.forEach(students::add);
		return students;
	}

	
}
