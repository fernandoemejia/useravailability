package com.bairesdev.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bairesdev.springmvc.model.Producto;
import com.bairesdev.springmvc.model.Categoria;
import com.bairesdev.springmvc.service.CategoriaService;
import com.bairesdev.springmvc.service.ProductoService;





//@RestController
@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	ProductoService productoService;
	
	@Autowired
	CategoriaService categoriaService;
	
	
	@Autowired
	MessageSource messageSource;

	/**
	 * This method will list all existing productos.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listProductos(ModelMap model) {

		List<Producto> productos = productoService.findAllProductos();
		model.addAttribute("productos", productos);
		return "productoslist";
	}

	/**
	 * This method will provide the medium to add a new Producto.
	 */
	@RequestMapping(value = { "/newProducto" }, method = RequestMethod.GET)
	public String newProducto(ModelMap model) {
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		model.addAttribute("edit", false);
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving  in database. It also validates the  input
	 */
	@RequestMapping(value = { "/newProducto" }, method = RequestMethod.POST)
	public String saveProducto(@Valid Producto producto, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [Producto].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!productoService.isProductoDescripcionUnique(producto.getId(), producto.getDescripcion())){
			FieldError descripcionError =new FieldError("producto","descripcion",messageSource.getMessage("non.unique.descripcion", new String[]{producto.getDescripcion()}, Locale.getDefault()));
		    result.addError(descripcionError);
			return "registration";
		}
		
		productoService.saveProducto(producto);

		model.addAttribute("success", "Producto " + producto.getDescripcion() + " registered successfully");
		//return "success";
		return "registrationsuccess";
	}

	/*
	
	 @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	    public String editProducto(@PathVariable("id") int id, ModelMap model) {
	        System.out.println("Updating Producto " + id);
	         
	        Producto producto = productoService.findById(id);
			model.addAttribute("producto", producto);
			model.addAttribute("edit", true);
			return "registration";
	    }
	    
*/	    
	/**
	 * This method will provide the medium to update an existing Producto.
	 */
		
	@RequestMapping(value = { "/edit-producto-{descripcion}" }, method = RequestMethod.GET)
	public String editProducto(@PathVariable String descripcion, ModelMap model) {
		Producto producto = productoService.findByDescripcion(descripcion);
		model.addAttribute("producto", producto);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	
	
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating  in database. It also validates the  input
	 */
	
	 
	 @RequestMapping(value = { "/edit-producto-{descripcion}" }, method = RequestMethod.POST)
	public String updateProducto(@Valid Producto producto, BindingResult result,
			ModelMap model, @PathVariable String descripcion) {

		if (result.hasErrors()) {
			return "registration";
		}

		


		productoService.updateProducto(producto);

		model.addAttribute("success", "Producto " + producto.getDescripcion() +  " updated successfully");
		return "registrationsuccess";
	}

	
	@RequestMapping(value = { "/delete-producto-{descripcion}" }, method = RequestMethod.GET)
	public String delete(@PathVariable String descripcion) {
		productoService.deleteProductoByDescripcion(descripcion);
		return "redirect:/list";
	}
	

	/**
	 * This method will provide Categoria list to views
	 */
	@ModelAttribute("roles")
	public List<Categoria> initializeCategorias() {
		return categoriaService.findAll();
	}

}
