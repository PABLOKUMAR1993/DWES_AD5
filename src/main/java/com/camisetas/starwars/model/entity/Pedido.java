package com.camisetas.starwars.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="pedidos")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {


	// Atributos


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//uni-directional many-to-one association to Direccion
	@ManyToOne
	@JoinColumn(name="id_direccion")
	private Direccion direccion;

	//uni-directional many-to-one association to Tarjeta
	@ManyToOne
	@JoinColumn(name="id_tarjeta")
	private Tarjeta tarjeta;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to PedidosProducto
	@OneToMany(mappedBy="pedido", cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<PedidosProducto> pedidosProductos;


	// Constructor


	public Pedido() {}


	// Getters y Setters


	public int getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Tarjeta getTarjeta() {
		return this.tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PedidosProducto> getPedidosProductos() {
		return this.pedidosProductos;
	}

	public void setPedidosProductos(List<PedidosProducto> pedidosProductos) {
		this.pedidosProductos = pedidosProductos;
	}


	// Métodos


	public void addPedidosProducto(PedidosProducto pedidosProducto) {
		if (pedidosProductos == null) pedidosProductos = new ArrayList<>();
		pedidosProductos.add(pedidosProducto);
	}

	public void removePedidosProducto(PedidosProducto pedidosProducto) {
		if (pedidosProductos != null) pedidosProductos = new ArrayList<>();
		pedidosProductos.remove(pedidosProducto);
	}


	// hashCode y equals


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pedido pedido)) return false;
		return getIdPedido() == pedido.getIdPedido();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIdPedido());
	}


	// toString


	@Override
	public String toString() {
		return "Pedido{" +
				"idPedido=" + idPedido +
				", estado='" + estado + '\'' +
				", fecha=" + fecha +
				", direccion=" + direccion +
				", tarjeta=" + tarjeta +
				", usuario=" + usuario +
				", pedidosProductos=" + pedidosProductos +
				'}';
	}


}