package com.example.demo.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.example.demo.model.Allergy;
import com.example.demo.model.MedicalExamination;
import com.example.demo.model.Patient;

public class PatientDTO {
	private Long id;
	 private String firstName;
	 private String lastName;
	 private Set<AllergyDTO> listOfAllergy  = new HashSet<>();
	// private Set<MedicalExiaminationDTO> examinations = new HashSet<>();
	 
	public PatientDTO(Patient p) {
		super();
		this.id = p.getId();
		this.firstName =  p.getFirstName();
		this.lastName = p.getLastName();
		for (Allergy a : p.getListOfAllergy()) {
			listOfAllergy.add(new AllergyDTO(a));
		}
	//	for (MedicalExamination me: p.getExaminations()) {
	//		this.examinations.add(new MedicalExiaminationDTO(me));
//		}		
	}
	public PatientDTO() {
		
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
	public Set<AllergyDTO> getListOfAllergy() {
		return listOfAllergy;
	}
	public void setListOfAllergy(Set<AllergyDTO> listOfAllergy) {
		this.listOfAllergy = listOfAllergy;
	}
	//public Set<MedicalExiaminationDTO> getExaminations() {
//		return examinations;
//	}
//	public void setExaminations(Set<MedicalExiaminationDTO> examinations) {
//		this.examinations = examinations;
//	}
	
	public static Patient getPatient(PatientDTO patient) {
		Set<Allergy> alergy= new HashSet<>();
		for (AllergyDTO allergyDTO : patient.getListOfAllergy()) {
			alergy.add(AllergyDTO.getAllergy(allergyDTO));
		}
		Set<MedicalExamination> me= new HashSet<>();
	//	for (MedicalExiaminationDTO meDTO : patient.getExaminations()) {
	//		me.add(MedicalExiaminationDTO.getMedicalExamination(meDTO));
	//	}
		return new Patient(patient.getId(), patient.getFirstName(),patient.getLastName(), alergy, me );
		
	}
	
	
	 
	 
}
