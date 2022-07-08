package com.humber.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.humber.registration.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, String>{

}
