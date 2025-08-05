/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.enviosFacade;
import EJB.usuariosFacadeLocal;
import EJB.valoracionesFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.envios;
import modelo.usuarios;
import modelo.valoraciones;
import org.primefaces.PrimeFaces;

/**
 *
 * @author gueps
 */
@Named
@ViewScoped
public class UsuarioControlador implements Serializable {

    private usuarios usuario;

    private List<valoraciones> valoracionesUsuario;

    @EJB
    private usuariosFacadeLocal usuarioEJB;

    @EJB
    private valoracionesFacadeLocal valoracionEJB;

    @PostConstruct
    public void init() {
        usuario = (usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        valoracionesUsuario = valoracionEJB.findByUsuario(usuario);
        /*Si el usuario no está en la sesion*/
        if (usuario == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario no encontrado en la sesion"));
        }
    }

    public void insertarUsuario() {
        try {
            usuarioEJB.create(usuario);
        } catch (Exception e) {
            System.out.println("Error al insertar usuario a la base de datos" + e.getMessage());
        }
    }

    public void actualizarDatos() {
        try {
            usuarioEJB.edit(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos actualizados correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar los datos"));

        }
    }

    public void eliminarValoracion(valoraciones valoracion) {
        try {
            valoracionEJB.remove(valoracion);
            this.valoracionesUsuario = valoracionEJB.findByUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Valoracion eliminada correctamente"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la valoracion"));

        }
    }

    public usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(usuarios usuario) {
        this.usuario = usuario;
    }

    public usuariosFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(usuariosFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public List<valoraciones> getValoracionesUsuario() {
        return valoracionesUsuario;
    }

    public void setValoracionesUsuario(List<valoraciones> valoracionesUsuario) {
        this.valoracionesUsuario = valoracionesUsuario;
    }

    public valoracionesFacadeLocal getValoracionEJB() {
        return valoracionEJB;
    }

    public void setValoracionEJB(valoracionesFacadeLocal valoracionEJB) {
        this.valoracionEJB = valoracionEJB;
    }

    public void borrarCuenta() {
        try {
            usuario.setNombre(null);
            usuarioEJB.edit(usuario);
            PrimeFaces.current().executeScript("PF('dlgEliminar').show();");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }
}
