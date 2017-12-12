package com.java.service;

import java.util.List;


import com.java.model.Student;

public interface StudentDAO {
	
	void save(List<Student> student);
	
	public Student getById(String id);
	
    public void generateJsonReports(Student students);
	
	public List<Student> getStudents();
	
	// public void setStatus(Student student);
	
	// public Student calculateTotal(Student students);
	
	// public void calculateRank(List<Student> students);

}
