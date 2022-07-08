package com.humber.registration.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Registration {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int registerid;
	
	@ManyToOne
    @JoinColumn(name="studid")
	private Student student;
	
	@ManyToOne
    @JoinColumn(name="courseid")
	private Course course;
}
