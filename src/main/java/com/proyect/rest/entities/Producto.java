package com.proyect.rest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {

	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;	
	
	private String tipoProducto;
	
	private Double precio;
	
	
}
