package com.humber.registration.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.humber.registration.model.Course;
import com.humber.registration.model.Register;
import com.humber.registration.model.Registration;
import com.humber.registration.model.Student;
import com.humber.registration.services.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	 private static final Logger LOGGER = LogManager.getLogger(RegistrationController.class);
	String message = null;
	Boolean isDone;

	@GetMapping("/register")
	public List getRegisteredCourses() {
		return registrationService.getRegistrations();
	}

	@GetMapping("/register/students/{studid}")
	public List getCoursesRegisteredByStudent(@PathVariable String studid) {
		List<Course> coursesRegisteredByStudent = registrationService.getCoursesRegisteredByStudent(studid);

		return coursesRegisteredByStudent;
	}

	@PostMapping("/register")
	public String registerCourse(@RequestBody Register register) {
		isDone = registrationService.register(register);
		if (isDone == true) {
			message = "Successfully registered";
			LOGGER.info("Registered course to the database");
		} else {
			message = "Unable to register, student or course doesn't exist";
			LOGGER.error("Unable to register, student or course doesn't exist");
		}
		return message;
	}

	@GetMapping("/register/courses/{courseid}")
	public List getStudentsRegisteredInCourse(@PathVariable String courseid) {
		List<Student> studentsRegisteredInCourse = registrationService.getStudentsRegisteredInCourse(courseid);

		return studentsRegisteredInCourse;
	}
}
