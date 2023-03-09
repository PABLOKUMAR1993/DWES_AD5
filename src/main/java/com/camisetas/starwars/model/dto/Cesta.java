package com.camisetas.starwars.model.dto;
import com.camisetas.starwars.model.entity.Producto;
import java.io.Serializable;
import java.util.Objects;


/**
 * Esta clase Data Transfer Object (DTO) representa la cesta de la compra (Producto + cantidad).
 */
public class Cesta implements Serializable {


    // Atributos


    private static final long serialVersionUID = 1L;

    private Producto producto;

    private int cantidad;


    // Constructores


    public Cesta() {}

    public Cesta(Producto producto, double precio, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }


    // Getters y Setters


    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    // HashCode y Equals


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cesta cesta)) return false;
        return getProducto().equals(cesta.getProducto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProducto());
    }


    // toString


    @Override
    public String toString() {
        return "Cesta{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }


}
