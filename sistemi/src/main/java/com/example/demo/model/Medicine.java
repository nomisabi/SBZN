package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Medicine implements Serializable{
	
	public enum Category {
	        ANTIBIOTICS, ANALGESICS, OTHER
    };
    
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
    private Long id;
	
    @Column
    private String name;
    
    @Column
    private Category category;
    
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Ingredient> ingredients  = new HashSet<>();
    
	public Medicine(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.category= Category.OTHER;
	}
	
	public Medicine(Long id, String name, Category category, Set<Ingredient> ingredients) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.ingredients = ingredients;
	}

	public Medicine() {
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
