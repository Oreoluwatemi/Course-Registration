package com.humber.registration.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.registration.model.Course;
import com.humber.registration.model.Register;
import com.humber.registration.model.Registration;
import com.humber.registration.model.Student;
import com.humber.registration.repository.CourseRepository;
import com.humber.registration.repository.RegistrationRepository;
import com.humber.registration.repository.StudentRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	Boolean isDone;
	
	//get all registrations
	public List getRegistrations() {
		List<Registration> allRegistrations = (List<Registration>) registrationRepository.findAll();
		
		return allRegistrations;
	}
	
	
	//add a registration to the database
	public Boolean register(Register register) {
		
		// check if student exists
		Optional<Student> student = studentRepository.findById(register.getStudid());
		
		//check if course exists
		Optional<Course> course = courseRepository.findById(register.getCourseid());
		
		//if both exists then we add registration to the database
		if (student.isPresent()) {
			if(course.isPresent()) {
				Registration registration = new Registration();
				registration.setStudent(student.get());
				registration.setCourse(course.get());
				registrationRepository.save(registration);
				isDone = true;
			}else {
			isDone = false;
			}
		} else {
			isDone = false;
		}
		return isDone;
	}
 
	//fetch all courses registered by a student with student id passed
	public List getCoursesRegisteredByStudent(String studid) {
		
		//first check student with student id exists
		Optional<Student> student = studentRepository.findById(studid);
		
		//if student exist then we check if the student has made any registration
		if(student.isPresent()) {
			
		//add all the registration made by the student to a list
		List<Registration> registerationByStudent = registrationRepository.findBystudent(student.get());
		
		//if the student hasn't made any registration i.e list is empty, then we return null
		if(registerationByStudent == null) {
			return null;
		}
		
		
		List<Course> coursesRegisteredByStudent = new ArrayList<Course>(); 
		
		//we loop through the student registration and get the course details
		for (Registration r : registerationByStudent){
			Optional<Course> course = courseRepository.findById(r.getCourse().getCourseid());
			
			//we then add the course details to a list
			if(course.isPresent()) {
			coursesRegisteredByStudent.add(course.get());
			}
		}
		
		//all courses registered by the student is returned
		return coursesRegisteredByStudent;
		}
		else {
			return null;
		}
	}
	
	//fetch all students registered in a course
	public List getStudentsRegisteredInCourse(String courseid) {
		
		//first check if course exists
		Optional<Course> course = courseRepository.findById(courseid);
		
		//if course exists then find all registrations with the course in the database
		if(course.isPresent()) {
		List<Registration> registrationByCourse = registrationRepository.findBycourse(course.get());
		
		//if there is no registration with the course in the databse then we return null
		if(registrationByCourse == null) {
			return null;
		}
		
		List<Student> studentsRegisteredInCourse = new ArrayList<Student>();
		
		//loop through all registrations with the course details and get the student details
		for(Registration r : registrationByCourse) {
			
			//check if student exist then add the student to a list
			Optional<Student> student = studentRepository.findById(r.getStudent().getStudid());
			if(student.isPresent()) {
				studentsRegisteredInCourse.add(student.get());
			}
		}
		
		//return all the students registered in the course
		return studentsRegisteredInCourse;
		}
		else {
			return null;
		}
	}
	
	

}
