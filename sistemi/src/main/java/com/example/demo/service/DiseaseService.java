package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Disease;
import com.example.demo.repository.DiseaseRepository;

@Service
public class DiseaseService {

	@Autowired
	private DiseaseRepository diagnosisRep;
	
	public Disease save(Disease diagnosis) {
		return diagnosisRep.save(diagnosis);
	}
	
	public List<Disease> findAll() {
		return diagnosisRep.findAll();
	}
	
	public Optional<Disease> findOne(Long id) {
		return diagnosisRep.findById(id);
	}
	
	public void delete(Long id) {
		diagnosisRep.deleteById(id);
	}
	
	public void delete(Disease disease) {
		diagnosisRep.delete(disease);
	}
}
