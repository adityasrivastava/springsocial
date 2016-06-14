package com.sample.social.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sample.social.repo.UserRepository;
import com.sample.social.service.SpringSecurityUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.
//			inMemoryAuthentication()
//			.withUser("aditya")
//			.password("aditya")
//			.roles("USER");
//	}
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.
			userDetailsService(userDetailsService());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/**")
			.authorizeRequests()
				.anyRequest().hasRole("USER")
				.and()
			.httpBasic();
	}
	
	@Bean
	public SpringSecurityUserService userDetailsService(){
		return new SpringSecurityUserService(userRepository);
	}

}
