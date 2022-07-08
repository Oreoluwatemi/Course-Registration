package com.humber.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.registration.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String>{

}
