/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.enviosFacadeLocal;
import EJB.usuariosFacade;
import EJB.usuariosFacadeLocal;
import EJB.valoracionesFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.envios;
import modelo.productos;
import modelo.usuarios;
import modelo.valoraciones;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author david
 */
@Named  //Indicar que la clase esta asociada con un EJB
@ViewScoped //Indicar que solo existe mientras exista la vista asociada
public class EnviosUsuarioControlador implements Serializable {

    @EJB
    private enviosFacadeLocal enviosFacade;

    @EJB
    private usuariosFacadeLocal usuariosFacade;

    @EJB
    private valoracionesFacadeLocal valoracionesFacade;

    @Inject
    private UsuarioControlador usuarioControlador;

    private List<envios> enviosUsuario;

    private envios envioSeleccionado;

    private List<productos> productosEnvioSeleccionado;

    private productos productoSeleccionado;

    private valoraciones nuevaValoracion;

    @PostConstruct
    public void init() {
        usuarios user = usuarioControlador.getUsuario();
        enviosUsuario = enviosFacade.obtenerEnviosUsuario(user);
        productosEnvioSeleccionado = Collections.emptyList();
        productoSeleccionado = new productos();
        nuevaValoracion = new valoraciones();
    }

    public List<envios> getEnviosUsuario() {
        return enviosUsuario;
    }

    //meter usuario desde la variable global y desde ahi sacar la lista de envios
    public enviosFacadeLocal getEnviosFacade() {
        return enviosFacade;
    }

    public void setEnviosFacade(enviosFacadeLocal enviosFacade) {
        this.enviosFacade = enviosFacade;
    }

    public usuariosFacadeLocal getUsuariosFacade() {
        return usuariosFacade;
    }

    public void setUsuariosFacade(usuariosFacadeLocal usuariosFacade) {
        this.usuariosFacade = usuariosFacade;
    }

    public UsuarioControlador getUsuarioControlador() {
        return usuarioControlador;
    }

    public void setUsuarioControlador(UsuarioControlador usuarioControlador) {
        this.usuarioControlador = usuarioControlador;
    }

    public envios getEnvioSeleccionado() {
        return envioSeleccionado;
    }

    public void setEnvioSeleccionado(envios envioSeleccionado) {
        this.envioSeleccionado = envioSeleccionado;
    }

    public List<productos> getProductosEnvioSeleccionado() {
        return productosEnvioSeleccionado;
    }

    public void setProductosEnvioSeleccionado(List<productos> productosEnvioSeleccionado) {
        this.productosEnvioSeleccionado = productosEnvioSeleccionado;
    }

    public productos getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(productos productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public valoraciones getNuevaValoracion() {
        return nuevaValoracion;
    }

    public void setNuevaValoracion(valoraciones nuevaValoracion) {
        this.nuevaValoracion = nuevaValoracion;
    }

    public void onRowToggle(ToggleEvent event) {
        envioSeleccionado = (envios) event.getData();
        productosEnvioSeleccionado = enviosFacade.obtenerProductosEnvio(envioSeleccionado.getProductos());
    }

    public void crearValoracion() {
        //System.out.println(this.nuevaValoracion.toString());
        usuarios usuario = (usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String horaActual = new SimpleDateFormat("HH:mm:ss").format(new Date());

        this.nuevaValoracion.setFecha(fechaActual);
        this.nuevaValoracion.setHora(horaActual);

        this.nuevaValoracion.setIdUsuario(usuario);
        this.nuevaValoracion.setIdProducto(productoSeleccionado);

        valoracionesFacade.create(nuevaValoracion);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Se ha creado correctamente la valoracion"));
    }

    
}
