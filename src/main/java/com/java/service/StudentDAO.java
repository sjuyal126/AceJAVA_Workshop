package com.java.service;

import com.java.model.Student;

public interface StudentDAO {
	
	void save(Student student);
	
	public Student getById(String id);
	
	public void generateJsonReports(Student student);

}
