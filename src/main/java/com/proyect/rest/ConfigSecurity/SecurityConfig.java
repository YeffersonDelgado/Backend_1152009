package com.proyect.rest.ConfigSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtFilter jwtFilter;
	private final AuthenticationProvider authenticationProvider;
	
	private final String[] RUTAS = {
		"/v3/api-docs/**",
		"/swagger-ui/**",
		"/v2/api-docs/**",
		"/swagger-resources/**"	
	};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers(
				publicEndpoints()).permitAll()
				.requestMatchers(RUTAS).permitAll()
				.anyRequest().authenticated())
		.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
	}
	

	
	public RequestMatcher publicEndpoints() {
		
		return new OrRequestMatcher(
				new AntPathRequestMatcher("/api/test/helloPublic"),
				new AntPathRequestMatcher("/api/auth/**"),
				new AntPathRequestMatcher("/api/producto/**"),
				new AntPathRequestMatcher("/**")
				);
		
	}
	
	
	
}
