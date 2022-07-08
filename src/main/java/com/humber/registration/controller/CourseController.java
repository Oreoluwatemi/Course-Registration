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

import com.humber.registration.model.Course;
import com.humber.registration.services.CourseService;

@RestController
public class CourseController {

	@Autowired
	CourseService courseservice;
	
	 private static final Logger LOGGER = LogManager.getLogger(CourseController.class);
	String message = null;
	Boolean isDone = false;
	
	@GetMapping("/courses")
	public List getCourses() {
		return courseservice.getCourses();
	}

	@GetMapping("/courses/{id}")
	public Course getCourse(@PathVariable String id) {
		return courseservice.getCourse(id);
	}
	
	@PostMapping("/courses")
	public String addCourse(@RequestBody Course course) {
		isDone = courseservice.addCourse(course);
		if (isDone == true) {
			message = "Course successfully added";
			LOGGER.info("Course with id: " +  course.getCourseid() + " added");
		} else {
			message = "Unsuccessful course record exists";
		}
		return message;
	}
	
	@PutMapping("/courses")
	public String updateCourse(@RequestParam(name = "id") String id, @RequestBody Course course) {
		isDone = courseservice.updateCourse(course);
		if (isDone == true) {
			message = "Course successfully updated";
			LOGGER.info("Course " + id + " updated");
		} else {
			message = "Unsuccessful, no coures record exists";
		}
		return message;
	}

	@DeleteMapping("/courses")
	public String deleteCourse(@RequestParam(name = "id") String id) {
		isDone = courseservice.deleteCourse(id);
		if (isDone == true) {
			message = "Course successfully deleted";
			 LOGGER.info("Course " + id + " deleted");
		} else {
			message = "Unsuccessful, no record to delete";
			LOGGER.error("Unable to delete, record with " + id + " doesn't exist");
		}
		return message;
		
	}
}
