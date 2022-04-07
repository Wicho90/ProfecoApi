package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Producto;
import com.bolsadeideas.springboot.backend.apirest.models.services.IProductoService;
@CrossOrigin //(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductoRestController {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/productos")
	public List<Producto> index(){
		return productoService.findAll();
	}
	
	@GetMapping("/productos/{id}") //retorna el producto en json
	public Producto show(@PathVariable Long id) {
		return productoService.findById(id);
	}
	
	@PostMapping("/productos")//Transformar el json de llega al objeto producto
	@ResponseStatus(HttpStatus.CREATED)
	public Producto create(@RequestBody Producto  producto) {
		return productoService.save(producto);
	}
	
	@PutMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto update(@RequestBody Producto producto, @PathVariable Long id) {
		//Buscamos el producto mediante al id que nos pasron
		Producto productoActual = productoService.findById(id);
		
		//agregamos los nuevos datos al producto en la base de datos
		productoActual.setNombre(producto.getNombre());
		productoActual.setMercado(producto.getMercado());
		productoActual.setPrecio(producto.getPrecio());
		productoActual.setIdMercado(producto.getIdMercado());
		
		return productoService.save(productoActual);
		
	}
	
	@DeleteMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		productoService.delete(id);
		
	}
	
}
