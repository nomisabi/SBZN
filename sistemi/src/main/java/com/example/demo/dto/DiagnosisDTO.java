package com.example.demo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.example.demo.model.Diagnosis;
import com.example.demo.model.Disease;
import com.example.demo.model.Medicine;

public class DiagnosisDTO {
	private Long id;
	 private Date dateOfDiagnosis;
	 private DiseaseDTO disease;
	 private Set<MedicineDTO> medicines = new HashSet<>();
	 private boolean operation;
	 
	 public DiagnosisDTO() {
		 
	 }
	 
	public DiagnosisDTO(Diagnosis diagnosis) {
		this.id = diagnosis.getId();
		this.dateOfDiagnosis = diagnosis.getDateOfDiagnosis();
		if (diagnosis.getDisease()!=null)
			this.disease = new DiseaseDTO( diagnosis.getDisease());
		for (Medicine m:diagnosis.getMedicines()) {
			medicines.add(new MedicineDTO(m));
		}
		this.operation=diagnosis.isOperation(); 
		 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateOfDiagnosis() {
		return dateOfDiagnosis;
	}

	public void setDateOfDiagnosis(Date dateOfDiagnosis) {
		this.dateOfDiagnosis = dateOfDiagnosis;
	}

	public DiseaseDTO getDisease() {
		return disease;
	}

	public void setDisease(DiseaseDTO disease) {
		this.disease = disease;
	}

	public Set<MedicineDTO> getMedicines() {
		return medicines;
	}

	public void setMedicines(Set<MedicineDTO> medicines) {
		this.medicines = medicines;
	}

	public boolean isOperation() {
		return operation;
	}

	public void setOperation(boolean operation) {
		this.operation = operation;
	}
	
	public static Diagnosis getDiagnosis(DiagnosisDTO diagnosisDTO) {
		Disease dis = null;
		if (diagnosisDTO.getDisease()!=null)
			dis= DiseaseDTO.getDisease(diagnosisDTO.getDisease());
		Set<Medicine> medicines =new HashSet<Medicine>();
		for (MedicineDTO medicineDTO : diagnosisDTO.getMedicines()) {
			medicines.add(MedicineDTO.getMedicine(medicineDTO));
		}
		return new Diagnosis(diagnosisDTO.getId(), diagnosisDTO.getDateOfDiagnosis(), dis, medicines, diagnosisDTO.isOperation());
	}
	 
}
