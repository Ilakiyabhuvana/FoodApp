//package com.example.demo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityFoodApp {
//	@Bean
//	public UserDetailsService userDeatilService(PasswordEncoder encoder) {
//		
//		UserDetails details = User.withUsername("Bhuvana")
//				.password(encoder.encode("Bhuvana@2002")).roles("ADMIN").build();
//		UserDetails user1 = User.withUsername("Bhuvana")
//				.password(encoder.encode("Bhuvana@2002")).roles("USER").build();
//		UserDetails user2 = User.withUsername("Bhuvana")
//				.password(encoder.encode("Bhuvana@2002")).roles("USER").build();
//		
//		List<UserDetails> ref = new ArrayList<>();
//		ref.add(details);
//		ref.add(user1);
//		ref.add(user2);
//		
//		return new InMemoryUserDetailsManager(ref);
//	}
//	
//	@Bean
//	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
//		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/getfoode").permitAll().requestMatchers("/postrecord").authenticated().and().formLogin().and().build();
//		
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEnforder() {
//		return new BCryptPasswordEncoder();
//	}
//
//}
