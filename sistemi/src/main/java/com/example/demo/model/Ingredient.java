package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ingredient")
public class Ingredient implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
    private Long id;
	
    @Column
    private String name;
    
    
	public Ingredient(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Ingredient() {
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

}
