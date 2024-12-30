package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.StrictHttpFirewall;

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
						.requestMatchers("/*", "/styles/**", "/register.jsp", "/auth/**", "/search/**", "/listing/**").permitAll()
						.requestMatchers("/user/**", "/personalListings.jsp", "/favoriteListings.jsp", "/parts/**").hasAnyRole("Admin", "User")
						.requestMatchers("/admin/**", "/allMessages.jsp").hasRole("Admin")
						.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login.jsp").permitAll()
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/", true)
						.failureUrl("/login.jsp?error=true"))
				.logout(logout -> logout
						.logoutSuccessUrl("/"))
				.csrf(csrf -> csrf.disable())
				.build();
	}

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
