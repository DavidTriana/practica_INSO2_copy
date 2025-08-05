/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.vendedores;

/**
 *
 * @author gueps
 */
@Local
public interface vendedoresFacadeLocal {

    void create(vendedores vendedores);

    void edit(vendedores vendedores);

    void remove(vendedores vendedores);

    vendedores find(Object id);

    List<vendedores> findAll();

    List<vendedores> findRange(int[] range);

    int count();
    
    vendedores verificarVendedor(vendedores vendedor);
    
}
