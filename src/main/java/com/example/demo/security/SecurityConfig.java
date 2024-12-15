package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import model.Role;
import model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService uds;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(requests -> requests
						.requestMatchers("/login.jsp", "/register.jsp", "/user/**").permitAll()
						.requestMatchers("/index.jsp").hasAnyRole("Admin", "Guest", "User")
						.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login.jsp").permitAll()
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/index.jsp")
						.failureUrl("/login.jsp?error=true"))
				.logout(logout -> logout
						.logoutSuccessUrl("/login.jsp"))
				.csrf(csrf -> csrf.disable())
				.build();
	}

//	@Bean
//	AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
//			PasswordEncoder passwordEncoder) {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//		return new ProviderManager(authenticationProvider);
//	}

//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//		String encodedPassword = passwordEncoder.encode("password");
//
//		User user = new User();
//		user.setUsername("user");
//		user.setPassword(encodedPassword);
//		Role r = new Role();
//		r.setName("ADMIN");
//		user.setRole(r);
//
//		return new InMemoryUserDetailsManager(new CustomUserDetails(user));
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
