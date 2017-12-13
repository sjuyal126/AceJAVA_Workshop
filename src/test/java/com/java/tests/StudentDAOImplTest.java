package com.java.tests;

import java.util.HashSet;
import java.util.Set;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import com.java.model.Student;
import com.java.model.Subject;
import com.java.repository.StudentRepository;
import com.java.service.StudentDAOImpl;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class StudentDAOImplTest {

	@Autowired(required=true)
	StudentRepository studentRepository;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testsetStatus() {
		
		Student s1 = new Student(101, "Sakshi");
		Subject sub1 = new Subject(1,"Physics", 33, s1);
		Subject sub2 = new Subject(2,"Chemistry", 33, s1);
		Subject sub3 = new Subject(3,"Maths", 33, s1);
		Set<Subject> subjects = new HashSet<>();
		subjects.add(sub1);
		subjects.add(sub2);
		subjects.add(sub3);
		
		StudentDAOImpl dao = new StudentDAOImpl();
		Mockito.when(dao.setStatus(subjects)).thenReturn("fail");
		// Assert.assertEquals("fail", dao.setStatus(subjects));
		
	}
}
