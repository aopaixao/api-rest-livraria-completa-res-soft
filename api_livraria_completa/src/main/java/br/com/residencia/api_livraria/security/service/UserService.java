package br.com.residencia.api_livraria.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.api_livraria.entity.User;
import br.com.residencia.api_livraria.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}
}
