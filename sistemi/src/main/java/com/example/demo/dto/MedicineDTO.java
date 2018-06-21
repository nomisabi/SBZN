package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Medicine;
import com.example.demo.model.Medicine.Category;

public class MedicineDTO {
	
	private Long id;
		
	private String name;
	    
	private Category category;
	    
	private Set<IngredientDTO> ingredients  = new HashSet<>();
	
	public MedicineDTO() {
		
	}
	
	public MedicineDTO(Medicine medicine) {
		if (medicine.getIngredients()!=null)
			for (Ingredient i: medicine.getIngredients())
				this.ingredients.add(new IngredientDTO(i));
		this.category=medicine.getCategory();
		this.name=medicine.getName();
		if (medicine.getId()!=null) 
			this.id=medicine.getId();
		
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<IngredientDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}

	public static Medicine getMedicine(MedicineDTO medicineDTO) {
		Set<Ingredient> ingredients  = new HashSet<>();
		for (IngredientDTO i: medicineDTO.ingredients) {
			ingredients.add(IngredientDTO.getIngredient(i));
		}
		return new Medicine(medicineDTO.getId(),medicineDTO.getName(), medicineDTO.getCategory(), ingredients);
		
	}

}
