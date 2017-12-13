package com.java.tests;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.java.repository.StudentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class FileUploadControllerTest {

	@Autowired
	private StudentRepository studentRepository;
	
	
}
