package com.camisetas.starwars.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the pedidos_productos database table.
 * 
 */
@Embeddable
public class PedidosProductoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_pedido", insertable=false, updatable=false)
	private int idPedido;

	@Column(name="id_producto", insertable=false, updatable=false)
	private int idProducto;

	public PedidosProductoPK() {
	}
	public int getIdPedido() {
		return this.idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdProducto() {
		return this.idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PedidosProductoPK)) {
			return false;
		}
		PedidosProductoPK castOther = (PedidosProductoPK)other;
		return 
			(this.idPedido == castOther.idPedido)
			&& (this.idProducto == castOther.idProducto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPedido;
		hash = hash * prime + this.idProducto;
		
		return hash;
	}
}