package com.camisetas.starwars.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto")
	private int idProducto;

	private String descripcion;

	private String imagen;

	private String nombre;

	private double precio;

	private int stock;

	//bi-directional many-to-one association to PedidosProducto
	@OneToMany(mappedBy="producto")
	private List<PedidosProducto> pedidosProductos;

	public Producto() {
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<PedidosProducto> getPedidosProductos() {
		return this.pedidosProductos;
	}

	public void setPedidosProductos(List<PedidosProducto> pedidosProductos) {
		this.pedidosProductos = pedidosProductos;
	}

	public PedidosProducto addPedidosProducto(PedidosProducto pedidosProducto) {
		getPedidosProductos().add(pedidosProducto);
		pedidosProducto.setProducto(this);

		return pedidosProducto;
	}

	public PedidosProducto removePedidosProducto(PedidosProducto pedidosProducto) {
		getPedidosProductos().remove(pedidosProducto);
		pedidosProducto.setProducto(null);

		return pedidosProducto;
	}

}