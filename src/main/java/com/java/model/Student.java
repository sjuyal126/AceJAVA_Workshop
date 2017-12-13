package com.java.model;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@XmlRootElement(name="Student")
public class Student implements Serializable{
	
	@Id
	@Column(name = "student_id")
	@XmlElement(name="ID")
	private int student_id;
	
	@XmlElement(name="Name")
	private String student_name;
	
	@Transient
	private int total_marks;
	
	@Transient
	private String STATUS;
	
	@Transient
	private int  rank;
	
	public int getRANK() {
		return rank;
	}

	public void setRANK(int rank) {
		this.rank = rank;
	}

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@XmlElementWrapper(name="Subjects")
    @XmlElement(name="subject")
	private Set<Subject>  listOfsubjects;

	public int getId() {
		return student_id;
	}

	public void setId(int id) {
		this.student_id = id;
	}

	public String getName() {
		return student_name;
	}

	public void setName(String name) {
		this.student_name = name;
	}

	public int getTotal_marks() {
		return total_marks;
	}

	public void setTotal_marks(int total_marks) {
		this.total_marks = total_marks;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public Set<Subject> getSubject() {
		return listOfsubjects;
	}

	public void setSubject(Set<Subject> subject) {
		this.listOfsubjects = subject;
	}
	
	public int hashCode() {
		return this.total_marks;
	}
	
	public boolean equals(Object obj) {
		Student student = (Student) obj;
		if(this.total_marks == student.total_marks) {
			return true;
		}
		else
			return false;
	}

	/*@Override
	public int compareTo(Object obj) {
		Student s1 = (Student) obj;
		if(this.total_marks - s1.total_marks > 0) {
			return -1;
		}
		else if(this.total_marks - s1.total_marks < 0) {
			return +1;
		}
			return 0;
	}*/
}
