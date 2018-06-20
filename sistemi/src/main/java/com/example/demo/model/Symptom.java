package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sympton")
public class Symptom implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
    private Long id;
	
    @Column
    private String name;
    
    @Column
    private Boolean specific;
    
    
	public Symptom(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.specific=false;
	}	
	
	public Symptom(Long id, String name, Boolean specific) {
		super();
		this.id = id;
		this.name = name;
		this.specific = specific;
	}

	public Symptom() {
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

	public Boolean getSpecific() {
		return specific;
	}

	public void setSpecific(Boolean specific) {
		this.specific = specific;
	}

	
}
