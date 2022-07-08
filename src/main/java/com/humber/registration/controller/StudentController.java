package com.humber.registration.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humber.registration.model.Student;
import com.humber.registration.services.StudentService;



@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	 private static final Logger LOGGER = LogManager.getLogger(StudentController.class);

	String message = null;
	Boolean isDone = false;
	
	@GetMapping("/students")
	public List getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable String id) {
		return studentService.getStudent(id);
	}

	@PostMapping("/students")
	public String addStudent(@RequestBody Student student) {
		isDone = studentService.addStudent(student);
		if (isDone == true) {
			message = "Student successfully added";
			LOGGER.info("Student with id " + student.getStudid() + " has been added to the database");
		} else {
			message = "Unsuccessful student record exists";
		}
		return message;
	}

	@PutMapping("/students")
	public String updateStudent(@RequestParam(name = "id") String id, @RequestBody Student student) {
		isDone = studentService.updateStudent(id, student);
		if (isDone == true) {
			message = "Student successfully updated";
			LOGGER.info("Student with id " + id + " has been updated");
		} else {
			message = "Unsuccessful, no record";
		}
		return message;
	}

	@DeleteMapping("/students")
	public String deleteStudent(@RequestParam(name = "id") String id) {
		isDone = studentService.deleteStudent(id);
		if (isDone == true) {
			message = "Student successfully deleted";
			LOGGER.info("Student with id: " + id + " has been deleted");
		} else {
			message = "Unsuccessful, no record to delete";
		}
		return message;
		
	}

}
