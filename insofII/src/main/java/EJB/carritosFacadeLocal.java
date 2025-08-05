/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.carritos;
import modelo.productos;
import modelo.usuarios;

/**
 *
 * @author gueps
 */
@Local
public interface carritosFacadeLocal {

    void create(carritos carritos);

    void edit(carritos carritos);

    void remove(carritos carritos);

    carritos find(Object id);

    List<carritos> findAll();

    List<carritos> findRange(int[] range);

    int count();
    
    carritos findCarritoByUsuario(usuarios usuario);
    
    void removeCarritoByUsuario(usuarios usuario);

    void addProducto(carritos carrito, productos producto);
}
