package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SympomForExamination implements Serializable{

	 private static final long serialVersionUID = 1L;

	 @Id @GeneratedValue
	 private Long id;
	 
	 private Boolean value;
	 
	 @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	 @JoinColumn(name = "symptom_id")
	 private Symptom symptom;

	public SympomForExamination(Long id, Boolean value, Symptom symptom) {
		super();
		this.id = id;
		this.value = value;
		this.symptom = symptom;
	}

	public SympomForExamination() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

}
