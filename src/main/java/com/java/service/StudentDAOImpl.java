package com.java.service;


import java.io.File;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.model.School;
import com.java.model.Student;
import com.java.model.Subject;
import com.java.repository.StudentRepository;

@Service
public class StudentDAOImpl implements StudentDAO{
	
	static String UPLOADED_FOLDER = "E://temp//";
	@Autowired
	private StudentRepository studentRepository;
	private List<Student> list;
	
	@Override
	public void save(List<Student> student) {
		
			for(Student s1 : student) {
				studentRepository.save(s1);
			}
			File files = new File("reports\\");
    		if(files.isDirectory()) {
    			if(files.listFiles().length>0) {
    				File[] f = files.listFiles();
    				int n = f.length;
    				for( int i=0; i<=n-1; i++) {
    					f[i].delete();
    				}
    				
    			}
    		}
    		
    		for(Student s1 : this.getStudents()) {
    			s1.setSTATUS(this.setStatus(s1.getSubject()));
    			s1.setTotal_marks(this.calculateTotal(s1.getSubject()));	
    		}
    		
    		this.calculateRank(this.getStudents());
    		
    		ExecutorService executors = Executors.newFixedThreadPool(5);
    		for(Student s1 : this.getStudents()) {
    		executors.submit(() -> {
 			this.generateJsonReports(s1);
    		});
    		
	}
	}
	@Override
	public void uploadFile(MultipartFile file) throws IOException, JAXBException {
		byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
        
        this.parseXML(file, UPLOADED_FOLDER);
	}
	
	@Override
	public void parseXML(MultipartFile file, String location) throws JAXBException {
		File file1 = new File(UPLOADED_FOLDER+file.getOriginalFilename());
		JAXBContext jaxbContext = JAXBContext.newInstance(School.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		School school = (School) jaxbUnmarshaller.unmarshal(file1);
		
		this.save(school.getStudents());
	}
	
	@Override
	public String setStatus(Set<Subject> subjects) {
		Stream<Subject> subject = subjects.stream().filter(t -> t.getSubject_marks() < 35);
		if(subject.count() > 0)
			return "FAIL";
		else
			return "PASS";
			}
	
	
	@Override
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		studentRepository.findAll()
		.forEach(students::add);
		return students;
	}
	
	
	@Override
	public int calculateTotal(Set<Subject> subjects) {
		int total = subjects.stream().mapToInt(t -> t.getSubject_marks()).sum();
		return total;
	}
	
	@Override
	public void generateJsonReports(Student students) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("reports\\"+students.getId()+"_student.json"), students);
			}
		catch(JsonGenerationException e) {
			
		}
		catch(JsonMappingException e) {
			
		}
		catch(IOException e) {
			
		}
	}
	
	@Override
	public void calculateRank(List<Student> students) {
		Set<Integer> set = new TreeSet<>();
		Map<Integer, Integer> map = new LinkedHashMap<>();
		List<Student> passStudents = students.stream()
				.filter(t -> t.getSTATUS().equalsIgnoreCase("pass"))
				.collect(Collectors.toList());
		
		for(Student s1 : passStudents) {
			set.add(s1.getTotal_marks());
		}
		List<Integer> list = new ArrayList<>(set);
		list.sort((Integer i1, Integer i2) -> i2-i1);
		
		Iterator itr = list.iterator();
		int i =1;
		while(itr.hasNext()) {
			Integer marks = (Integer) itr.next();
			map.put(marks, i);
			i++;
		}
		
		for(Student s1 : passStudents) {
			s1.setRANK(map.get(s1.getTotal_marks()));
		}
		
	}
	
}

	