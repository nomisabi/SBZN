package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Disease;
import com.example.demo.model.Ingredient;
import com.example.demo.repository.DiseaseRepository;

@Service
public class DiseaseService {

	@Autowired
	private DiseaseRepository diseaseRep;
	@Autowired
	private KieContainer kieContainer;
	
	public Disease save(Disease disease) {
		List<Disease> diseases= diseaseRep.findAll();
		KieSession kieSession = kieContainer.newKieSession();		
		for (Disease s : diseases) {
			kieSession.insert(s);
		}
	    kieSession.insert(disease);
	    kieSession.getAgenda().getAgendaGroup("unique").setFocus();
	    int fire = kieSession.fireAllRules();
	    kieSession.dispose();
	    System.out.println("fire "+fire);
	    if (fire!=0)
	    	return null;
		return diseaseRep.save(disease);
	}
	
	public List<Disease> findAll() {
		return diseaseRep.findAll();
	}
	
	public Optional<Disease> findOne(Long id) {
		return diseaseRep.findById(id);
	}
	
	public void delete(Long id) {
		diseaseRep.deleteById(id);
	}
	
	public void delete(Disease disease) {
		diseaseRep.delete(disease);
	}
}
