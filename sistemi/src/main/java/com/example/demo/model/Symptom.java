package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sympton")
public class Symptom implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
    private Long id;
	
    @Column
    private String name;
    
    @Column
    private boolean specificSymptom;
    
    @Column
    private int days;
    
    @Column
    private int times;
    
    @Column
    private String symptomForSpecific;
    
	public Symptom(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.specificSymptom=false;
	}	
	
	public Symptom(Long id, String name, boolean specific, int days, int times, String symptomForSpecific) {
		super();
		this.id = id;
		this.name = name;
		this.specificSymptom = specific;
		this.days = days;
		this.times = times;
		this.symptomForSpecific = symptomForSpecific;
	}



	public Symptom(Long id, String name, boolean specific) {
		super();
		this.id = id;
		this.name = name;
		this.specificSymptom = specific;
	}

	public Symptom() {
		super();
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



	public boolean isSpecificSymptom() {
		return specificSymptom;
	}

	public void setSpecificSymptom(boolean specificSymptom) {
		this.specificSymptom = specificSymptom;
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

	
}
