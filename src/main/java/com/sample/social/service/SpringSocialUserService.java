package com.sample.social.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.sample.social.dao.User;
import com.sample.social.repo.UserRepository;

public class SpringSocialUserService implements SocialUserDetailsService{
	
	UserRepository userRepo;
	
	public SpringSocialUserService(UserRepository userRepo){
		this.userRepo = userRepo;
	}

	@Override
	public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		return null;
	}
	
	public org.springframework.security.core.userdetails.User buildUser(User user) {

		SocialUser securityUser = new SocialUser(user.getEmail(), user.getPassword(), buildGrantedAuthority(user));

		return securityUser;
	}

	public Collection<GrantedAuthority> buildGrantedAuthority(User user) {
		Collection<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();

		authorityList.add(new SimpleGrantedAuthority(user.getRole().toString()));
		return authorityList;
	}

}
