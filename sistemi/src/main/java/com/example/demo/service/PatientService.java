package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.drools.MedicalExaminationEvent;
import com.example.demo.model.MedicalExamination;
import com.example.demo.model.Patient;
import com.example.demo.model.User;
import com.example.demo.repository.MedicalExaminationRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRep;
	@Autowired
	private UserRepository userRep;
	@Autowired
	private KieContainer kieContainer;
	@Autowired
	private MedicalExaminationRepository medRep;
	
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
		List<Patient> patients =findAll();
		List<Patient> returnVal =new ArrayList<>();
		System.out.println(doctor.get().getId());
		for (Patient patient : patients) {
			if (patient.getDoctor()!=null)
				if (patient.getDoctor().getId()==doctor.get().getId())
					returnVal.add(patient);
		}
		return returnVal;
	}

	public List<Patient> findAllWithoutDoctor() {
		List<Patient> patients =findAll();
		List<Patient> returnVal =new ArrayList<>();
		for (Patient patient : patients) {
			if (patient.getDoctor()==null)
					returnVal.add(patient);
		}
		return returnVal;
	}

	public List<Patient> getReport1() {
		
		KieSession kieSession = kieContainer.newKieSession();
		Set<Long> patients = new HashSet<Long>();
		kieSession.insert(patients);
		List<MedicalExamination> me= medRep.findAll();
		for (MedicalExamination medicalExamination : me) {
			kieSession.insert(new MedicalExaminationEvent(medicalExamination));
		}	 
		kieSession.getAgenda().getAgendaGroup("report-chr").setFocus();
	    int fire = kieSession.fireAllRules();
	    kieSession.dispose();
	    System.out.println(patients.size());
	    List<Patient> pRet= new ArrayList<>();
	    for (Long p : patients) {
			Optional<Patient> pat= patientRep.findById(p);
			if (pat.get()!=null)
				pRet.add(pat.get());
		}
		return pRet;
	}
	
	public List<Patient> getReport2() {
		
		KieSession kieSession = kieContainer.newKieSession();
		Set<Long> patients = new HashSet<Long>();
		kieSession.insert(patients);
		List<MedicalExamination> me= medRep.findAll();
		for (MedicalExamination medicalExamination : me) {
			kieSession.insert(new MedicalExaminationEvent(medicalExamination));
		}	    
		kieSession.getAgenda().getAgendaGroup("report-add").setFocus();
	    int fire = kieSession.fireAllRules();
	    kieSession.dispose();
	    List<Patient> pRet= new ArrayList<>();
	    for (Long p : patients) {
			Optional<Patient> pat= patientRep.findById(p);
			if (pat.get()!=null)
				pRet.add(pat.get());
		}
		return pRet;
	}
	
	public List<Patient> getReport3() {
		
		KieSession kieSession = kieContainer.newKieSession();
		Set<Long> patients = new HashSet<Long>();
		kieSession.insert(patients);
		List<MedicalExamination> me= medRep.findAll();
		System.out.println("me: "+me.size());
		for (MedicalExamination medicalExamination : me) {
			MedicalExaminationEvent mevent= new MedicalExaminationEvent(medicalExamination);
			if (mevent==null)
				System.out.println("vmiert null ");
			if (kieSession==null)
				System.out.println("kieSession null ");
			kieSession.insert(mevent);
		}	    
		kieSession.getAgenda().getAgendaGroup("report-imm").setFocus();
	    int fire = kieSession.fireAllRules();
	    kieSession.dispose();
	    List<Patient> pRet= new ArrayList<>();
	    for (Long p : patients) {
			Optional<Patient> pat= patientRep.findById(p);
			if (pat.get()!=null)
				pRet.add(pat.get());
		}
		return pRet;
	}
}
