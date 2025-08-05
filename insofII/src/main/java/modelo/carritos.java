/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author david
 */
@Entity
@Table(name="pedidos")
public class carritos implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPedido;
    
    @Column(name="costeTotal")
    private Double costeTotal;
    
    @JoinColumn(name="idUsuario")
    @OneToOne
    private usuarios usuario;
    
    @Column(name="productos")
    private String productos;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Double getCosteTotal() {
        return costeTotal;
    }

    public void setCosteTotal(Double costeTotal) {
        this.costeTotal = costeTotal;
    }

    public usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(usuarios usuario) {
        this.usuario = usuario;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idPedido;
        hash = 71 * hash + Objects.hashCode(this.costeTotal);
        hash = 71 * hash + Objects.hashCode(this.usuario);
        hash = 71 * hash + Objects.hashCode(this.productos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final carritos other = (carritos) obj;
        if (this.idPedido != other.idPedido) {
            return false;
        }
        if (this.costeTotal != other.costeTotal) {
            return false;
        }
        if (!Objects.equals(this.productos, other.productos)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "carritos{" + "idPedido=" + idPedido + ", costeTotal=" + costeTotal + ", usuario=" + usuario + ", productos=" + productos + '}';
    }
    
}
