package com.example.demo.dto;

import javax.persistence.Column;

import com.example.demo.model.Ingredient;
import com.example.demo.model.User;

public class IngredientDTO {
	 private Long id;
	 private String name;
	 
	 public IngredientDTO() {
	 }

	 public IngredientDTO(Ingredient ing) {
		if (ing.getId()!=null)
			this.id = ing.getId();
		this.name = ing.getName();
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

	public static Ingredient getIngredient(IngredientDTO ingredientDTO) {
		return new Ingredient(ingredientDTO.getId(),ingredientDTO.getName());
		
	}

}
