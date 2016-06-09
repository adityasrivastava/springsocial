package com.sample.social.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@Autowired
	Environment env;
	
//	@Autowired
//	ConnectionFactoryLocator connectionLocator;
	
	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource() {
		EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
		factory.setDatabaseName("spring-social-quickstart");
		factory.setDatabaseType(EmbeddedDatabaseType.H2);
		factory.setDatabasePopulator(databasePopulator());
		return factory.getDatabase();
	}
	
//	@RequestMapping("/")
//	public void mainPage(HttpServletResponse response){
//
////		FacebookConnectionFactory connectionFactory =
////			    new FacebookConnectionFactory(env.getProperty("facebook.appId"), env.getProperty("facebook.secretKey"));
////			OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
////			OAuth2Parameters params = new OAuth2Parameters();
////			params.setRedirectUri("http://localhost:8080/redirect-callback");
////			String authorizeUrl = oauthOperations.buildAuthorizeUrl(params);
////			
////			System.out.println(authorizeUrl);
////			try {
////				response.sendRedirect(authorizeUrl);
////			} catch (IOException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//
//			// upon receiving the callback from the provider:
//			 
//			
//	}
//	
	@RequestMapping("/redirect-callback")
	public void redirectPage(@RequestParam(name="code") String authorizationCode){
//		System.out.println(authorizationCode);
//		FacebookConnectionFactory connectionFactory =
//			    new FacebookConnectionFactory(env.getProperty("facebook.appId"), env.getProperty("facebook.secretKey"));
//		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
//		AccessGrant accessGrant = oauthOperations.exchangeForAccess(authorizationCode, "http://localhost:8080/redirect-callback", null);
//		Connection<Facebook> connection = connectionFactory.createConnection(accessGrant);
//		System.out.println(connection.getDisplayName());
		
	}
	
	@RequestMapping("/redirect-callback2")
	public void test(){
		System.out.println("Test success");
	}
	
	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("JdbcUsersConnectionRepository.sql", JdbcUsersConnectionRepository.class));
		return populator;
	}
	

}
