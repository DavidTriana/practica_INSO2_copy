/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.enviosFacadeLocal;
import EJB.productosFacadeLocal;
import EJB.vendedoresFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import modelo.vendedores;
import modelo.productos;
import org.primefaces.PrimeFaces;


/**
 *
 * @author david
 */
@ManagedBean(name = "ConfigVendedorControlador")
@ViewScoped
public class ConfigVendedorControlador implements Serializable{
    private vendedores vendedor;
    
    private String contraseñaNueva;
    private String contraseñaConfirmada;
    
    private String nombreNuevo;
    private String emailNuevo;
    private String telefonoNuevo;
    
    private String numeroCuentaNuevo;
    
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombreNuevo() {
        return nombreNuevo;
    }

    public void setNombreNuevo(String nombreNuevo) {
        this.nombreNuevo = nombreNuevo;
    }

    public String getEmailNuevo() {
        return emailNuevo;
    }

    public void setEmailNuevo(String emailNuevo) {
        this.emailNuevo = emailNuevo;
    }

    public String getTelefonoNuevo() {
        return telefonoNuevo;
    }

    public void setTelefonoNuevo(String telefonoNuevo) {
        this.telefonoNuevo = telefonoNuevo;
    }

    public String getNumeroCuentaNuevo() {
        return numeroCuentaNuevo;
    }

    public void setNumeroCuentaNuevo(String numeroCuentaNuevo) {
        this.numeroCuentaNuevo = numeroCuentaNuevo;
    }

    public String getContraseñaNueva() {
        return contraseñaNueva;
    }

    public void setContraseñaNueva(String contraseñaNueva) {
        this.contraseñaNueva = contraseñaNueva;
    }

    public String getContraseñaConfirmada() {
        return contraseñaConfirmada;
    }

    public void setContraseñaConfirmada(String contraseñaConfirmada) {
        this.contraseñaConfirmada = contraseñaConfirmada;
    }
    
    
    @EJB
    private vendedoresFacadeLocal vendedorEJB;
    
    @EJB
    private productosFacadeLocal productoEJB;
    
    @PostConstruct
    public void init(){
        
        vendedor = (vendedores) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
         
    }
    
    public void guardarInformacion() {
        
        // System.out.println("DATOS "+ nombreNuevo + emailNuevo + telefonoNuevo + contraseñaNueva + contraseñaConfirmada);    
        
        if (contraseñaNueva.length() > 0 || contraseñaConfirmada.length() > 0) {
            
            if (contraseñaConfirmada.equals(contraseñaNueva)) {
            
                vendedor.setContraseña(contraseñaNueva);
                
            } else {
                
                mensaje = "Las contraseñas no coinciden. Se mantendrá la contraseña anterior.";
                
                PrimeFaces.current().executeScript("PF('dlg').show();");
                
                return;
            }
        }
        
        vendedorEJB.edit(vendedor);
        
        PrimeFaces.current().executeScript("PF('dlgExito').show();");
    }
    
    public String handleConfirm() {

        return "principalVendedor.xhtml?faces-redirect=true";
    }

    
    public vendedores getVendedor() {
        return vendedor;
    }

    public void setUsuario(vendedores vendedor) {
        this.vendedor = vendedor;
    }

    public vendedoresFacadeLocal getUsuarioEJB() {
        return vendedorEJB;
    }

    public void setUsuarioEJB(vendedoresFacadeLocal vendedorEJB) {
        this.vendedorEJB = vendedorEJB;
    }

    public vendedoresFacadeLocal getVendedorEJB() {
        return vendedorEJB;
    }

    public void setVendedorEJB(vendedoresFacadeLocal vendedorEJB) {
        this.vendedorEJB = vendedorEJB;
    }

    
    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }
    
    public void eliminarCuenta() {
        
        List<productos> productos = productoEJB.obtenerProductosDeVendedor(vendedor);
        
        for(int i=0; i<productos.size(); i++) {
            
            productoEJB.ocultarProducto(productos.get(i).getIdProducto());
        }
        
        vendedor.setNombre(null);
        vendedorEJB.edit(vendedor); 
        
        PrimeFaces.current().executeScript("PF('dlgEliminar').show();");
    }
}
