package com.java.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name="STUDENT")
public class Student {
	@Id
	@XmlElement(name="ID")
	String ids;
	@XmlElement(name="NAME")
	String names;
	
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
