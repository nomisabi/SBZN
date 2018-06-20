package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Authority;
import com.example.demo.model.User;
import com.example.demo.model.UserAuthority;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserAuthorityRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserAuthorityRepository userAuthorityRepository;
	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public User save(User user, String role) {
		
		user = userRepository.save(user);
		Authority authority = authorityRepository.findByName(role);

		if (authority != null) {
			UserAuthority userAuthority = new UserAuthority(user, authority);
			userAuthority = userAuthorityRepository.save(userAuthority);

		}
		return user;
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public Page<User> find(Pageable page, String role) {
		return userRepository.find(role, page);
	}

	public Page<User> findAll(Pageable page) {
		return userRepository.findAll(page);
	}

	public Optional<User> findOne(Long id) {
		return userRepository.findById(id);
	}

	public void remove(Long id, String role) {
		Authority authority = authorityRepository.findByName(role);
		Long userAuthorityID = userAuthorityRepository.findByUserAndAuthority(id, authority.getId());
		userAuthorityRepository.deleteById(userAuthorityID);
		userRepository.deleteById(id);

	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findByUsernameAndAuthority(String username, String role) {
		return userRepository.findByUsernameAndAuthority(username, role);
	}

	public boolean changePassword(String username, String currentPassword, String newPassword, String newPassword2) {
		User user = userRepository.findByUsername(username);

		if (user != null && BCrypt.checkpw(currentPassword, user.getPassword()) && newPassword.equals(newPassword2)) {

			user.setPassword(passwordEncoder.encode(newPassword));
			userRepository.save(user);
			return true;

		}

		return false;
	}

	public UserAuthority saveUserAuthority(User user, String role) {
		Authority authority = authorityRepository.findByName(role);

		UserAuthority userAuthority = new UserAuthority(user, authority);
		return userAuthorityRepository.save(userAuthority);
	}

	public int count(String role) {
		List<User> users = userRepository.findAll(role);
		return users.size();
	}

	public Long getCountOfUsers() {
		return userRepository.count();
	}

	public List<String> getUsersAuthority(User user) {
		return userRepository.getUserAuthority(user.getId());
	}
}
