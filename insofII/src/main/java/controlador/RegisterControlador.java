/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.carritosFacadeLocal;
import EJB.usuariosFacadeLocal;
import EJB.vendedoresFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.carritos;
import modelo.usuarios;
import modelo.vendedores;
import org.primefaces.PrimeFaces;

/**
 *
 * @author gueps
 */
@ManagedBean
@ViewScoped
/* El bean solo estará disponible mientras el usuario esté interactuando con la vista*/
public class RegisterControlador implements Serializable {

    private String tipoUsuario;
    private usuarios usuario = new usuarios();
    private vendedores vendedor = new vendedores();
    private carritos carrito = new carritos();

    @EJB
    private usuariosFacadeLocal usuarioFacadeLocal;

    @EJB
    private vendedoresFacadeLocal vendedorFacadeLocal;

    @EJB
    private carritosFacadeLocal carritosFacadeLocal;

    public String registrarUsuario() {
        String nav = "login.xhtml?faces-redirect=true";
        if (tipoUsuario.equals("normal")) {
            usuarioFacadeLocal.create(usuario);
            carrito.setUsuario(usuario);
            carritosFacadeLocal.create(carrito);
            usuario = new usuarios();
            carrito = new carritos();
        } else if (tipoUsuario.equals("vendedor")) {
            vendedorFacadeLocal.create(vendedor);
            vendedor = new vendedores();
        }
        return nav;
    }

    /*Getters y setters de tipoUsuario, usuario y vendedor*/
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(usuarios usuario) {
        this.usuario = usuario;
    }

    public vendedores getVendedor() {
        return vendedor;
    }

    public void setVendedor(vendedores vendedor) {
        this.vendedor = vendedor;
    }

    /* Getter y setters de usuarios*/
    public int getIdUsuario() {
        return usuario.getIdUsuario();
    }

    public void setIdUsuario(int idUsuario) {
        usuario.setIdUsuario(idUsuario);
    }

    public String getNombreUsuario() {
        return usuario.getNombre();
    }

    public void setNombreUsuario(String nombre) {
        usuario.setNombre(nombre);
    }

    public String getTelefonoUsuario() {
        return usuario.getNombre();
    }

    public void setTelefonoUsuario(String telefono) {
        usuario.setTelefono(telefono);
    }

    public String getEmailUsuario() {
        return usuario.getEmail();
    }

    public void setEmailUsuario(String email) {
        usuario.setEmail(email);
    }

    public String getContraseniaUsuario() {
        return usuario.getContrasenia();
    }

    public void setContraseniaUsuario(String contrasenia) {
        usuario.setContrasenia(contrasenia);
    }

    public String getDireccionUsuario() {
        return usuario.getDireccion();
    }

    public void setDireccionUsuario(String direccion) {
        usuario.setDireccion(direccion);
    }

    public String getTarjetaBancoUsuario() {
        return usuario.getTarjetaBanco();
    }

    public void setTarjetaBancoUsuario(String tarjetaBanco) {
        usuario.setTarjetaBanco(tarjetaBanco);
    }

    /* Getters y setters de vendedor*/
    public int getIdVendedor() {
        return vendedor.getIdVendedor();
    }

    public void setIdVendedor(int idVendedor) {
        vendedor.setIdVendedor(idVendedor);
    }

    public String getNombreVendedor() {
        return vendedor.getNombre();
    }

    public void setNombreVendedor(String nombre) {
        vendedor.setNombre(nombre);
    }

    public String getTelefonoVendedor() {
        return vendedor.getTelefono();
    }

    public void setTelefonoVendedor(String telefono) {
        vendedor.setTelefono(telefono);
    }

    public String getEmailVendedor() {
        return vendedor.getEmail();
    }

    public void setEmailVendedor(String email) {
        vendedor.setEmail(email);
    }

    public String getContraseñaVendedor() {
        return vendedor.getContraseña();
    }

    public void setContraseñaVendedor(String contraseña) {
        vendedor.setContraseña(contraseña);
    }

    public String getTarjetaBancoVendedor() {
        return vendedor.getTarjetaBanco();
    }

    public void setTarjetaBancoVendedor(String tarjetaBanco) {
        vendedor.setTarjetaBanco(tarjetaBanco);
    }

}
