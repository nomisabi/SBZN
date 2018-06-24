package com.example.demo.drools;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.definition.type.Position;

import com.example.demo.model.Disease;
import com.example.demo.model.Symptom;

public class FoundDisease {
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

		public FoundDisease(Disease disease, Symptom symptom) {
			super();
			this.disease = disease;
			this.symptom = symptom;
		}
	
	
	
}
