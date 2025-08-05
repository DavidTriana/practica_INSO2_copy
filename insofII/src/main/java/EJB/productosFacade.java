/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.productos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.vendedores;

/**
 *
 * @author gueps
 */
@Stateless
public class productosFacade extends AbstractFacade<productos> implements productosFacadeLocal {

    @PersistenceContext(unitName = "inso2bbddPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public productosFacade() {
        super(productos.class);
    }
    
    @Override
    public List<productos> obtenerProductosDeVendedor(vendedores vendedor){
    
        List<productos> listaProductos = new ArrayList<>();
        
        String consulta = "FROM productos p WHERE p.vendedores=:param1";
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", vendedor);        
        listaProductos = query.getResultList();
     
        for (int i = listaProductos.size() - 1; i >= 0; i--) {
            if (listaProductos.get(i).getCantidad() != 1) {
                listaProductos.remove(i);
            }
        }
        
        //System.out.println(listaProductos.get(0));
        
        return listaProductos;
    }
        
    @Override
    public void ocultarProducto(int idProducto){
    
        String consulta = "UPDATE productos p SET p.cantidad = 0 WHERE p.idProducto = :param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", idProducto);
        query.executeUpdate();

    }
}
