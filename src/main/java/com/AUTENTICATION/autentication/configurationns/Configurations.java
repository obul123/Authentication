package com.AUTENTICATION.autentication.configurationns;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Configurations {
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder)
	{
		UserDetails admin=User.withUsername("Obul")
							  .password(encoder.encode("Pwd1"))
							  .roles("ADMIN")
							  .build();
		
		UserDetails user=User.withUsername("Reddy")
							 .password(encoder.encode("Pwd2"))
							 .roles("USER")
							 .build();
		return new InMemoryUserDetailsManager(admin,user);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http.csrf().disable()
				   .authorizeHttpRequests()
				   .requestMatchers("/get/one").permitAll()
				   .and()
				   .authorizeHttpRequests().
				   requestMatchers("/get/two").authenticated()
				   .and().formLogin()
				   .and()
				   .authorizeHttpRequests().
				   requestMatchers("/get/three").authenticated()
				   .and().formLogin()
				   .and().build();
	}

}
