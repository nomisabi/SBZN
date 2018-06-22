package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Diagnosis  implements Serializable{

	 private static final long serialVersionUID = 1L;

	 @Id @GeneratedValue
	 private Long id;
	  
	 @Column
	 private Date dateOfDiagnosis;
	 
	 @Column
	 private Disease disease;
	 
	 @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	 private Set<Medicine> medicines = new HashSet<>();
	 
	 @Column
	 private boolean operation;

	public Diagnosis(Long id, Date dateOfDiagnosis, Disease disease, Set<Medicine> medicines, boolean operation) {
		super();
		this.id = id;
		this.dateOfDiagnosis = dateOfDiagnosis;
		this.disease = disease;
		this.medicines = medicines;
		this.operation=operation;
	}

	public Diagnosis(Long id, Date dateOfDiagnosis, Disease disease) {
		super();
		this.id = id;
		this.dateOfDiagnosis = dateOfDiagnosis;
		this.disease = disease;
	}

	public Diagnosis() {
		super();
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

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Set<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}

	public boolean isOperation() {
		return operation;
	}

	public void setOperation(boolean operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "Diagnosis [id=" + id + ", dateOfDiagnosis=" + dateOfDiagnosis + ", disease=" + disease + ", medicines="
				+ medicines + "]";
	}
	 
}
