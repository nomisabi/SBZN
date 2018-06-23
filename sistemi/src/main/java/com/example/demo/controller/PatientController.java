package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DiagnosisDTO;
import com.example.demo.dto.DiseaseDTO;
import com.example.demo.dto.MedicalExiaminationDTO;
import com.example.demo.dto.MedicineDTO;
import com.example.demo.dto.PatientDTO;
import com.example.demo.dto.SymptomDTO;
import com.example.demo.model.Allergy;
import com.example.demo.model.Diagnosis;
import com.example.demo.model.Disease;
import com.example.demo.model.MedicalExamination;
import com.example.demo.model.Medicine;
import com.example.demo.model.Patient;
import com.example.demo.model.SympomForExamination;
import com.example.demo.model.Symptom;
import com.example.demo.model.User;
import com.example.demo.service.AllergyService;
import com.example.demo.service.DiagnosisService;
import com.example.demo.service.DiseaseService;
import com.example.demo.service.MedicalExaminationService;
import com.example.demo.service.MedicineService;
import com.example.demo.service.PatientService;
import com.example.demo.service.SympomForExaminationService;
import com.example.demo.service.SymptomService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class PatientController {

	@Autowired
	PatientService patientService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AllergyService allergyService;
	
	@Autowired
	MedicalExaminationService examinationService;
	
	@Autowired
	SympomForExaminationService sympexaminationService;
	
	@Autowired
	DiagnosisService diagnosisService;
	
	@Autowired
	MedicineService medicineService;
	
	@Autowired
	SymptomService symptomService;
	
	@Autowired
	DiseaseService diseaseService;
	
	@RequestMapping(value = "user/{id}/patients", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> newPatient(@PathVariable Long id,@RequestBody PatientDTO patientDTO) {
		Optional<User> dr=userService.findOne(id);
		if (dr.get()==null)
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		Patient m= patientDTO.getPatient(patientDTO);
		m.setDoctor(dr.get());
		Set<Allergy> a= new HashSet<>();
		for (Allergy al : m.getListOfAllergy()) {
			al= allergyService.save(al);
			a.add(al);
		}
		m.setListOfAllergy(a);
		m=patientService.save(m);
		if (m==null)
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);	
		//Set<Patient> patients = dr.get().getPatients();
		//patients.add(m);
		//dr.get().setPatients(patients);
		//userService.update(dr.get());
		
		return new ResponseEntity<>( HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "user/{id}/patients", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<List<PatientDTO>> getPatients(@PathVariable Long id){
			List<Patient> med= patientService.findAllByDoctor(id);
			List<PatientDTO> ret= new ArrayList<>();
			for (Patient patient : med) {
				ret.add(new PatientDTO(patient));
			}
			return new ResponseEntity<>(ret, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "patients/{id}/examinations", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> newExamination(@PathVariable Long id,@RequestBody MedicalExiaminationDTO examinationDTO) {
		Optional<Patient> p=patientService.findOne(id);
		if (p.get()==null)
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		MedicalExamination m= examinationDTO.getMedicalExamination(examinationDTO);
		m.setPatient(p.get());
		Set<SympomForExamination> symp = new HashSet<>();
		
		for (SympomForExamination sfe : m.getSympom()) {
			sfe= sympexaminationService.save(sfe);
			symp.add(sfe);		}
		m.setSympom(symp);
		m.setDiagnosis(null);
		m=examinationService.save(m);
		if (m==null)
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);	
		Set<MedicalExamination> me = p.get().getExaminations();
		me.add(m);
		p.get().setExaminations(me);
		patientService.save(p.get());
		
		return new ResponseEntity<>( HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "user/{id}/examinations", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<List<MedicalExiaminationDTO>> getExamination(@PathVariable Long id){
			List<MedicalExamination> med= (List<MedicalExamination>) examinationService.findAllByDoctor(id);
			List<MedicalExiaminationDTO> ret= new ArrayList<>();
			for (MedicalExamination patient : med) {
				ret.add(new MedicalExiaminationDTO(patient));
			}
			return new ResponseEntity<>(ret, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "patients/no_dr", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<List<PatientDTO>> getPatientsNoDR(){
			List<Patient> med= patientService.findAllWithoutDoctor();
			List<PatientDTO> ret= new ArrayList<>();
			for (Patient patient : med) {
				ret.add(new PatientDTO(patient));
			}
			return new ResponseEntity<>(ret, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/patients/{id}", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<PatientDTO> getPatient(@PathVariable Long id) {
		Optional<Patient> m= patientService.findOne(id);
		if (m.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		
		return new ResponseEntity<>(new PatientDTO(m.get()),HttpStatus.OK);	
	}
	
	@RequestMapping(value = "user/{id_u}/patients/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<PatientDTO> setDoctorToPatients(@PathVariable("id_u") Long id_u, @PathVariable("id") Long id) {
		Optional<User> dr=userService.findOne(id_u);
		if (dr.get()==null)
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		Optional<Patient> p=patientService.findOne(id);
		if (p.get()==null)
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		p.get().setDoctor(dr.get());	
		Patient m=patientService.save(p.get());
		if (m==null)
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);	
		return new ResponseEntity<>( HttpStatus.OK);	
		
	}
	
	@RequestMapping(value = "/patients/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<Void> deletePatient(
			@PathVariable Long id) {
		Optional<Patient> m= patientService.findOne(id);
		if (m.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		m.get().setDoctor(null);
		patientService.save(m.get());
		return new ResponseEntity<>(HttpStatus.OK);		
	}
	
	@RequestMapping(value = "examinations/{id}/diagnosis", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> newDiagnois(@PathVariable Long id,@RequestBody DiagnosisDTO diagnosisDTO) {
		Optional<MedicalExamination> me=examinationService.findOne(id);
		if (me.get()==null)
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		//check allergy
		Patient p = me.get().getPatient();
		if (p==null)
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		List<Medicine> medicines = new ArrayList<>();
		for (MedicineDTO medicineDTO : diagnosisDTO.getMedicines()) {
			Optional<Medicine> m= medicineService.findOne(medicineDTO.getId());
			medicines.add(m.get());
		}
		
		List<Medicine> allergetic = allergyService.check(medicines, p);
		if (allergetic.size()!=0)
			return new ResponseEntity<>("PATIENT IS ALLERGIC", HttpStatus.BAD_REQUEST);
		//end
		Diagnosis d= DiagnosisDTO.getDiagnosis(diagnosisDTO);
		d=diagnosisService.save(d);
		me.get().setDiagnosis(d);
		examinationService.save(me.get());
		
		return new ResponseEntity<>( HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "patients/{id}/allergy", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<List<MedicineDTO>> checkAllergy(@PathVariable Long id,@RequestBody List<MedicineDTO> medicinesDTO) {
		Optional<Patient> p = patientService.findOne(id);
		if (p.get()==null)
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		List<Medicine> medicines = new ArrayList<>();
		for (MedicineDTO medicineDTO : medicinesDTO) {
			Optional<Medicine> m= medicineService.findOne(medicineDTO.getId());
			medicines.add(m.get());
		}
		
		List<Medicine> allergetic = allergyService.check(medicines, p.get());
		
		List<MedicineDTO> medicinesDTORet = new ArrayList<>();
		for (Medicine medicine : allergetic) {
			medicinesDTORet.add(new MedicineDTO(medicine));
		}
		return new ResponseEntity<>(medicinesDTORet, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "symptoms/disease", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<List<DiseaseDTO>> findDiseaseBySymptoms(@RequestBody List<SymptomDTO> symptomDTO) {
		System.out.println("symprom size: "+symptomDTO.size());
		List<Symptom> symptoms = new ArrayList<>();
		for (SymptomDTO symptom2 : symptomDTO) {
			symptoms.add(SymptomDTO.getSymptom(symptom2));
		}
		List<Disease> diseases = symptomService.findDisease(symptoms);
		List<DiseaseDTO> diseaseDTO = new ArrayList<>();
		for (Disease disease : diseases) {
			diseaseDTO.add(new DiseaseDTO(disease));
		}
		System.out.println("desise size: "+diseaseDTO.size());
		return new ResponseEntity<>(diseaseDTO, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "disease/{id}/symptoms", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<List<SymptomDTO>> findDiseaseBySymptoms(@PathVariable Long id) {
		Optional<Disease> disease = diseaseService.findOne(id);
		if (disease.get()==null)
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		List<Symptom> symptoms = symptomService.findSymptom(disease.get());
		List<SymptomDTO> symptomDTO = new ArrayList<>();
		for (Symptom s : symptoms) {
			symptomDTO.add(new SymptomDTO(s));
		}
		
		return new ResponseEntity<>(symptomDTO, HttpStatus.OK);		
	}
}
