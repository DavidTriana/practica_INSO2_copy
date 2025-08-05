/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.productos;
import modelo.usuarios;
import modelo.valoraciones;

/**
 *
 * @author gueps
 */
@Local
public interface valoracionesFacadeLocal {

    void create(valoraciones valoraciones);

    void edit(valoraciones valoraciones);

    void remove(valoraciones valoraciones);

    valoraciones find(Object id);

    List<valoraciones> findAll();

    List<valoraciones> findRange(int[] range);

    int count();
    
    public List<valoraciones> findByProducto(productos producto);
    
    public List<valoraciones> findByUsuario(usuarios usuario);    
}
