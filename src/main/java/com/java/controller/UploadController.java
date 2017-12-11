package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.model.School;
import com.java.model.Student;
import com.java.service.StudentDAO;
import com.java.service.StudentDAOImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Controller
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D://temp//";
    List<Student> listOfStudents;
    
    @Autowired
    private StudentDAO studentDAOimpl;
    
    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            
            File file1 = new File(UPLOADED_FOLDER+file.getOriginalFilename());
			JAXBContext jaxbContext = JAXBContext.newInstance(School.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			School school = (School) jaxbUnmarshaller.unmarshal(file1);
		
            for(Student s1 : school.getStudents()) {
            	studentDAOimpl.save(s1);
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
    		
    		studentDAOimpl.generateJsonReports(studentDAOimpl.getStudents());
    		

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
          catch (JAXBException e) {
        	  e.printStackTrace();
          }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}