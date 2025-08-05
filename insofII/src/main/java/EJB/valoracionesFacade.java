/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.productos;
import modelo.usuarios;
import modelo.valoraciones;

/**
 *
 * @author gueps
 */
@Stateless
public class valoracionesFacade extends AbstractFacade<valoraciones> implements valoracionesFacadeLocal {

    @PersistenceContext(unitName = "inso2bbddPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public valoracionesFacade() {
        super(valoraciones.class);
    }

    @Override
    public List<valoraciones> findByProducto(productos producto) {
        String consulta = "SELECT v FROM valoraciones v WHERE v.idProducto = :productos";

        Query query = em.createQuery(consulta);

        query.setParameter("productos", producto);

        List<valoraciones> resultado = query.getResultList();

        if (!resultado.isEmpty()) {
            return resultado;
        } else {
            return null;
        }
    }
    
    @Override
    public List<valoraciones> findByUsuario(usuarios usuario) {
        String consulta = "SELECT v FROM valoraciones v WHERE v.idUsuario = :usuarios";

        Query query = em.createQuery(consulta);

        query.setParameter("usuarios", usuario);

        List<valoraciones> resultado = query.getResultList();

        if (!resultado.isEmpty()) {
            return resultado;
        } else {
            return null;
        }
    }

}
