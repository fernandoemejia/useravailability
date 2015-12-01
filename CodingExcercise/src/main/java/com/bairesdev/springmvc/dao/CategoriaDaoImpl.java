package com.bairesdev.springmvc.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bairesdev.springmvc.model.Categoria;



@Repository("categoriaDao")
public class CategoriaDaoImpl extends AbstractDao<Integer, Categoria>implements CategoriaDao{

	public Categoria findById(int id) {
		return getByKey(id);
	}

	public Categoria findByType(String type) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("type", type));
		return (Categoria) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> findAll(){
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("type"));
		return (List<Categoria>)crit.list();
	}
	
}
