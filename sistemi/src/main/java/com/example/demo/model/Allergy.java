package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Allergy implements Serializable{

	 private static final long serialVersionUID = 1L;

	 @Id @GeneratedValue
	 private Long id;
	 
	 @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	 private Ingredient ingredient;
	 
	 @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	 private Medicine medicine;

	public Allergy(Long id, Ingredient ingredient) {
		super();
		this.id = id;
		this.ingredient = ingredient;
	}

	public Allergy(Long id, Medicine medicine) {
		super();
		this.id = id;
		this.medicine = medicine;
	}

	public Allergy() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	@Override
	public String toString() {
		return "Allergy [id=" + id + ", ingredient=" + ingredient + ", medicine=" + medicine + "]";
	}
	 
	 
	
	 
}
