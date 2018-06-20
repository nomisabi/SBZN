package com.example.demo.model;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String skill;
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Patient> patients = new HashSet<>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Set<UserAuthority> userAuthorities = new HashSet<>();

	public User() {

	}

	public User(Long id, String username, String email, String firstName, String lastName, String skill, Set<Patient> patients ) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName= lastName;
		this.skill=skill;
		this.patients=patients;
	}

	public User(Long id, String username, String email, String firstName, String lastName, String skill ) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName= lastName;
		this.skill=skill;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	public Set<UserAuthority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(Set<UserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}
	
	public boolean hasAuthority(String authority){
		for (UserAuthority userAuthority : userAuthorities) {
			if (userAuthority.getAuthority().getName().equals(authority)){
				return true;
			}
		}
		return false;
	}

}
