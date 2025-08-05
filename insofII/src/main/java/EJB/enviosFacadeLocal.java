/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.envios;
import modelo.productos;
import modelo.usuarios;

/**
 *
 * @author gueps
 */
@Local
public interface enviosFacadeLocal {

    void create(envios envios);

    void edit(envios envios);

    void remove(envios envios);

    envios find(Object id);

    List<envios> findAll();

    List<envios> findRange(int[] range);

    int count();
    
    List<envios> obtenerEnviosUsuario(usuarios usuario);
    
    List<productos> obtenerProductosEnvio(String productosString);
    
    List<productos> obtenerListaProductosEnvio(String productosString);
    
    List<productos> obtenerTodosProductosEnvios();
    
}
