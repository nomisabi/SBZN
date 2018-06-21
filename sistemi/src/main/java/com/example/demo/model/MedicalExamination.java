package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class MedicalExamination implements Serializable{

	
	 private static final long serialVersionUID = 1L;

	 @Id @GeneratedValue
	 private Long id;
	 
	 @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	 private Diagnosis diagnosis;
	 
	 private Double temperature;
	 
	 @ManyToMany( fetch = FetchType.EAGER)
	 private Set<SympomForExamination> sympom  = new HashSet<>();

	 public MedicalExamination(Long id, Diagnosis diagnosis, Double temperature, Set<SympomForExamination> sympom) {
		super();
		this.id = id;
		this.diagnosis = diagnosis;
		this.temperature = temperature;
		this.sympom = sympom;
	}

	 public MedicalExamination() {
		super();
	}

		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Set<SympomForExamination> getSympom() {
		return sympom;
	}

	public void setSympom(Set<SympomForExamination> sympom) {
		this.sympom = sympom;
	}
 
	 
	 /*private Boolean runnyNose;
	 
	 private Boolean soreThroat;
	 
	 private Boolean headache;
	 
	 private Boolean sneeze;
	 
	 private Boolean cough;

	 private Boolean chills;
	 
	 private Double temperature;
	 
	 private Boolean earache;
	 
	 private Boolean lossOfAppetite;
	 
	 private Boolean fatigue;
	 
	 private Boolean yellowSecretionFromTheNose;
	 
	 private Boolean swellingAroundTheEye;
	 
	 private Boolean oftenUrination;
	 
	 private Boolean lossOfBodyWeight;
	 
	 private Boolean exhaustion;
	 
	 private Boolean nauseaAndVomiting;
	 
	 private Boolean nocturia;
	 
	 private Boolean swollenFeetAndAnkle;
	 
	 private Boolean choking;
	 
	 private Boolean chestPain;
	 
	 private Boolean diarrhea;

	public MedicalExamination() {
		super();
	}

	
	public MedicalExamination(Long id, Diagnosis diagnosis, Boolean runnyNose, Boolean soreThroat, Boolean headache,
			Boolean sneeze, Boolean cough, Boolean chills, Double temperature, Boolean earache, Boolean lossOfAppetite,
			Boolean fatigue, Boolean yellowSecretionFromTheNose, Boolean swellingAroundTheEye, Boolean oftenUrination,
			Boolean lossOfBodyWeight, Boolean exhaustion, Boolean nauseaAndVomiting, Boolean nocturia,
			Boolean swollenFeetAndAnkle, Boolean choking, Boolean chestPain, Boolean diarrhea) {
		super();
		this.id = id;
		this.diagnosis = diagnosis;
		this.runnyNose = runnyNose;
		this.soreThroat = soreThroat;
		this.headache = headache;
		this.sneeze = sneeze;
		this.cough = cough;
		this.chills = chills;
		this.temperature = temperature;
		this.earache = earache;
		this.lossOfAppetite = lossOfAppetite;
		this.fatigue = fatigue;
		this.yellowSecretionFromTheNose = yellowSecretionFromTheNose;
		this.swellingAroundTheEye = swellingAroundTheEye;
		this.oftenUrination = oftenUrination;
		this.lossOfBodyWeight = lossOfBodyWeight;
		this.exhaustion = exhaustion;
		this.nauseaAndVomiting = nauseaAndVomiting;
		this.nocturia = nocturia;
		this.swollenFeetAndAnkle = swollenFeetAndAnkle;
		this.choking = choking;
		this.chestPain = chestPain;
		this.diarrhea = diarrhea;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Boolean getRunnyNose() {
		return runnyNose;
	}

	public void setRunnyNose(Boolean runnyNose) {
		this.runnyNose = runnyNose;
	}

	public Boolean getSoreThroat() {
		return soreThroat;
	}

	public void setSoreThroat(Boolean soreThroat) {
		this.soreThroat = soreThroat;
	}

	public Boolean getHeadache() {
		return headache;
	}

	public void setHeadache(Boolean headache) {
		this.headache = headache;
	}

	public Boolean getSneeze() {
		return sneeze;
	}

	public void setSneeze(Boolean sneeze) {
		this.sneeze = sneeze;
	}

	public Boolean getCough() {
		return cough;
	}

	public void setCough(Boolean cough) {
		this.cough = cough;
	}

	public Boolean getChills() {
		return chills;
	}

	public void setChills(Boolean chills) {
		this.chills = chills;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Boolean getEarache() {
		return earache;
	}

	public void setEarache(Boolean earache) {
		this.earache = earache;
	}

	public Boolean getLossOfAppetite() {
		return lossOfAppetite;
	}

	public void setLossOfAppetite(Boolean lossOfAppetite) {
		this.lossOfAppetite = lossOfAppetite;
	}

	public Boolean getFatigue() {
		return fatigue;
	}

	public void setFatigue(Boolean fatigue) {
		this.fatigue = fatigue;
	}

	public Boolean getYellowSecretionFromTheNose() {
		return yellowSecretionFromTheNose;
	}

	public void setYellowSecretionFromTheNose(Boolean yellowSecretionFromTheNose) {
		this.yellowSecretionFromTheNose = yellowSecretionFromTheNose;
	}

	public Boolean getSwellingAroundTheEye() {
		return swellingAroundTheEye;
	}

	public void setSwellingAroundTheEye(Boolean swellingAroundTheEye) {
		this.swellingAroundTheEye = swellingAroundTheEye;
	}

	public Boolean getOftenUrination() {
		return oftenUrination;
	}

	public void setOftenUrination(Boolean oftenUrination) {
		this.oftenUrination = oftenUrination;
	}

	public Boolean getLossOfBodyWeight() {
		return lossOfBodyWeight;
	}

	public void setLossOfBodyWeight(Boolean lossOfBodyWeight) {
		this.lossOfBodyWeight = lossOfBodyWeight;
	}

	public Boolean getNauseaAndVomiting() {
		return nauseaAndVomiting;
	}

	public void setNauseaAndVomiting(Boolean nauseaAndVomiting) {
		this.nauseaAndVomiting = nauseaAndVomiting;
	}

	public Boolean getNocturia() {
		return nocturia;
	}

	public void setNocturia(Boolean nocturia) {
		this.nocturia = nocturia;
	}

	public Boolean getChoking() {
		return choking;
	}

	public void setChoking(Boolean choking) {
		this.choking = choking;
	}

	public Boolean getChestPain() {
		return chestPain;
	}

	public void setChestPain(Boolean chestPain) {
		this.chestPain = chestPain;
	}

	public Boolean getDiarrhea() {
		return diarrhea;
	}

	public void setDiarrhea(Boolean diarrhea) {
		this.diarrhea = diarrhea;
	}


	public Boolean getExhaustion() {
		return exhaustion;
	}


	public void setExhaustion(Boolean exhaustion) {
		this.exhaustion = exhaustion;
	}


	public Boolean getSwollenFeetAndAnkle() {
		return swollenFeetAndAnkle;
	}


	public void setSwollenFeetAndAnkle(Boolean swollenFeetAndAnkle) {
		this.swollenFeetAndAnkle = swollenFeetAndAnkle;
	}*/
	 
	 
}


