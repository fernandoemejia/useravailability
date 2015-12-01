package com.bairesdev.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bairesdev.springmvc.dao.ProductoDao;
import com.bairesdev.springmvc.model.Producto;



@Service("productoService")
@Transactional
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoDao dao;

	public Producto findById(int id) {
		return dao.findById(id);
	}

	public Producto findByDescripcion(String descripcion) {
		Producto producto = dao.findByDescripcion(descripcion);
		return producto;
	}

	
	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateProducto(Producto producto) {
		Producto entity = dao.findById(producto.getId());
		if(entity!=null){
			entity.setDescripcion(producto.getDescripcion());
			entity.setCategorias(producto.getCategorias());
		}
	}

	
	public void deleteProductoByDescripcion(String descripcion) {
		dao.deleteByDescripcion(descripcion);
	}

	public List<Producto> findAllProductos() {
		return dao.findAllProductos();
	}

	public boolean isProductoDescripcionUnique(Integer id, String descripcion) {
		Producto producto = findByDescripcion(descripcion);
		return ( producto == null || ((id != null) && (producto.getId() == id)));
	}

	public void saveProducto(Producto producto) {
		dao.save(producto);		
	}
	
}
