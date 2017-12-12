package com.java.service;


import java.io.File;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.model.Student;
import com.java.model.Subject;
import com.java.repository.StudentRepository;

@Service
public class StudentDAOImpl implements StudentDAO{
	
	private StudentRepository studentRepository;
	private List<Student> list;
	
	@Autowired
	public StudentDAOImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	@Override
	public void save(List<Student> student) {
		
			for(Student s1 : student) {
				studentRepository.save(s1);
			}
	}
	
	@Override
	public Student getById(String id) {
		return studentRepository.findOne(id);
	}
	
	@Override
	public void generateJsonReports(Student students) {
		
		ObjectMapper mapper = new ObjectMapper();
		
				try {
					mapper.writerWithDefaultPrettyPrinter().writeValue(new File("reports\\"+students.getId()+"_student.json"), students);
					}
				catch(JsonGenerationException e) {
					
				}
				catch(JsonMappingException e) {
					
				}
				catch(IOException e) {
					
				}
			}
	
	@Override
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		studentRepository.findAll()
		.forEach(students::add);
		return students;
	}
	@Override
	public int calculateTotal(Set<Subject> subjects) {
		int total = subjects.stream().mapToInt(t -> t.getSubject_marks()).sum();
		return total;
	}
	@Override
	public String setStatus(Set<Subject> subjects) {
		/*Iterator<Subject> itr = subjects.iterator();
		while(itr.hasNext()) {
			Subject sub = itr.next();
			if(sub.getSubject_marks() < 35) {
				return "FAIL";
			}
			else
				return "PASS";
		}
		return "PASS";*/
		Stream<Subject> subject = subjects.stream().filter(t -> t.getSubject_marks() < 35);
		if(subject.count() > 0)
			return "FAIL";
		else
			return "PASS";
		
	}
	
	/*@Override
	public void calculateRank(List<Student> students) {
		students.stream().filter(t -> t.getSTATUS() == "PASS").collect(Collectors.toList());
		students.stream().sorted();
		students.stream().findFirst().get().setRank(1);
	}*/
	/*@Override
	public void setStatus(Student student) {
		if (student.getMarks_chemistry() < 35 || student.getMarks_english() < 35 || student.getMarks_german() < 35 || student.getMarks_maths() < 35 || student.getMarks_physics() < 35 ) {
			student.setSTATUS("FAIL");
		}
		else
			student.setSTATUS("PASS");
	}*/
}	
