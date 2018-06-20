package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Symptom;
import com.example.demo.repository.SymptomRepository;

@Service
public class SymptomService {

	@Autowired
	private SymptomRepository symptomRep;
	
	public Symptom save(Symptom symptom) {
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
