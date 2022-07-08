package com.humber.registration.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Student {

	@Id
	private String studid;
	private String firstname;
	private String lastname;

	
}
