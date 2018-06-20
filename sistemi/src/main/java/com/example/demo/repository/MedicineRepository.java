package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Allergy;
import com.example.demo.model.Medicine;
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
