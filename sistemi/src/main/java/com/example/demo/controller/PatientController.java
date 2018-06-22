package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MedicineDTO;
import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Medicine;
import com.example.demo.model.Patient;
import com.example.demo.service.DiseaseService;
import com.example.demo.service.PatientService;

@RestController
@RequestMapping(value = "/api")
public class PatientController {

	@Autowired
	PatientService patientService;
	
	@RequestMapping(value = "/patients", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> newPatient(@RequestBody PatientDTO patientDTO) {
		Patient m= patientDTO.getPatient(patientDTO);
		// findBy name
		m=patientService.save(m);
		if (m==null)
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);	
		return new ResponseEntity<>( HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "user/{id}/patients", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<List<Patient>> getPatients(@PathVariable Long id){
			List<Patient> med= patientService.findAllByDoctor(id);
			return new ResponseEntity<>(med, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/patients/{id}", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
		Optional<Patient> m= patientService.findOne(id);
		if (m.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		return new ResponseEntity<>(m.get(),HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/patients/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<Void> deletePatient(
			@PathVariable Long id) {
		Optional<Patient> m= patientService.findOne(id);
		if (m.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		patientService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);		
	}
}
