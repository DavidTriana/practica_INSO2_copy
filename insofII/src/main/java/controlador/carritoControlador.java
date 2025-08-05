/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import modelo.carritos;
import modelo.productos;
import modelo.usuarios;
import EJB.carritosFacadeLocal;
import EJB.enviosFacadeLocal;
import EJB.productosFacadeLocal;
import EJB.usuariosFacadeLocal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import modelo.envios;

/**
 *
 * @author gueps
 */
@Named
@ViewScoped

public class carritoControlador implements Serializable {

    @EJB
    private carritosFacadeLocal carritosFacade;

    @EJB
    private usuariosFacadeLocal usuariosFacade;

    @EJB
    private productosFacadeLocal productosFacade;

    @EJB
    private enviosFacadeLocal enviosFacade;

    @Inject
    private UsuarioControlador usuarioControlador;

    private carritos carrito;
    private List<productos> productosList;

    public carritoControlador() {

    }

    // Método para inicializar el carrito actual del usuario
    @PostConstruct
    public void init() {
        try {
            usuarios user = usuarioControlador.getUsuario();
            if (user != null) {
                carrito = carritosFacade.findCarritoByUsuario(user);
                if (carrito != null) {
                    if (carrito.getProductos() != null && !carrito.getProductos().isEmpty()) {
                        String[] productosIds = carrito.getProductos().split(",");
                        productosList = new ArrayList<>();
                        double aux = 0;
                        for (String productoId : productosIds) {
                            productos product = productosFacade.find(Integer.parseInt(productoId));
                            aux += product.getPrecio();
                            productosList.add(product);
                        }
                        carrito.setCosteTotal(aux);
                    }

                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo inicilizar el carrito" + e.toString()));
        }

    }

    public String comprarCarrito() {
        usuarios user = usuarioControlador.getUsuario();

        if (carrito != null && carrito.getCosteTotal() != null) {
            envios envio = new envios();
            envio.setUsuario(carrito.getUsuario());
            envio.setProductos(carrito.getProductos());
            envio.setPrecioTotal(carrito.getCosteTotal());

            /*Obtener fecha y hota*/
            String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String horaActual = new SimpleDateFormat("HH:mm:ss").format(new Date());

            envio.setFecha(fechaActual);
            envio.setHora(horaActual);
            envio.setEstado("Pendiente");

            enviosFacade.create(envio);
            carritosFacade.removeCarritoByUsuario(user);

            carrito = null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra realizada con éxito", "Su compra ha sido procesada correctamente."));
            String toEmail = user.getEmail();
            String subject = "Compra realizada con éxito";
            StringBuilder body = new StringBuilder();
            body.append("Gracias por su compra. Su pedido ha sido procesado correctamente.\n\n")
                    .append("Detalles del envío:\n")
                    .append("Fecha: ").append(fechaActual).append("\n")
                    .append("Hora: ").append(horaActual).append("\n")
                    .append("Estado: Pendiente\n")
                    .append("Productos:\n");

            for (productos producto : productosList) {
                body.append("- ").append(producto.getNombre()).append(" - Precio: ").append(producto.getPrecio()).append("\n");
            }

            body.append("\nPrecio Total: ").append(envio.getPrecioTotal()).append("\n")
                    .append("\nDirección de envío: ").append(user.getDireccion()).append("\n");

            Email.sendEmail(toEmail, subject, body.toString());

            return "principalUsuario.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Carrito vacío", "Agregue productos al carrito antes de realizar la compra."));
            return "";
        }
    }

    public String eliminarCarrito() {
        usuarios user = usuarioControlador.getUsuario();
        if (user != null) {
            carritosFacade.removeCarritoByUsuario(user);
            return "carritoUsuario.xhtml?faces-redirect=true";
        }
        return "";
    }

    /* Getters y setters */
    public carritos getCarrito() {
        return carrito;
    }

    public List<productos> getProductList() {
        return productosList;
    }
}
