package com.bairesdev.springmvc.dao;

import java.util.List;

import com.bairesdev.springmvc.model.Categoria;



public interface CategoriaDao {

	List<Categoria> findAll();
	
	Categoria findByType(String type);
	
	Categoria findById(int id);
}
