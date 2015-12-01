package com.bairesdev.springmvc.service;


import java.util.List;

import com.bairesdev.springmvc.model.Producto;


public interface ProductoService {
	
	Producto findById(int id);
	
	Producto findByDescripcion(String descripcion);
	
	void saveProducto(Producto producto);
	
	void updateProducto(Producto producto);
	
	void deleteProductoByDescripcion(String descripcion);

	List<Producto> findAllProductos(); 
	
	boolean isProductoDescripcionUnique(Integer id, String descripcion);

}