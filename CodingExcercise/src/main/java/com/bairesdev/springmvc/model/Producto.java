package com.bairesdev.springmvc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="PRODUCTO")
public class Producto {

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	
	
		
	@NotEmpty
	@Column(name="descripcion", nullable=false)
	private String descripcion;


	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PRODUCTO_CATEGORIA", 
             joinColumns = { @JoinColumn(name = "PRODUCTO_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "CATEGORIA_ID") })
	private Set<Categoria> categorias = new HashSet<Categoria>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Producto))
			return false;
		Producto other = (Producto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
				return true;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", " +  "descripcion=" + descripcion
				+ " ]";
	}
}
