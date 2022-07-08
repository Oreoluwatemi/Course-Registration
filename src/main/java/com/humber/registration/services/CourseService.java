package com.humber.registration.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.registration.model.Course;
import com.humber.registration.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	Boolean isDone = false;
	
	public List getCourses() {
		List<Course> courses = new ArrayList<Course>();
		
		courses = (List<Course>)courseRepository.findAll();
		
		return courses;
	}
	
	public Course getCourse(String courseid) {
		Optional<Course> course = courseRepository.findById(courseid);
		
		return course.orElseGet(null);
	}
	
	public Boolean addCourse(Course course) {
		//check if course exist before adding
		Boolean doesExist = courseRepository.existsById(course.getCourseid());
		
		if(doesExist == false) {
			courseRepository.save(course);
			isDone = true;
		}
		if(doesExist == true) {
			isDone = false;
		}
		
		return isDone;
	}
	
	public Boolean updateCourse(Course course) {
		//check if course exists before updating
		Boolean doesExist = courseRepository.existsById(course.getCourseid());
		
		if(doesExist == false) {
			isDone = false;
		}
		if(doesExist == true) {
			courseRepository.save(course);
			isDone = true;
		}
		
		return isDone;
	}
	
	public Boolean deleteCourse(String courseid) {
		//check if course exists before deleting
		Boolean doesExist = courseRepository.existsById(courseid);
		
		if(doesExist == false) {
			isDone = false;
		}
		if(doesExist == true) {
		 courseRepository.deleteById(courseid);
		 isDone = true;
		}
		
		return isDone;
	}
}
