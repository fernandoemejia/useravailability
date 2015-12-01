package com.bairesdev.springmvc.service;

import java.util.List;

import com.bairesdev.springmvc.model.Categoria;



public interface CategoriaService {

	Categoria findById(int id);

	Categoria findByType(String type);
	
	List<Categoria> findAll();
	
}
