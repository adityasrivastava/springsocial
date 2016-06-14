package com.sample.social.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sample.social.dao.User;
import com.sample.social.repo.UserRepository;

public class SpringSecurityUserService implements UserDetailsService {

	
	UserRepository userRepo;
	
	@Autowired
	public SpringSecurityUserService(UserRepository userRepo){
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + username);
		}

		return buildUser(user);
	}

	public org.springframework.security.core.userdetails.User buildUser(User user) {

		org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), buildGrantedAuthority(user));

		return securityUser;
	}

	public Collection<GrantedAuthority> buildGrantedAuthority(User user) {
		Collection<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();

		authorityList.add(new SimpleGrantedAuthority(user.getRole().toString()));
		return authorityList;
	}

}
