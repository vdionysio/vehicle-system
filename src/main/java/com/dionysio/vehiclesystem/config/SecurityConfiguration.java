package com.dionysio.vehiclesystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.dionysio.vehiclesystem.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz.antMatchers("/registration**", "/js/**", "/css/**", "/img/**",
				"/Registration/Create", "/h2-console/**").permitAll()
				.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/Dashboard/Index", true).permitAll())
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/home")).csrf().disable();

		http.headers().frameOptions().disable();
		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userServiceImpl);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

}
