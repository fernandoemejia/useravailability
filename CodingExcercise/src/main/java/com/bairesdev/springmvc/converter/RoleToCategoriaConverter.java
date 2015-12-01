package com.bairesdev.springmvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.bairesdev.springmvc.model.Categoria;
import com.bairesdev.springmvc.service.CategoriaService;


/**
 * A converter class used in views to map id's to actual Categoria objects.
 */
@Component
public class RoleToCategoriaConverter implements Converter<Object, Categoria>{

	@Autowired
	CategoriaService categoriaService;

	/**
	 * Gets Categoria by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public Categoria convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		Categoria categoria= categoriaService.findById(id);
		System.out.println("Categoria : "+categoria);
		return categoria;
	}
	
}