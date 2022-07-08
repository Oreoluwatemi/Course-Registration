package com.humber.registration.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Course {

	@Id
	private String courseid;
	private String coursename;

}
