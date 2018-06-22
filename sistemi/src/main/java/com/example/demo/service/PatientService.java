package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Patient;
import com.example.demo.model.User;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRep;
	@Autowired
	private UserRepository userRep;
	
	public Patient save(Patient patient) {
		return patientRep.save(patient);
	}
	
	public List<Patient> findAll() {
		return patientRep.findAll();
	}
	
	public Optional<Patient> findOne(Long id) {
		return patientRep.findById(id);
	}
	
	public void delete(Long id) {
		patientRep.deleteById(id);
	}
	
	public void delete(Patient patient) {
		patientRep.delete(patient);
	}

	public List<Patient> findAllByDoctor(Long id) {
		Optional<User> doctor = userRep.findById(id);
		if (doctor.get()==null)
			return null;
		List<Patient> returnVal = new ArrayList<>();
		returnVal.addAll( doctor.get().getPatients());
		return returnVal;
	}
}
