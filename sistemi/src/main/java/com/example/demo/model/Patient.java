package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Patient implements Serializable{

	 private static final long serialVersionUID = 1L;

	 @Id @GeneratedValue
	 private Long id;
	 
	 @Column
	 private String firstName;
	 
	 @Column
	 private String lastName;
	 
	 @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	 private Set<Allergy> listOfAllergy  = new HashSet<>();
	 
	 @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	 private Set<MedicalExamination> examinations = new HashSet<>();

	public Patient(Long id, String firstName, String lastName, Set<Allergy> listOfAllergy,
			Set<MedicalExamination> examinations) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.listOfAllergy = listOfAllergy;
		this.examinations = examinations;
	}

	public Patient() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Allergy> getListOfAllergy() {
		return listOfAllergy;
	}

	public void setListOfAllergy(Set<Allergy> listOfAllergy) {
		this.listOfAllergy = listOfAllergy;
	}

	public Set<MedicalExamination> getExaminations() {
		return examinations;
	}

	public void setExaminations(Set<MedicalExamination> examinations) {
		this.examinations = examinations;
	}
	 
	 
}
