package com.camisetas.starwars.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the tarjetas database table.
 * 
 */
@Entity
@Table(name="tarjetas")
@NamedQuery(name="Tarjeta.findAll", query="SELECT t FROM Tarjeta t")
public class Tarjeta implements Serializable {


	// Atributos


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tarjeta")
	private int idTarjeta;

	private String cvv;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_caducidad")
	private Date fechaCaducidad;

	private String nombre;

	private String numero;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="tarjeta")
	private List<Pedido> pedidos;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="tarjetas")
	private List<Usuario> usuarios;


	// Constructor


	public Tarjeta() {
	}


	// Getters & Setters


	public int getIdTarjeta() {
		return this.idTarjeta;
	}

	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getCvv() {
		return this.cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Date getFechaCaducidad() {
		return this.fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setTarjeta(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setTarjeta(null);

		return pedido;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	// HashCode & Equals


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tarjeta tarjeta)) return false;
		return getNumero().equals(tarjeta.getNumero());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNumero());
	}


	// toString


	@Override
	public String toString() {
		return "Tarjeta{" +
				"idTarjeta=" + idTarjeta +
				", cvv='" + cvv + '\'' +
				", fechaCaducidad=" + fechaCaducidad +
				", nombre='" + nombre + '\'' +
				", numero='" + numero + '\'' +
				", pedidos=" + pedidos +
				", usuarios=" + usuarios +
				'}';
	}


}