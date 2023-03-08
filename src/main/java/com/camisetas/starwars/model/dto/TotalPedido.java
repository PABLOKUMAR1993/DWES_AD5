package com.camisetas.starwars.model.dto;


import java.io.Serializable;

/**
 * Esta clase representa la suma del importe de los productos y cantidades de un pedido.
 * Ha sido creada para poder almacenar en un solo objeto el importe de un pedido y también el id del pedido al que
 * pertence.
 */
public class TotalPedido implements Serializable {


    // Atributos

    private static final long serialVersionUID = 1L;

    private int idPedido;
    private double totalPedido;


    // Constructores


    public TotalPedido(int idPedido, double total) {
        this.idPedido = idPedido;
        this.totalPedido = total;
    }


    // Getters y Setters


    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }


    // toString


    @Override
    public String toString() {
        return "TotalPedido{" +
                "idPedido=" + idPedido +
                ", totalPedido=" + totalPedido +
                '}';
    }


}
