package com.practice.Springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigure {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
			.csrf(csrf->csrf.disable())
			.authorizeHttpRequests(auth->auth.requestMatchers("/login","/views/**","/logout-success").permitAll()
										.anyRequest().authenticated())
			.formLogin(form->form.loginPage("/login").permitAll())
			.logout(logout->logout.logoutUrl("/logout").invalidateHttpSession(true)
					.clearAuthentication(true).logoutSuccessUrl("/logout-success").permitAll());
		return http.build();
	}
}
