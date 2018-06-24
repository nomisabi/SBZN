package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.example.demo.model.Disease;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Medicine;
import com.example.demo.model.Symptom;
import com.example.demo.model.Disease.Category;

public class DiseaseDTO {
	  private Long id;
	  private String name;
	  private Double temperatureMin;
	  private Double temperatureMax;
	  private Category category;
	  private Set<SymptomDTO> simptons  = new HashSet<>();
	  
	  public DiseaseDTO() {
		  
	  }
	  
	  public DiseaseDTO(Disease disease) {
		  id= disease.getId();
		  name=  disease.getName();
		  temperatureMin=  disease.getTemperatureMin();
		  temperatureMax=  disease.getTemperatureMax();
		  category=  disease.getCategory();
		  for (Symptom s:  disease.getSimptons()) {
			  simptons.add(new SymptomDTO(s));
		  }
	  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(Double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

	public Double getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(Double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<SymptomDTO> getSimptons() {
		return simptons;
	}

	public void setSimptons(Set<SymptomDTO> simptons) {
		this.simptons = simptons;
	}
	  

	public static Disease getDisease(DiseaseDTO diseaseDTO) {
		Set<Symptom> symptoms  = new HashSet<>();
		for (SymptomDTO s: diseaseDTO.simptons) {
			symptoms.add(SymptomDTO.getSymptom(s));
		}
		return new Disease(diseaseDTO.getId(),diseaseDTO.getName(), diseaseDTO.getTemperatureMin(), diseaseDTO.getTemperatureMax(), diseaseDTO.getCategory(), symptoms);
		
	}
	  
}
