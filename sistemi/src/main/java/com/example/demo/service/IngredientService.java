package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ingredient;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.ItemRepository;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRep;
	
	public Ingredient save(Ingredient ingredient) {
		return ingredientRep.save(ingredient);
	}
	
	public List<Ingredient> findAll() {
		return ingredientRep.findAll();
	}
	
	public Optional<Ingredient> findOne(Long id) {
		return ingredientRep.findById(id);
	}
	
	public void delete(Long id) {
		ingredientRep.deleteById(id);
	}
	
	public void delete(Ingredient ingredient) {
		ingredientRep.delete(ingredient);
	}
}
