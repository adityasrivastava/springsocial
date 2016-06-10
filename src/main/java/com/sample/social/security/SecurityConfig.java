package com.sample.social.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.
			inMemoryAuthentication()
			.withUser("aditya")
			.password("aditya")
			.roles("USER");
	}
	
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.antMatcher("/**")
				.authorizeRequests()
					.anyRequest().hasRole("USER")
					.and()
				.httpBasic()
				.and()
				.apply(new SpringSocialConfigurer());
		}
	}


}
