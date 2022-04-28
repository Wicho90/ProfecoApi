package com.bolsadeideas.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Mercado;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long>{
	
	@Query("from Mercado")
	public List<Mercado> findAllMercados();

}
