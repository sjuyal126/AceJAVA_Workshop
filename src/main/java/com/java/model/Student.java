package com.java.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@XmlRootElement(name="STUDENT")
public class Student implements Serializable, Comparable {

	@Id
	@XmlElement(name="ID")
	int student_id;
	
	@XmlElement(name="NAME")
	String student_names;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="student_id")
	private Set<Subject> subjects;
	
	@Transient
	@JsonProperty("Total_Marks")
	int total_marks;
	
	@Transient
	int Rank;
	
	@Transient
	String STATUS;
	
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public int getTotal_marks() {
		return total_marks;
	}
	public void setTotal_marks(int total_marks) {
		this.total_marks = total_marks;
	}
	public int getRank() {
		return Rank;
	}
	public void setRank(int Rank) {
		this.Rank = Rank;
	}
	
	public Set<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	public int getId() {
		return student_id;
	}
	public void setId(int ids) {
		this.student_id = ids;
	}
	public String getName() {
		return student_names;
	}
	public void setName(String names) {
		this.student_names = names;
	}
	
	public int compareTo(Object obj) {
		Student s1 = (Student) obj;
		if(this.total_marks - s1.total_marks > 0) {
			return -1;
		}
		else
			return +1;
	}

}
