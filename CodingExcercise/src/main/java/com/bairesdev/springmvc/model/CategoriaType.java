package com.bairesdev.springmvc.model;


public enum CategoriaType {
	LIQUOR("LIQUOR"),
	FOOD("FOOD"),
	DRINKS("DRINKS");
	
	String categoriaType;
	
	private CategoriaType(String categoriaType){
		this.categoriaType = categoriaType;
	}
	
	public String getCategoriaType(){
		return categoriaType;
	}
	
}
