package com.example.demo.service;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.drools.Diseases;
import com.example.demo.drools.FoundDisease;
import com.example.demo.drools.ResultedSymptoms;
import com.example.demo.model.Disease;
import com.example.demo.model.Symptom;
import com.example.demo.model.User;
import com.example.demo.repository.DiseaseRepository;
import com.example.demo.repository.SymptomRepository;

@Service
public class SymptomService {

	@Autowired
	private SymptomRepository symptomRep;
	@Autowired
	private DiseaseRepository diseaseRep;
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

	public List<Disease> findDisease(List<Symptom> symptoms) {
		KieSession kieSession = kieContainer.newKieSession();
		List<Disease> diseases= diseaseRep.findAll();
		for (Disease disease : diseases) {
			for (Symptom symptom : disease.getSimptons()) {
				kieSession.insert(new Diseases(disease,symptom));
			}
		}
		ResultedSymptoms r= new ResultedSymptoms();
		kieSession.insert(r);
		for (Symptom s : symptoms) {
			kieSession.insert(s);
		}
		kieSession.getAgenda().getAgendaGroup("diseases").setFocus();
	    kieSession.fireAllRules();
	    kieSession.dispose();
	    
	    
	    System.out.println("r: "+r.toString());
	  //  r.sorted();
	    Map<Disease, List<Symptom>> sorted = r.getDiseaseWithSymptoms().entrySet().stream()
		        .sorted(comparingInt(e->e.getValue().size()))
		        .collect(toMap(
		                Map.Entry::getKey,
		                Map.Entry::getValue,
		                (a,b) -> {throw new AssertionError();},
		                LinkedHashMap::new
		        ));
	    
	    List<Disease> diseasesRet = new ArrayList<>();
	    for (Disease dis : sorted.keySet()) {
	    	diseasesRet.add(dis);
		}
	    Collections.reverse(diseasesRet);
		return diseasesRet;
	}
	
	public List<Symptom> findSymptom(Disease d) {
		KieSession kieSession = kieContainer.newKieSession();
		List<Disease> diseases= diseaseRep.findAll();
		for (Disease disease : diseases) {
			for (Symptom symptom : disease.getSimptons()) {
				kieSession.insert(new Diseases(disease,symptom));
			}
		}
		ResultedSymptoms r= new ResultedSymptoms();
		kieSession.insert(r);
		kieSession.insert(d);
		
		kieSession.getAgenda().getAgendaGroup("diseases").setFocus();
	    kieSession.fireAllRules();
	    kieSession.dispose();
		
	    System.out.println("r: "+r.toString());
	    
	    List<Symptom> symptomRet = new ArrayList<>();
	    for (Disease dis : r.getDiseaseWithSymptoms().keySet()) {
	    	for (Symptom s: r.getDiseaseWithSymptoms().get(dis))
	    		symptomRet.add(s);
		}
	    Collections.sort(symptomRet, new Comparator<Symptom>() {
	        @Override
	        public int compare(Symptom s1, Symptom s2) {
	            return Boolean.compare(s2.isSpecificSymptom(),s1.isSpecificSymptom());
	        }
	    });
		return symptomRet;
	}
}
