package com.proyect.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class testController {

   @GetMapping("/helloPublic")
   public String holaPublic() {
	   return "REVIVIENDO A PEPE CARDENAS";
   }
   
   @GetMapping("/helloProtected")
   public String holaProtected() {
	   return "Hola Mundo encriptado";
   }
	
}
