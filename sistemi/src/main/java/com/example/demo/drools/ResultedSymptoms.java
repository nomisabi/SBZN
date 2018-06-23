package com.example.demo.drools;

import java.util.ArrayList;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.model.Disease;
import com.example.demo.model.Symptom;

public class ResultedSymptoms {
	private Map<Disease, List<Symptom>> diseaseWithSymptoms = new HashMap<>();
	
	public ResultedSymptoms() {
		
	}
	
	public void add(Disease d, Symptom s) {
		if (diseaseWithSymptoms.containsKey(d))
			diseaseWithSymptoms.get(d).add(s);
		else {
			 List<Symptom> symp= new ArrayList<>();
			 symp.add(s);
			 diseaseWithSymptoms.put(d, symp);			
		}
	}

	public Map<Disease, List<Symptom>> getDiseaseWithSymptoms() {
		return diseaseWithSymptoms;
	}

	public void setDiseaseWithSymptoms(Map<Disease, List<Symptom>> diseaseWithSymptoms) {
		this.diseaseWithSymptoms = diseaseWithSymptoms;
	}
	
	Map<Disease, List<Symptom>> sorted = diseaseWithSymptoms.entrySet().stream()
	        .sorted(comparingInt(e->e.getValue().size()))
	        .collect(toMap(
	                Map.Entry::getKey,
	                Map.Entry::getValue,
	                (a,b) -> {throw new AssertionError();},
	                LinkedHashMap::new
	        ));

	@Override
	public String toString() {
		return "ResultedSymptoms [diseaseWithSymptoms=" + diseaseWithSymptoms + "]";
	} 
	
	
	
	
}
