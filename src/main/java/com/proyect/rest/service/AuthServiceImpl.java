package com.proyect.rest.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyect.rest.ConfigSecurity.JwtService;
import com.proyect.rest.controller.models.AuthResponse;
import com.proyect.rest.controller.models.AuthenticationRequest;
import com.proyect.rest.controller.models.RegisterRequest;
import com.proyect.rest.entities.RoleEntity;
import com.proyect.rest.entities.Usuario;
import com.proyect.rest.repository.UsuarioRepository;

import ch.qos.logback.core.net.LoginAuthenticator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;	
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	@Override
	public AuthResponse register(RegisterRequest request) {

		var usuario = Usuario.builder()
				.nombre(request.getNombre())
				.apellido(request.getApellido())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(RoleEntity.USER)
				.build();	
		usuarioRepository.save(usuario);
		var jwtToken = jwtService.generateToken(usuario);
		
		return AuthResponse.builder().token(jwtToken).build();
	}

	@Override
	public AuthResponse authenticate(AuthenticationRequest request) {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		var usuario = usuarioRepository.findUsuarioByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(usuario);

		return AuthResponse.builder().token(jwtToken).build();
	}

	
	
	

}
