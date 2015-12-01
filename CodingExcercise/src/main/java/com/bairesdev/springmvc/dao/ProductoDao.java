package com.bairesdev.springmvc.dao;

import java.util.List;


import com.bairesdev.springmvc.model.Producto;


public interface ProductoDao {

	Producto findById(int id);
	
	Producto findByDescripcion(String descripcion);
	
	void save(Producto producto);
	
	void deleteByDescripcion(String descripcion);
	
	List<Producto> findAllProductos();

}

