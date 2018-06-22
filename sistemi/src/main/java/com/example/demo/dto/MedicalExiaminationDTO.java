package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.demo.model.Diagnosis;
import com.example.demo.model.MedicalExamination;
import com.example.demo.model.Patient;
import com.example.demo.model.SympomForExamination;
import com.example.demo.model.User;

public class MedicalExiaminationDTO {
	 private Long id;
	 private DiagnosisDTO diagnosis;
	 private double temperature;
	 private UserDTO doctor;
	 private Set<SymptomForExaminationDTO> sympom  = new HashSet<>();
	 private PatientDTO patient;
	 
	 public MedicalExiaminationDTO() {
		 
	 }
	 
	 public MedicalExiaminationDTO(MedicalExamination me) {
		 id=me.getId();
		 if (me.getDiagnosis()!=null)
			 diagnosis=new DiagnosisDTO(me.getDiagnosis());
		 temperature = me.getTemperature();
		 if (me.getDoctor()!=null)
			 doctor=new UserDTO(me.getDoctor());
		 if (me.getPatient()!=null)
			 patient=new PatientDTO(me.getPatient());
		 for (SympomForExamination se : me.getSympom()) {
			sympom.add(new SymptomForExaminationDTO(se));
		}
	 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DiagnosisDTO getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(DiagnosisDTO diagnosis) {
		this.diagnosis = diagnosis;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public UserDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(UserDTO doctor) {
		this.doctor = doctor;
	}

	public Set<SymptomForExaminationDTO> getSympom() {
		return sympom;
	}

	public void setSympom(Set<SymptomForExaminationDTO> sympom) {
		this.sympom = sympom;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
	 
	public static MedicalExamination getMedicalExamination(MedicalExiaminationDTO meDTO) {
		 Diagnosis diagnosis= null;
		 if (meDTO.getDiagnosis()!=null)
			 diagnosis=DiagnosisDTO.getDiagnosis(meDTO.getDiagnosis());
		 User doctor= null;
		 if (meDTO.getDoctor()!=null)
			 doctor=UserDTO.getUser(meDTO.getDoctor());
		 Patient patient= null;
		 if (meDTO.getPatient()!=null)
			 patient=PatientDTO.getPatient(meDTO.getPatient());
		 Set<SympomForExamination> sympom  = new HashSet<>();
		 for (SymptomForExaminationDTO seDTO: meDTO.getSympom())
			 sympom.add(SymptomForExaminationDTO.getSymptom(seDTO));
		 return new MedicalExamination(meDTO.getId(),diagnosis, meDTO.getTemperature(), sympom, doctor, patient );
		 
	}
	 
	 
}
