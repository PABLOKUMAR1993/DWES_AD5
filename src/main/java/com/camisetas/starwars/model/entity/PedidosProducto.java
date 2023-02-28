package com.camisetas.starwars.model.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the pedidos_productos database table.
 * 
 */
@Entity
@Table(name="pedidos_productos")
@NamedQuery(name="PedidosProducto.findAll", query="SELECT p FROM PedidosProducto p")
public class PedidosProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PedidosProductoPK id;

	@Column(name="precio_unitario")
	private double precioUnitario;

	private int unidades;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="id_pedido", insertable=false, updatable=false)
	private Pedido pedido;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto", insertable=false, updatable=false)
	private Producto producto;

	public PedidosProducto() {
	}

	public PedidosProductoPK getId() {
		return this.id;
	}

	public void setId(PedidosProductoPK id) {
		this.id = id;
	}

	public double getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getUnidades() {
		return this.unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}