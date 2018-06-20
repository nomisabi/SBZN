package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Medicine;
import com.example.demo.repository.MedicineRepository;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepository medicineRep;
	
	public Medicine save(Medicine medicine) {
		return medicineRep.save(medicine);
	}
	
	public List<Medicine> findAll() {
		return medicineRep.findAll();
	}
	
	public Optional<Medicine> findOne(Long id) {
		return medicineRep.findById(id);
	}
	
	public void delete(Long id) {
		medicineRep.deleteById(id);
	}
	
	public void delete(Medicine medicine) {
		medicineRep.delete(medicine);
	}
}
