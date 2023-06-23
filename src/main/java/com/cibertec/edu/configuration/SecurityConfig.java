package com.cibertec.edu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
        	.authorizeRequests()
            .antMatchers("/login", "/css/**", "/js/**","/jasper/**").permitAll()
            .antMatchers("/guardar").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/registro")
            .permitAll()
            .and()
            .logout()
            .permitAll();
        return http.build();
    }
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user1 = User.builder()
				.username("Agustin")
				.password(passwordEncoder().encode("123456"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user1);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}