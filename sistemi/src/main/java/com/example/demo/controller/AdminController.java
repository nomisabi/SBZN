package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DiseaseDTO;
import com.example.demo.dto.IngredientDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.MedicineDTO;
import com.example.demo.dto.SymptomDTO;
import com.example.demo.model.Disease;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Medicine;
import com.example.demo.model.Symptom;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.DiseaseService;
import com.example.demo.service.IngredientService;
import com.example.demo.service.MedicineService;
import com.example.demo.service.SymptomService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class AdminController {

	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	IngredientService ingredientService;

	@Autowired
	MedicineService medicineService;
	
	@Autowired
	SymptomService symptomService;

	

	
	@RequestMapping(value = "/medicines", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> newMedicine(@RequestBody MedicineDTO medicineDTO) {
		System.out.println("med");
		Medicine m= medicineDTO.getMedicine(medicineDTO);
		// findBy name
		m=medicineService.save(m);
		if (m==null)
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);	
		return new ResponseEntity<>( HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "/medicines", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<List<Medicine>> getMedicines(){
			List<Medicine> med= medicineService.findAll();
			return new ResponseEntity<>(med, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/medicines/{id}", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<Medicine> getMedicine(@PathVariable Long id) {
		Optional<Medicine> m= medicineService.findOne(id);
		if (m.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		return new ResponseEntity<>(m.get(),HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/medicines/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<Void> deleteMedicine(
			@PathVariable Long id) {
		Optional<Medicine> m= medicineService.findOne(id);
		if (m.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		medicineService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/ingredients", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> newIngredient(@RequestBody IngredientDTO ingredientDTO) {
		Ingredient i= ingredientDTO.getIngredient(ingredientDTO);
		// findBy name
		i=ingredientService.save(i);
		if (i==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "/ingredients", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<List<Ingredient>> getIngredients() {
		List<Ingredient> ing= ingredientService.findAll();
		return new ResponseEntity<>(ing, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/ingredients/{id}", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<Ingredient> getIngredient( @PathVariable Long id) {
		Optional<Ingredient> i= ingredientService.findOne(id);
		if (i.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		return new ResponseEntity<>(i.get(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ingredients/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<Void> deleteIngredient(
			 @PathVariable Long id) {
		Optional<Ingredient> i= ingredientService.findOne(id);
		if (i.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		ingredientService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
	
	@RequestMapping(value = "/symptoms", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> newSymptom(@RequestBody SymptomDTO symptomDTO) {
		Symptom i= symptomDTO.getSymptom(symptomDTO);
		// findBy name
		i=symptomService.save(i);
		if (i==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "/symptoms", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<List<Symptom>> getSymptoms() {
		List<Symptom> ing= symptomService.findAll();
		return new ResponseEntity<>(ing, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/symptoms/{id}", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<Symptom> getSymptom( @PathVariable Long id) {
		Optional<Symptom> i= symptomService.findOne(id);
		if (i.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		return new ResponseEntity<>(i.get(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/symptoms/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<Void> deleteSymptom(
			 @PathVariable Long id) {
		Optional<Symptom> i= symptomService.findOne(id);
		if (i.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		symptomService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);	
	}

	
	@RequestMapping(value = "/diseases", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> newDisease(@RequestBody DiseaseDTO diseaseDTO) {
		Disease i= diseaseDTO.getDisease(diseaseDTO);
		// findBy name
		i=diseaseService.save(i);
		if (i==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "/diseases", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<List<Disease>> getDiseases() {
		List<Disease> ing= diseaseService.findAll();
		return new ResponseEntity<>(ing, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/diseases/{id}", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<Disease> getDisease( @PathVariable Long id) {
		Optional<Disease> i= diseaseService.findOne(id);
		if (i.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		return new ResponseEntity<>(i.get(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/diseases/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<Void> deleteDisease(
			 @PathVariable Long id) {
		Optional<Disease> i= diseaseService.findOne(id);
		if (i.get()==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		diseaseService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);	
	}

}
