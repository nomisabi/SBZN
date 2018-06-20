package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Allergy;
import com.example.demo.repository.AllergyRepository;

@Service
public class AllergyService {

	@Autowired
	private AllergyRepository allergyRep;
	
	public Allergy save(Allergy allergy) {
		return allergyRep.save(allergy);
	}
	
	public List<Allergy> findAll() {
		return allergyRep.findAll();
	}
	
	public Optional<Allergy> findOne(Long id) {
		return allergyRep.findById(id);
	}
	
	public void delete(Long id) {
		allergyRep.deleteById(id);
	}
	
	public void delete(Allergy allergy) {
		allergyRep.delete(allergy);
	}
}
