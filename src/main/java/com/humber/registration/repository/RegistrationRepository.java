package com.humber.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.registration.model.Course;
import com.humber.registration.model.Registration;
import com.humber.registration.model.Student;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String>{

	//create a new method so Jpa repository can find by student 
	//and course object in the registration table
	List<Registration> findBystudent(Student student);
	
	List<Registration> findBycourse(Course course);
}
