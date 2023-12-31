package com.epam.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	/*
	 * 1. Default security configurations
	 */

//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//		http.formLogin(withDefaults());
//		http.httpBasic(withDefaults());
//		return http.build();
//	}

	/*
	 * 2. Custom security configurations - Allowing some endpoints but some not
	 */

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> requests.requestMatchers("/myAccount", "/myBalance", "/myCards", "/myLoans")
						.authenticated().requestMatchers("/contact", "/notices").permitAll());
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}

	/*
	 * 3. Custom security configurations - Denying all the endpoints
	 */

//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
//		http.formLogin(withDefaults());
//		http.httpBasic(withDefaults());
//		return http.build();
//	}

	/*
	 * 3. Custom security configurations - Allowing all the endpoints
	 */

//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
//		http.formLogin(withDefaults());
//		http.httpBasic(withDefaults());
//		return http.build();
//	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {

		// Approach 1 where we use withDefaultPasswordEncoder() while creating the user
		// details
		
//		UserDetails admin = User.withDefaultPasswordEncoder()
//				.username("admin")
//				.password("admin")
//				.authorities("admin")
//				.build();
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("user")
//				.authorities("read")
//				.build();
//		return new InMemoryUserDetailsManager(admin, user);

		// Approach 2 where we use NoOpPasswordEncoder while creating the user details
		
		UserDetails admin = User.withUsername("admin").password("admin").authorities("admin").build();
		UserDetails user = User.withUsername("user").password("user").authorities("read").build();
		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
