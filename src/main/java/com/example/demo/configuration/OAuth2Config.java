package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OAuth2Config {

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(this.clientRegistration());
	}
	
	private ClientRegistration clientRegistration() {
		
		  return ClientRegistration.withRegistrationId("github")
		  .clientId("4bd90afc1b1bd67ccfcf")
		  .clientSecret("9383b5f812771fb23fe7c886e9fb3d708f16c6f0")
		  .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
		  .scope("read") .authorizationUri("https://github.com/login/oauth/authorize")
		  .tokenUri("https://github.com/login/oauth/access_token")
		  .userInfoUri("https://api.github.com/user") .userNameAttributeName("id")
		  .clientName("OAuth2 Registration")
		  .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
		  .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}") .build();
		 
		/*
		 * return CommonOAuth2Provider.GITHUB.getBuilder("github")
		 * .clientId("4bd90afc1b1bd67ccfcf")
		 * .clientSecret("9383b5f812771fb23fe7c886e9fb3d708f16c6f0") .build();
		 */
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.oauth2Login(c -> c.defaultSuccessUrl("/home"));
		httpSecurity.authorizeHttpRequests(r -> r.anyRequest().authenticated());
		return httpSecurity.build();
	}
}
