package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SympomForExamination;
import com.example.demo.model.Symptom;
import com.example.demo.model.User;
import com.example.demo.repository.SymptomForExaminantionRepository;
import com.example.demo.repository.SymptomRepository;

@Service
public class SympomForExaminationService {

	@Autowired
	private SymptomForExaminantionRepository symptomRep;
	
	public SympomForExamination save(SympomForExamination symptom) {
		return symptomRep.save(symptom);
	}
	
	public List<SympomForExamination> findAll() {
		return symptomRep.findAll();
	}
	
	public Optional<SympomForExamination> findOne(Long id) {
		return symptomRep.findById(id);
	}
	
	public void delete(Long id) {
		symptomRep.deleteById(id);
	}
	
	public void delete(SympomForExamination symptom) {
		symptomRep.delete(symptom);
	}
}
