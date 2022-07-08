package com.humber.registration.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.registration.model.Student;
import com.humber.registration.repository.StudentRepository;


@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	Boolean isDone = false;
	
	//get all students from the database
	public List getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		students = (List<Student>) studentRepository.findAll();

		return students;
	}

	//find a student with id 
	public Student getStudent(String id) {
		Optional<Student> student = studentRepository.findById(id);

		return student.orElseGet(null);
	}

	//add student to the database
	public Boolean addStudent(Student student) {
		// check if student exists before adding and provide response accordingly
		Boolean doesExist = studentRepository.existsById(student.getStudid());

		if (doesExist == true) {
			isDone = false;
		} else {
			Student saved = studentRepository.save(student);

			if (saved != null) {
				isDone = true;
			}
		}
		return isDone;
	}

	//update student
	public Boolean updateStudent(String id, Student student) {

		// check if student exist, if they do then we update the record
		Boolean doesExist = studentRepository.existsById(student.getStudid());

		if (doesExist == false) {
			isDone = false;
		} else {
			Student saved = studentRepository.save(student);

			if (saved != null) {
				isDone = true;
			}
		}
		return isDone;
	}

	//delete student
	public Boolean deleteStudent(String id) {
		
		//check if student exists the delete record
		Boolean doesExist = studentRepository.existsById(id);

		if(doesExist == false) {
			isDone = false;
		}
		if(doesExist == true) {
		studentRepository.deleteById(id);
		isDone = true;
		}
		
		return isDone;
	}


}
