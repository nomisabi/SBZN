package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserPasswordDTO;
import com.example.demo.model.User;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.UserService;


@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	/*** login ***/
	public ResponseEntity<String> login(
			@RequestBody LoginDTO loginDTO) {
		System.out.println("Bar eddig jo");
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
					loginDTO.getPassword());
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
			return new ResponseEntity<>(tokenUtils.generateToken(details), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Invalid login", HttpStatus.BAD_REQUEST);
		}
	}
/*
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> register(
			@RequestBody RegisterDTO registerDTO) {
		if (!registerDTO.getPassword().equals(registerDTO.getPassword2())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User user = RegisterDTO.getTenant(registerDTO);
		user = userService.save(user, "ROLE_USER");
		
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
*/
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	/*** get list of users ***/
	public ResponseEntity<List<UserDTO>> getUsers(Pageable page) {
		Page<User> users = userService.findAll(page);

		List<UserDTO> usersDTO = new ArrayList<>();
		for (User user : users) {
			usersDTO.add(new UserDTO(user));
		}

		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	
	/*** get user by id ***/
	public ResponseEntity<UserDTO> getUser(
			 @PathVariable Long id) {
		Optional<User> user = userService.findOne(id);
		if (user.get() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new UserDTO(user.get()), HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT, consumes = "application/json")
	/*** update user ***/
	public ResponseEntity<UserDTO> updateUser(
			 @RequestBody UserDTO userDTO) {
		Optional<User> user = userService.findOne(userDTO.getId());

		if (user.get() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User user1 = userService.update(user.get());

		return new ResponseEntity<>(new UserDTO(user1), HttpStatus.OK);
	}

	@RequestMapping(value = "/users/password", method = RequestMethod.PUT, consumes = "application/json")
	/*** change user password ***/
	public ResponseEntity<Void> changePassword(
			@RequestBody UserPasswordDTO userPasswordDTO,
			HttpServletRequest request) {

		String token = request.getHeader("X-Auth-Token");
		String username = tokenUtils.getUsernameFromToken(token);

		boolean flag = userService.changePassword(username, userPasswordDTO.getCurrentPassword(),
				userPasswordDTO.getNewPassword1(), userPasswordDTO.getNewPassword2());

		if (flag) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/users/count", method = RequestMethod.GET)
	/*** get a count of users ***/
	public ResponseEntity<Long> getCountOfUsers() {
		Long count = userService.getCountOfUsers();
		return new ResponseEntity<>(count, HttpStatus.OK);

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, params = { "username" })
	/**** find a user by username ***/
	public ResponseEntity<UserDTO> findUserByUsername(
			 @RequestParam("username") String username) {
		User user = userService.findByUsername(username);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<String> authorities = userService.getUsersAuthority(user);

		if (authorities.contains("ROLE_ADMIN") || authorities.contains("ROLE_COMPANY")) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);

	}

	@RequestMapping(value = "/users/me", method = RequestMethod.GET)
	/*** get a current user ***/
	public ResponseEntity<UserDTO> getCurrentUser(HttpServletRequest request) {
		String token = request.getHeader("X-Auth-Token");
		String username = tokenUtils.getUsernameFromToken(token);

		User user = userService.findByUsername(username);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}

}
