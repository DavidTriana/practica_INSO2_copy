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
@Table(name="valoraciones")
public class valoraciones implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idValoracion;
    
    @Column(name="valoracion")
    private int valoracion;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="fecha")
    private String fecha;
    
    @Column(name="hora")
    private String hora;
    
    @JoinColumn(name="idUsuario" , referencedColumnName="idUsuario")
    @ManyToOne
    private usuarios idUsuario;
    
    @JoinColumn(name="idProducto" , referencedColumnName="idProducto")
    @ManyToOne
    private productos idProducto;

    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(productos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idValoracion;
        hash = 97 * hash + this.valoracion;
        hash = 97 * hash + Objects.hashCode(this.descripcion);
        hash = 97 * hash + Objects.hashCode(this.fecha);
        hash = 97 * hash + Objects.hashCode(this.hora);
        hash = 97 * hash + Objects.hashCode(this.idUsuario);
        hash = 97 * hash + Objects.hashCode(this.idProducto);
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
        final valoraciones other = (valoraciones) obj;
        if (this.idValoracion != other.idValoracion) {
            return false;
        }
        if (this.valoracion != other.valoracion) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        if (!Objects.equals(this.idProducto, other.idProducto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "valoraciones{" + "idValoracion=" + idValoracion + ", valoracion=" + valoracion + ", descripcion=" + descripcion + ", fecha=" + fecha + ", hora=" + hora + ", idUsuario=" + idUsuario + ", idProducto=" + idProducto + '}';
    }

    
    
}
