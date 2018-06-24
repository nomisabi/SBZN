package com.example.demo.drools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.example.demo.model.MedicalExamination;
import com.example.demo.model.Medicine;
import com.example.demo.model.SympomForExamination;

@Role(Role.Type.EVENT)
@Timestamp("dateOfDiagnosis")
public class MedicalExaminationEvent implements Serializable{

	 private Long id;
	 private Long diseaseId;
	 
	 private Double temperature;
	 private Long doctorId;
	 private List<Medicine> medicines= new ArrayList<>();
	 private List<SympomForExamination> sympom  = new ArrayList<>();
	 private Long patientId;
	 private boolean healed;
	 private boolean operation;
	 private Date dateOfHealing;
	 private Date dateOfDiagnosis;
	 
	 public MedicalExaminationEvent(MedicalExamination me) {
		super();
		this.id = me.getId();
		this.temperature = me.getTemperature();
		if ( me.getDiagnosis()!=null) {
			if ( me.getDiagnosis().getDisease()!=null)
				this.diseaseId = me.getDiagnosis().getDisease().getId();
		
			for (Medicine medicine : me.getDiagnosis().getMedicines()) {
				this.medicines.add(medicine);			
			}
			this.healed= me.getDiagnosis().isHealed();
			this.operation= me.getDiagnosis().isOperation();
			this.dateOfHealing= me.getDiagnosis().getDateOfHealing();
			this.dateOfDiagnosis= me.getDiagnosis().getDateOfDiagnosis();
		}else {
			this.diseaseId =0L;
			this.healed=false;
			this.dateOfHealing = new Date();
			this.dateOfDiagnosis = new Date();
		}
		
		for (SympomForExamination sfm : me.getSympom()) {
			this.sympom.add(sfm);
		}
		if ( me.getDoctor()!=null) 
			this.doctorId=me.getDoctor().getId();
		else
			this.doctorId=0L;
		if ( me.getPatient()!=null)
			this.patientId=me.getPatient().getId();
		else
			this.patientId=0L;
	
	}

	 public MedicalExaminationEvent() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(Long diseaseId) {
		this.diseaseId = diseaseId;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}


	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public List<SympomForExamination> getSympom() {
		return sympom;
	}

	public void setSympom(List<SympomForExamination> sympom) {
		this.sympom = sympom;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public boolean isHealed() {
		return healed;
	}

	public void setHealed(boolean healed) {
		this.healed = healed;
	}

	public Date getDateOfHealing() {
		return dateOfHealing;
	}

	public void setDateOfHealing(Date dateOfHealing) {
		this.dateOfHealing = dateOfHealing;
	}

	public Date getDateOfDiagnosis() {
		return dateOfDiagnosis;
	}

	public void setDateOfDiagnosis(Date dateOfDiagnosis) {
		this.dateOfDiagnosis = dateOfDiagnosis;
	}

	public boolean isOperation() {
		return operation;
	}

	public void setOperation(boolean operation) {
		this.operation = operation;
	}	 
	
	 
}


