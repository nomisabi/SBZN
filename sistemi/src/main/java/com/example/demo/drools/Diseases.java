package com.example.demo.drools;

import org.kie.api.definition.type.Position;

import com.example.demo.model.Disease;
import com.example.demo.model.Symptom;

public class Diseases {
	/*
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

		public Diseases(String disease, String symptom) {
			super();
			this.disease = disease;
			this.symptom = symptom;
		}
*/
    @Position(0)
    private Disease disease;

    @Position(1)
    private Symptom symptom;

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public Diseases(Disease disease, Symptom symptom) {
		super();
		this.disease = disease;
		this.symptom = symptom;
	}
    
    


}
