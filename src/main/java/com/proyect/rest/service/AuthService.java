package com.proyect.rest.service;

import com.proyect.rest.controller.models.AuthResponse;
import com.proyect.rest.controller.models.AuthenticationRequest;
import com.proyect.rest.controller.models.RegisterRequest;

public interface AuthService {

	AuthResponse register(RegisterRequest request);
	
	AuthResponse authenticate(AuthenticationRequest request);
	
}
