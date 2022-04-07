package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	
	//Recibe el id retorna el producto
	public Producto findById(Long id);
	
	//Recibe el producto a guardar y retorna el producto con el id
	public Producto save(Producto cliente);
	
	
	public void delete(Long id);
	
	

}
