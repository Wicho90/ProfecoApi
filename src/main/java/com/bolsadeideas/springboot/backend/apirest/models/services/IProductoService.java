package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	
	public Page<Producto> findAll(Pageable pageable);

	//Recibe el id retorna el producto
	public Producto findById(Long id);
	
	//Recibe el producto a guardar y retorna el producto con el id
	public Producto save(Producto cliente);
	
	
	public void delete(Long id);
	
	

}
