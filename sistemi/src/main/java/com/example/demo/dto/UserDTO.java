package com.example.demo.dto;

import com.example.demo.model.User;

public class UserDTO {
	private Long id;
	private String username;

	private String email;
	
	private String firstname;	
	private String lastname;
	private String skill;

	public UserDTO() {

	}

	public UserDTO(User user) {
		if (user.getId()!=null)
			this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.firstname = user.getFirstName();
		this.lastname = user.getLastName();
		this.skill = user.getSkill();
	}

	public UserDTO(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}

	public UserDTO(Long id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
	}

	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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


	public static User getUser(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getEmail(), userDTO.getFirstname(), userDTO.getLastname(), userDTO.getSkill());
	
	}

}
