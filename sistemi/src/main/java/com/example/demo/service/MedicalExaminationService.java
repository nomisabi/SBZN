package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.drools.MedicalExaminationEvent;
import com.example.demo.drools.ResultDisease;
import com.example.demo.dto.SymptomForExaminationDTO;
import com.example.demo.model.Disease;
import com.example.demo.model.MedicalExamination;
import com.example.demo.model.Patient;
import com.example.demo.model.SympomForExamination;
import com.example.demo.model.Symptom;
import com.example.demo.repository.DiseaseRepository;
import com.example.demo.repository.MedicalExaminationRepository;

@Service
public class MedicalExaminationService {

	@Autowired
	private MedicalExaminationRepository medicalExaminationRep;
	@Autowired
	private DiseaseRepository diseaseRep;
	@Autowired
	private KieContainer kieContainer;
	
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

	public List<MedicalExamination> findAllByDoctor(Long id) {
		List<MedicalExamination> mes =findAll();
		List<MedicalExamination> returnVal =new ArrayList<>();
		for (MedicalExamination me : mes) {
			if (me.getDoctor()!=null)
				if (me.getDoctor().getId()==id)
					returnVal.add(me);
		}
		return returnVal;
	}

	public Disease startResoner(MedicalExamination m) {
		
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(m);
		ResultDisease result = new ResultDisease();
		System.out.println(result.toString());
		kieSession.insert(result);
		for (SympomForExamination s : m.getSympom()) {
			if (s.getValue())
				kieSession.insert(s.getSymptom());
		}
		List<MedicalExamination> me= medicalExaminationRep.findAll();
		System.out.println("me: "+me.size());
		for (MedicalExamination medicalExamination : me) {
			MedicalExaminationEvent mevent= new MedicalExaminationEvent(medicalExamination);
			kieSession.insert(mevent);
		}	    
		
		
		//kieSession.getAgenda().getAgendaGroup("report-imm").setFocus();
	    int fire = kieSession.fireAllRules();
	    kieSession.dispose();
	    
	    System.out.println(result.toString());
	    Optional<Disease> dis= diseaseRep.findById(result.getDiseaseId());
	    if (dis==null)
	    	return null;
		return dis.get();
	}
}
