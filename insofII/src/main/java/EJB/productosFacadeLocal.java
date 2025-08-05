/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.productos;
import modelo.vendedores;

/**
 *
 * @author gueps
 */
@Local
public interface productosFacadeLocal {

    void create(productos productos);

    void edit(productos productos);

    void remove(productos productos);

    productos find(Object id);

    List<productos> findAll();

    List<productos> findRange(int[] range);

    int count();
    
    List<productos> obtenerProductosDeVendedor(vendedores vendedor);
    
    void ocultarProducto(int idProducto);
    
}
