package com.example.demo.dto;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.model.SympomForExamination;
import com.example.demo.model.Symptom;

public class SymptomForExaminationDTO {

	private Long id;
	private Boolean value;
    private SymptomDTO symptom;
    
    public SymptomForExaminationDTO() {
    	
    }
    
    public SymptomForExaminationDTO(SympomForExamination se) {
    	id=se.getId();
    	value=se.getValue();
    	if (se.getSymptom()!=null)
    		symptom=new SymptomDTO(se.getSymptom());	
    	
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

	public SymptomDTO getSymptom() {
		return symptom;
	}

	public void setSymptom(SymptomDTO symptom) {
		this.symptom = symptom;
	}
    
    public static SympomForExamination getSymptom(SymptomForExaminationDTO seDTO) {
    	Symptom symptom = null;
    	if (seDTO.getSymptom()!=null)
    		symptom=SymptomDTO.getSymptom(seDTO.getSymptom());
    	return new SympomForExamination(seDTO.getId(), seDTO.getValue(), symptom);
    }
}
