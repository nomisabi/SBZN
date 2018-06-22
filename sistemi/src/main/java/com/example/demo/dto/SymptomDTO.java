package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Medicine;
import com.example.demo.model.Symptom;

public class SymptomDTO {

	private Long id;
    private String name;
    private boolean specific=false;
    private int days;
    private int times;
    private String symptomForSpecific;
    
	public SymptomDTO() {
		super();
	}

	public SymptomDTO(Symptom symptom) {
		super();
		this.id = symptom.getId();
		this.name = symptom.getName();
		this.specific = symptom.isSpecificSymptom();
		this.days= symptom.getDays();
		this.times= symptom.getTimes();
		this.symptomForSpecific= symptom.getSymptomForSpecific();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getSpecific() {
		return specific;
	}

	public void setSpecific(boolean specific) {
		this.specific = specific;
	}
    
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getSymptomForSpecific() {
		return symptomForSpecific;
	}

	public void setSymptomForSpecific(String symptomForSpecific) {
		this.symptomForSpecific = symptomForSpecific;
	}

	public static Symptom getSymptom(SymptomDTO symptomDTO) {
		return new Symptom(symptomDTO.getId(),symptomDTO.getName(),symptomDTO.getSpecific(), symptomDTO.getDays(), symptomDTO.getTimes(),symptomDTO.getSymptomForSpecific());
		
	}
    
}
