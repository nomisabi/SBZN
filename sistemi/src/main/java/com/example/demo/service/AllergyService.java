package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Allergy;
import com.example.demo.model.Medicine;
import com.example.demo.model.Patient;
import com.example.demo.model.User;
import com.example.demo.repository.AllergyRepository;
import com.example.demo.repository.PatientRepository;

@Service
public class AllergyService {

	@Autowired
	private AllergyRepository allergyRep;
	
	@Autowired
	private PatientRepository patientRep;
	
	@Autowired
	private KieContainer kieContainer;
	
	public Allergy save(Allergy allergy) {
		return allergyRep.save(allergy);
	}
	
	public List<Allergy> findAll() {
		return allergyRep.findAll();
	}
	
	public Optional<Allergy> findOne(Long id) {
		return allergyRep.findById(id);
	}
	
	public void delete(Long id) {
		allergyRep.deleteById(id);
	}
	
	public void delete(Allergy allergy) {
		allergyRep.delete(allergy);
	}
	
	public List<Medicine> check(List<Medicine> medicines, Patient p){
		List<Medicine> allergetic= new ArrayList<>();
		for (Medicine m : medicines) {
			KieSession kieSession = kieContainer.newKieSession();
			for (Allergy a : p.getListOfAllergy()) {
				System.out.println(a.toString());
				kieSession.insert(a);
			}	
			System.out.println(m.toString());
			kieSession.insert(m);
		    kieSession.getAgenda().getAgendaGroup("allergy").setFocus();
		    int fire = kieSession.fireAllRules();
		    kieSession.dispose();
		    System.out.println("fire "+fire);
		    if (fire!=0)
		    	allergetic.add(m);
		}
		return allergetic;
	}
}
