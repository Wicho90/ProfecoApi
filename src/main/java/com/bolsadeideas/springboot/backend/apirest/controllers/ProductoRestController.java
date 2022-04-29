package com.bolsadeideas.springboot.backend.apirest.controllers;


import java.io.IOException;
import java.net.MalformedURLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Mercado;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Producto;
import com.bolsadeideas.springboot.backend.apirest.models.services.IProductoService;
import com.bolsadeideas.springboot.backend.apirest.models.services.IUploadFileService;

@CrossOrigin // (origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductoRestController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUploadFileService uploadService;


	@GetMapping("/productos")
	public List<Producto> index() {
		return productoService.findAll();
	}
	
	@GetMapping("/productos/page/{page}")
	public Page<Producto> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 6);
		return productoService.findAll(pageable);
	}

	@GetMapping("/productos/{id}") // retorna el producto en json
	public ResponseEntity<?> show(@PathVariable Long id) {

		Producto producto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			producto = productoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Erro al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (producto == null) {
			response.put("mensaje", "El producto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/productos") // Transformar el json de llega al objeto producto
	public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult result) {
		Producto productoNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			productoNew = productoService.save(producto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido creado con éxito!");
		response.put("producto", productoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result,
			@PathVariable Long id) {

		// Buscamos el cliente mediante al id que nos pasron
		Producto productoActual = productoService.findById(id);
		Producto productoUpdate = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (productoActual == null) {
			response.put("mensaje", "Error: no se puedo editar, el prducto ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			// agregamos los nuevos datos al cliente en la base de datos
			productoActual.setNombre(producto.getNombre());
			productoActual.setPrecio(producto.getPrecio());
			productoActual.setCreateAt(producto.getCreateAt());
			productoActual.setMercado(producto.getMercado());
			
			productoUpdate = productoService.save(productoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al alctualizar el producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido actualizado con éxito!");
		response.put("producto", productoUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Producto producto = productoService.findById(id);
			String nombreFotoAnterior = producto.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
			productoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Producto eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/productos/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Producto producto = productoService.findById(id);
		
		if(!archivo.isEmpty()) {
			
			String nombreArchivo = null;
			try {
				nombreArchivo =	uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del prducto ");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = producto.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
			producto.setFoto(nombreArchivo);
			
			productoService.save(producto);
			
			response.put("producto", producto);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
			
		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"" );
		
		return new ResponseEntity<Resource>(recurso, cabecera,HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/productos/mercados")
	public List<Mercado> listarMercados(){
		return productoService.findAllMercados();
	}

}
