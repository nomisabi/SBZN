package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Medicine;
import com.example.demo.model.Symptom;
import com.example.demo.repository.MedicineRepository;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepository medicineRep;
	@Autowired
	private KieContainer kieContainer;
	
	public Medicine save(Medicine medicine) {
		List<Medicine> medicines= medicineRep.findAll();
		KieSession kieSession = kieContainer.newKieSession();		
		for (Medicine s : medicines) {
			kieSession.insert(s);
		}
	    kieSession.insert(medicine);
	    kieSession.getAgenda().getAgendaGroup("unique").setFocus();
	    int fire = kieSession.fireAllRules();
	    kieSession.dispose();
	    System.out.println("fire "+fire);
	    if (fire!=0)
	    	return null;
		return medicineRep.save(medicine);
	}
	
	public List<Medicine> findAll() {
		return medicineRep.findAll();
	}
	
	public Optional<Medicine> findOne(Long id) {
		return medicineRep.findById(id);
	}
	
	public void delete(Long id) {
		medicineRep.deleteById(id);
	}
	
	public void delete(Medicine medicine) {
		medicineRep.delete(medicine);
	}
}
