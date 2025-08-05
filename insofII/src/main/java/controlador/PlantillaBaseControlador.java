/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author david
 */
@Named
@ViewScoped
public class PlantillaBaseControlador implements Serializable {

    public void verificarYMostrar() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Object usuario = facesContext.getExternalContext().getSessionMap().get("usuario");
        String currentPage = facesContext.getViewRoot().getViewId();

        if (usuario == null && !currentPage.contains("login.xhtml")) {
            String contextPath = facesContext.getExternalContext().getApplicationContextPath();
            facesContext.getExternalContext().redirect(contextPath + "/faces/login.xhtml");
        }
    }

    public String irCarrito() {
        return "carritoUsuario.xhtml?faces-redirect=true";
    }

    public String irProductos() {
        return "productosGeneral.xhtml?faces-redirect=true";
    }

    public String irEnviosUsuario() {
        return "enviosUsuario.xhtml?faces-redirect=true";
    }

    public String irPrincipal() {
        return "principalUsuario.xhtml?faces-redirect=true";
    }

    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

}
