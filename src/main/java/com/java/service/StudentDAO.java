package com.java.service;

import java.util.List;


import com.java.model.Student;

public interface StudentDAO {
	
	void save(Student student);
	
	public Student getById(String id);
	
	public void generateJsonReports(List<Student> students);
	
	public List<Student> getStudents();

}
