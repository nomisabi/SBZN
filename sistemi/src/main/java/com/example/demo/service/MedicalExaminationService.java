package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MedicalExamination;
import com.example.demo.repository.MedicalExaminationRepository;

@Service
public class MedicalExaminationService {

	@Autowired
	private MedicalExaminationRepository medicalExaminationRep;
	
	public MedicalExamination save(MedicalExamination medicalExamination) {
		return medicalExaminationRep.save(medicalExamination);
	}
	
	public List<MedicalExamination> findAll() {
		return medicalExaminationRep.findAll();
	}
	
	public Optional<MedicalExamination> findOne(Long id) {
		return medicalExaminationRep.findById(id);
	}
	
	public void delete(Long id) {
		medicalExaminationRep.deleteById(id);
	}
	
	public void delete(MedicalExamination medicalExamination) {
		medicalExaminationRep.delete(medicalExamination);
	}
}
