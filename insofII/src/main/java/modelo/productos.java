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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gueps
 */

@Entity 
@Table(name="productos")
public class productos implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idProducto;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="marca")
    private String marca;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="categoria")
    private String categoria;
    
    @Column(name="precio")
    private double precio;
    
    @Column(name="cantidad")
    private int cantidad;
    
    @Column(name="valoraciones")
    private int valoraciones;
    
    @JoinColumn(name="vendedores", referencedColumnName="idVendedor")
    @ManyToOne
    private vendedores vendedores;

     @JoinColumn(name="pedidos", referencedColumnName="IDPEDIDO")
     @ManyToOne
     private carritos carritos;
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(int valoraciones) {
        this.valoraciones = valoraciones;
    }

    public vendedores getVendedores() {
        return vendedores;
    }

    public void setVendedores(vendedores vendedores) {
        this.vendedores = vendedores;
    }

    public carritos getCarritos() {
        return carritos;
    }

    public void setCarritos(carritos carritos) {
        this.carritos = carritos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.idProducto;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.marca);
        hash = 97 * hash + Objects.hashCode(this.descripcion);
        hash = 97 * hash + Objects.hashCode(this.categoria);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        hash = 97 * hash + this.cantidad;
        hash = 97 * hash + this.valoraciones;
        hash = 97 * hash + Objects.hashCode(this.vendedores);
        hash = 97 * hash + Objects.hashCode(this.carritos);
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
        final productos other = (productos) obj;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (this.valoraciones != other.valoraciones) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.vendedores, other.vendedores)) {
            return false;
        }
        if (!Objects.equals(this.carritos, other.carritos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "productos{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", marca=" + marca + ", descripcion=" + descripcion + ", categoria=" + categoria + ", precio=" + precio + ", cantidad=" + cantidad + ", valoraciones=" + valoraciones + ", vendedores=" + vendedores + ", carritos=" + carritos + '}';
    }
    
    
}
