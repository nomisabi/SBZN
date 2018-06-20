package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.example.demo.model.Medicine.Category;

@Entity
public class Disease implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public enum Category {
        FIRST, SECOND, THIRD
	};
	
	@Id @GeneratedValue
    private Long id;
	
    @Column
    private String name;
    
    @Column
    private Double temperatureMin;
    
    @Column
    private Double temperatureMax;
    
    @Column
    private Category category;
    
    
    
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Symptom> simptons  = new HashSet<>();
    
	public Disease(Long id, String name) {
		super();
		this.id = id;
		this.name = name;		
	}

	public Disease(Long id, String name, Double temperatureMin, Double temperatureMax, Category category,
			Set<Symptom> simptons) {
		super();
		this.id = id;
		this.name = name;
		this.temperatureMin = temperatureMin;
		this.temperatureMax = temperatureMax;
		this.category = category;
		this.simptons = simptons;
	}



	public Disease() {
		super();
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

	public Set<Symptom> getSimptons() {
		return simptons;
	}

	public void setSimptons(Set<Symptom> simptons) {
		this.simptons = simptons;
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


}
