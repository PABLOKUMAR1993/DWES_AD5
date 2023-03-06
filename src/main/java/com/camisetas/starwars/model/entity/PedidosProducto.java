package com.camisetas.starwars.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name="pedidos_productos")
@NamedQuery(name="PedidosProducto.findAll", query="SELECT p FROM PedidosProducto p")
public class PedidosProducto implements Serializable {


	// Atributos


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="numero_orden")
	private int numeroOrden;

	@Column(name="precio")
	private double precio;

	private int unidades;

	//bi-directional many-to-one association to Pedido
	@JoinColumn(name="id_pedido")
	@ManyToOne
	@JsonIgnore
	private Pedido pedido;

	//uni-directional many-to-one association to Producto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_producto")
	@JsonIgnore
	private Producto producto;


	// Constructor


	public PedidosProducto() {}


	// Getters y Setters


	public int getNumeroOrden() {
		return this.numeroOrden;
	}

	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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


	// hashCode y equals


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PedidosProducto that)) return false;
		return getNumeroOrden() == that.getNumeroOrden();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNumeroOrden());
	}


	// toString


	@Override
	public String toString() {
		return "PedidosProducto{" +
				"numeroOrden=" + numeroOrden +
				", precio=" + precio +
				", unidades=" + unidades +
//				", pedido=" + pedido +
				", producto=" + producto +
				'}';
	}


}