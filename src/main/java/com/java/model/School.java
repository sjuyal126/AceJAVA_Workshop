package com.java.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="School")
public class School {
	@XmlElement(name="Student")
	public List<Student> records;

	public List<Student> getStudents() {
		return records;
	}

	public void setStudents(List<Student> records) {
		this.records = records;
	}
	
}
