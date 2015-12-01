package com.bairesdev.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bairesdev.springmvc.dao.CategoriaDao;
import com.bairesdev.springmvc.model.Categoria;



@Service("categoriaService")
@Transactional
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	CategoriaDao dao;
	
	public Categoria findById(int id) {
		return dao.findById(id);
	}

	public Categoria findByType(String type){
		return dao.findByType(type);
	}

	public List<Categoria> findAll() {
		return dao.findAll();
	}
}
