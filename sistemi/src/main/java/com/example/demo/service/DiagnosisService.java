package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Diagnosis;
import com.example.demo.repository.DiagnosisRepository;

@Service
public class DiagnosisService {

	@Autowired
	private DiagnosisRepository diagnosisRep;
	
	public Diagnosis save(Diagnosis diagnosis) {
		return diagnosisRep.save(diagnosis);
	}
	
	public List<Diagnosis> findAll() {
		return diagnosisRep.findAll();
	}
	
	public Optional<Diagnosis> findOne(Long id) {
		return diagnosisRep.findById(id);
	}
	
	public void delete(Long id) {
		diagnosisRep.deleteById(id);
	}
	
	public void delete(Diagnosis diagnosis) {
		diagnosisRep.delete(diagnosis);
	}
}
