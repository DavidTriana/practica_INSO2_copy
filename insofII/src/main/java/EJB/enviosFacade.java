/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import controlador.UsuarioControlador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.envios;
import modelo.productos;
import modelo.usuarios;

/**
 *
 * @author gueps
 */
@Stateless
public class enviosFacade extends AbstractFacade<envios> implements enviosFacadeLocal {

    @PersistenceContext(unitName = "inso2bbddPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public enviosFacade() {
        super(envios.class);
    }

    @Override
    public List<envios> obtenerEnviosUsuario(usuarios usuario) {
        String consulta = "FROM envios e WHERE e.usuario.idUsuario=:param1";
        Query query = em.createQuery(consulta);

        query.setParameter("param1", usuario.getIdUsuario());
        List<envios> resultado = query.getResultList();
        return query.getResultList();

        /*Query q = em.createQuery("SELECT e FROM Envios e WHERE e.usuario = :usuario");
        q.setParameter("usuario", usuario);
        return q.getResultList();*/
    }

    @Override
    public List<productos> obtenerProductosEnvio(String productosString) {
        if (productosString == null || productosString.isEmpty()) {
            return null;
        }

        List<Integer> ids = Arrays.stream(productosString.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
        
        String consulta = "FROM productos p WHERE p.idProducto IN :ids";
        Query query = em.createQuery(consulta);
        query.setParameter("ids", ids);

        return query.getResultList();
    }
    
    @Override
    public List<productos> obtenerListaProductosEnvio(String productosString) {
        if (productosString == null || productosString.isEmpty()) {
            return null;
        }

        List<Integer> ids = Arrays.stream(productosString.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
        
        String consulta = "FROM productos p WHERE p.idProducto IN :ids";
        Query query = em.createQuery(consulta);
        query.setParameter("ids", ids);
        List<productos> productosUnicos = query.getResultList();
        
        Map<Integer, productos> productoMap = new HashMap<>();
        
        productos productoActual;
        for (int i=0; i<productosUnicos.size(); i++) {
                
            productoActual = productosUnicos.get(i);
            
            productoMap.put(productoActual.getIdProducto(), productoActual);
        }
        
        List<productos> resultado = new ArrayList<>();
        
        Integer id;
        for(int i=0; i< ids.size(); i++){
            
            id = ids.get(i);
            
            productos producto = productoMap.get(id);
            
            if (producto != null){
                
                resultado.add(producto);
            }
        }
        
        return resultado;
    }
    
    @Override
    public List<productos> obtenerTodosProductosEnvios(){
    
        String consulta = "SELECT e FROM envios e";
        Query query = em.createQuery(consulta);
        List<envios> listaEnvios = query.getResultList();
        
        List<productos> listaProductos = new ArrayList<>();
        List<productos> productosEnvio = new ArrayList<>();
        
        
        for(int i=0; i<listaEnvios.size(); i++){
            
            productosEnvio = obtenerListaProductosEnvio(listaEnvios.get(i).getProductos());
            
            // System.out.println("PRODUCTOS ENVIO ES "+ productosEnvio);
            
            for (int j=0; j<productosEnvio.size(); j++){
                
                listaProductos.add(productosEnvio.get(j));
            }
        }
        
        return listaProductos;
    }

}
