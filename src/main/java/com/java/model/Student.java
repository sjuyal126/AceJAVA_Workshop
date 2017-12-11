package com.java.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@XmlRootElement(name="STUDENT")
public class Student implements Serializable {

	@Id
	@XmlElement(name="ID")
	String ids;
	@XmlElement(name="NAME")
	String names;
	@XmlElement(name="PHYSICS")
	@Column(name="PHYSICS_MARKS")
	int physics_marks;
	@XmlElement(name="CHEMISTRY")
	@Column(name="CHEMISTRY_MARKS")
	int chemistry_marks;
	@XmlElement(name="MATHS")
	@Column(name="MATHS_MARKS")
	int math_marks;
	@XmlElement(name="ENGLISH")
	@Column(name="ENGLISH_MARKS")
	int english_marks;
	@XmlElement(name="GERMAN")
	@Column(name="GERMAN_MARKS")
	int german_marks;
	
	@Transient
	@JsonProperty("Total_Marks")
	int total_marks;
	@Transient
	@JsonProperty("Rank")
	int Rank;
	
	public int getTotal_marks() {
		return total_marks;
	}
	public void setTotal_marks(int total_marks) {
		this.total_marks = total_marks;
	}
	public int getRank() {
		return Rank;
	}
	public void setRank(int rank) {
		Rank = rank;
	}
	public int getMarks_physics() {
		return physics_marks;
	}
	public void setMarks_physics(int marks_physics) {
		this.physics_marks = marks_physics;
	}
	public int getMarks_chemistry() {
		return chemistry_marks;
	}
	public void setMarks_chemistry(int marks_chemistry) {
		this.chemistry_marks = marks_chemistry;
	}
	public int getMarks_maths() {
		return math_marks;
	}
	public void setMarks_maths(int marks_maths) {
		this.math_marks = marks_maths;
	}
	public int getMarks_english() {
		return english_marks;
	}
	public void setMarks_english(int marks_english) {
		this.english_marks = marks_english;
	}
	public int getMarks_german() {
		return german_marks;
	}
	public void setMarks_german(int marks_german) {
		this.german_marks = marks_german;
	}
	public String getId() {
		return ids;
	}
	public void setId(String ids) {
		this.ids = ids;
	}
	public String getName() {
		return names;
	}
	public void setName(String names) {
		this.names = names;
	}
}
