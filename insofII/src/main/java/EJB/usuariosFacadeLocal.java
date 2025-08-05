/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.usuarios;

/**
 *
 * @author gueps
 */
@Local
public interface usuariosFacadeLocal {

    void create(usuarios usuarios);

    void edit(usuarios usuarios);

    void remove(usuarios usuarios);

    usuarios find(Object id);

    List<usuarios> findAll();

    List<usuarios> findRange(int[] range);

    int count();
    
    usuarios verificarUsuario(usuarios usuario);
}
