package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
						.requestMatchers("/login.jsp", "/register.jsp", "/user/**", "/index.jsp", "/styles/**").permitAll()
						//.requestMatchers("/index.jsp").hasAnyRole("Admin", "Guest", "User")
						.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/index.jsp").permitAll()
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/user/home", true)
						.failureUrl("/login.jsp?error=true"))
				.logout(logout -> logout
						.logoutSuccessUrl("/index.jsp"))
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

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
