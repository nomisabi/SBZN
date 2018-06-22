package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Symptom;
import com.example.demo.model.User;
import com.example.demo.repository.SymptomRepository;

@Service
public class SymptomService {

	@Autowired
	private SymptomRepository symptomRep;
	@Autowired
	private KieContainer kieContainer;
	
	public Symptom save(Symptom symptom) {
		List<Symptom> symptoms= symptomRep.findAll();
		
		KieSession kieSession = kieContainer.newKieSession();
		
		for (Symptom s : symptoms) {
			kieSession.insert(s);
		}
	    kieSession.insert(symptom);
	    kieSession.getAgenda().getAgendaGroup("unique").setFocus();
	    int fire = kieSession.fireAllRules();
	    kieSession.dispose();
	    System.out.println("fire "+fire);
	    if (fire!=0)
	    	return null;
		return symptomRep.save(symptom);
	}
	
	public List<Symptom> findAll() {
		return symptomRep.findAll();
	}
	
	public Optional<Symptom> findOne(Long id) {
		return symptomRep.findById(id);
	}
	
	public void delete(Long id) {
		symptomRep.deleteById(id);
	}
	
	public void delete(Symptom symptom) {
		symptomRep.delete(symptom);
	}
}
