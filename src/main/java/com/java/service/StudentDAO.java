package com.java.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.springframework.web.multipart.MultipartFile;

import com.java.model.Student;
import com.java.model.Subject;

public interface StudentDAO {
	
	void save(List<Student> student);
	
	public void uploadFile(MultipartFile file) throws IOException, JAXBException;
	
	public void parseXML(MultipartFile file, String location) throws JAXBException;
	
	//public Student getById(String id);
	
    public void generateJsonReports(Student students);
	
	public List<Student> getStudents();
	
	public String setStatus(Set<Subject> subjects);
	
	public int calculateTotal(Set<Subject> subjects);
	
	public void calculateRank(List<Student> students);

}
