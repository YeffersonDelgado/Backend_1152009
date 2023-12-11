package com.proyect.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.rest.entities.Producto;
import com.proyect.rest.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/producto")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ProductoController {
  
	@Autowired
	ProductoRepository productoRepository;

	@GetMapping("/listar")
	public List<Producto> listar() {
		return productoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Producto> findByIdProducto(@PathVariable long id) {
		Optional<Producto> cp = productoRepository.findById(id);
		if (cp.isPresent()) {
			return cp;
		}
		return null;
	}

	@PostMapping("/save")
	public Producto save(@RequestBody Producto producto) {
       return productoRepository.save(producto) ;
	}
	

	@PutMapping("/{id}")
	public Producto put(@RequestBody Producto producto, @PathVariable long id) {

		Optional<Producto> pCurrent = productoRepository.findById(id);
		if (pCurrent.isPresent()) {
			Producto pReturn = pCurrent.get();
			pReturn.setNombre(producto.getNombre());
			pReturn.setTipoProducto(producto.getTipoProducto());
			pReturn.setPrecio(producto.getPrecio());
			
			productoRepository.save(pReturn);
			return pReturn;
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public Producto delete(@PathVariable long id) {
		Optional<Producto> p = productoRepository.findById(id);
		if (p.isPresent()) {
			productoRepository.deleteById(id);
			return p.get();
		}
		return null;
	}

	
}
