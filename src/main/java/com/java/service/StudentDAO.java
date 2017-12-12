package com.java.service;

import java.util.List;
import java.util.Set;

import com.java.model.Student;
import com.java.model.Subject;

public interface StudentDAO {
	
	void save(List<Student> student);
	
	public Student getById(String id);
	
    public void generateJsonReports(Student students);
	
	public List<Student> getStudents();
	
	public String setStatus(Set<Subject> subjects);
	
	public int calculateTotal(Set<Subject> subjects); 
	
	// public void calculateRank(List<Student> students);

}
