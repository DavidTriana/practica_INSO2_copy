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
import modelo.vendedores;
import modelo.productos;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author gueps
 */
@Stateless
public class vendedoresFacade extends AbstractFacade<vendedores> implements vendedoresFacadeLocal {

    @PersistenceContext(unitName = "inso2bbddPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public vendedoresFacade() {
        super(vendedores.class);
    }
    
    @Override
    public vendedores verificarVendedor(vendedores vendedor){
        String consulta = "FROM vendedores u WHERE u.email=:param1 and u.contraseña=:param2";
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", vendedor.getEmail());
        query.setParameter("param2", vendedor.getContraseña());
        
        List<vendedores> resultado = query.getResultList();
         if (!resultado.isEmpty()) {
             
            if (resultado.get(0).getNombre() == null) {
                return null;
            }
            return resultado.get(0);
        } else {
            return null;
        }

    }
    
    public List<productos> obtenerProductosVendedor(vendedores vendedor) {
            
        String consulta = "FROM productos p WHERE p.vendedores=:param1";

        Query query = em.createQuery(consulta);

        query.setParameter("param1", vendedor.getIdVendedor());
        
        List<productos> listaProductos = query.getResultList();

        
        return listaProductos;
    }
    
}
