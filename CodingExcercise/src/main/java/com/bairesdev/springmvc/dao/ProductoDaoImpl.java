package com.bairesdev.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bairesdev.springmvc.model.Producto;




@Repository("productoDao")
public class ProductoDaoImpl extends AbstractDao<Integer, Producto> implements ProductoDao {

	public Producto findById(int id) {
		Producto producto = getByKey(id);
		if(producto!=null){
			Hibernate.initialize(producto.getCategorias());
		}
		return producto;
	}

	public Producto findByDescripcion(String descripcion) {
		System.out.println("Descripcion : "+descripcion);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("descripcion", descripcion));
		Producto producto = (Producto)crit.uniqueResult();
		if(producto!=null){
			Hibernate.initialize(producto.getCategorias());
		}
		return producto;
	}

	@SuppressWarnings("unchecked")
	public List<Producto> findAllProductos() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("descripcion"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Producto> productos = (List<Producto>) criteria.list();
		
		
		return productos;
	}

	public void save(Producto producto) {
		persist(producto);
	}

	public void deleteByDescripcion(String descripcion) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("descripcion", descripcion));
		Producto producto = (Producto)crit.uniqueResult();
		delete(producto);
	}

}
