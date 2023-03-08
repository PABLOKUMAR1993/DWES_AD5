package com.camisetas.starwars.model.entity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {


	// Atributos


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

	//uni-directional many-to-one association to Familia
	@ManyToOne
	@JoinColumn(name="id_familia")
	private Familia familia;


	// Constructores


	public Producto() {}


	// Getters y Setters


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

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}


	// hashCode y equals


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Producto producto)) return false;
		return getIdProducto() == producto.getIdProducto();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIdProducto());
	}


	// toString


	@Override
	public String toString() {
		return "Producto{" +
				"idProducto=" + idProducto +
				", descripcion='" + descripcion + '\'' +
				", imagen='" + imagen + '\'' +
				", nombre='" + nombre + '\'' +
				", precio=" + precio +
				", stock=" + stock +
				", familia=" + familia +
				'}';
	}


}