package com.java.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.model.School;
import com.java.model.Student;
import com.java.repository.StudentRepository;
import com.java.service.StudentDAO;
import com.java.service.StudentDAOImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Controller
public class UploadController {
    
	static String UPLOADED_FOLDER = "E://temp//";
	
    @Autowired
    private StudentDAO studentDAOimpl;
    
    private StudentRepository studentRepository;
    
    
    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
    	
    	
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            studentDAOimpl.uploadFile(file);
    		
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        }
        
         catch (IOException e) {
            e.printStackTrace();
        }
        catch(MultipartException e) {
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
    
    @PostMapping("/viewReports")
	public ModelAndView Reports(@RequestParam( "id") String student_id) throws FileNotFoundException, IOException, ParseException {
		
    	ModelAndView model = new ModelAndView("ViewReports");
		String fileName = student_id + "_student.json";
		File file = new File(UPLOADED_FOLDER);
		File[] files = file.listFiles();
		int n = files.length;
		for(int i=0; i<=n-1; i++) {
			if(files[i].getAbsolutePath() == fileName) {
				Path p = Paths.get(files[i].getAbsolutePath());
				byte[] data = Files.readAllBytes(p);
				String jsonString = new String(data);
				model.addObject("message", jsonString);
			}
		}
		return model;
        
	}
    
    	
    }
