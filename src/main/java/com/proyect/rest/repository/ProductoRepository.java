package com.proyect.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.rest.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
