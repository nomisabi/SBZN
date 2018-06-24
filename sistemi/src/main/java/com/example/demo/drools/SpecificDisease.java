package com.example.demo.drools;

import org.kie.api.definition.type.Position;

public class SpecificDisease {
	@Position(0)
	private String disease;
	
	@Position(1)
	private String symptom;

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public SpecificDisease(String disease, String symptom) {
		super();
		this.disease = disease;
		this.symptom = symptom;
	}

	
}
