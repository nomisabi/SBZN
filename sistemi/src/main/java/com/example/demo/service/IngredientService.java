package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Medicine;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.ItemRepository;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRep;
	@Autowired
	private KieContainer kieContainer;
	
	public Ingredient save(Ingredient ingredient) {
		List<Ingredient> ingredients= ingredientRep.findAll();
		KieSession kieSession = kieContainer.newKieSession();		
		for (Ingredient s : ingredients) {
			kieSession.insert(s);
		}
	    kieSession.insert(ingredient);
	    kieSession.getAgenda().getAgendaGroup("unique").setFocus();
	    int fire = kieSession.fireAllRules();
	    kieSession.dispose();
	    System.out.println("fire "+fire);
	    if (fire!=0)
	    	return null;
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
