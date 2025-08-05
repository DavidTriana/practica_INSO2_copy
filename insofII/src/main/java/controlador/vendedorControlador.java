/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.enviosFacadeLocal;
import EJB.productosFacadeLocal;
import EJB.vendedoresFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import modelo.vendedores;
import modelo.productos;
import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author david
 */
@Named
@ViewScoped
public class vendedorControlador implements Serializable{
    private vendedores vendedor;
    private List<productos> productos;
    private productos nuevoProducto;
    private productos productoSeleccionado;
    
    private BarChartModel barModel;
    private int anchoGrafico;

    private BarChartModel barModelProfit;
    
    private List<String> categorias;

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public BarChartModel getBarModelProfit() {
        return barModelProfit;
    }

    public void setBarModelProfit(BarChartModel barModelProfit) {
        this.barModelProfit = barModelProfit;
    }

    public enviosFacadeLocal getEnviosEJB() {
        return enviosEJB;
    }

    public void setEnviosEJB(enviosFacadeLocal enviosEJB) {
        this.enviosEJB = enviosEJB;
    }

    
    public int getAnchoGrafico() {
        return anchoGrafico;
    }

    public void setAnchoGrafico(int anchoGrafico) {
        this.anchoGrafico = anchoGrafico;
    }


    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public productos getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(productos productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }
    
    
    @EJB
    private vendedoresFacadeLocal vendedorEJB;
    
    @EJB
    private productosFacadeLocal productoEJB;
    
    @EJB
    private enviosFacadeLocal enviosEJB;
    
    @PostConstruct
    public void init(){
        
        vendedor = new vendedores();
        nuevoProducto = new productos();
        
        vendedor = (vendedores) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        //System.out.println("el vendedor es: "+ vendedor.getNombre());
        
        crearTabla();
        
        nuevoProducto.setMarca("Marca propia");
        
        categorias = new ArrayList<>();
        categorias.add("Electrónica");
        categorias.add("Videojuegos");
        categorias.add("Juguetes");
        categorias.add("Ropa");
        categorias.add("Hogar");
        categorias.add("Alimentación");
        categorias.add("Papelería");
        categorias.add("Belleza y cosméticos");
        categorias.add("Misceláneo");

    }
    public void insertarVendedor(){
        try{
            vendedorEJB.create(vendedor);
        }catch(Exception e){
            System.out.println("Error al insertar vendedor a la base de datos" + e.getMessage());
        }
    }

    private void crearTabla() {
        
        productos = productoEJB.obtenerProductosDeVendedor(vendedor);
        nuevoProducto.setMarca("Marca propia");
        
        
        List<productos> productosCompradosTotales = enviosEJB.obtenerTodosProductosEnvios();
        List<productos> productosCompradosActuales = new ArrayList<>();
        
        //System.out.println(productosCompradosTotales);
        // Se calculan los productos que se van a representar en las graficas del dashboard
        for (int i=0; i<productosCompradosTotales.size(); i++) {
        
            for (int j=0; j<productos.size(); j++){
            
                if(productosCompradosTotales.get(i).getIdProducto() == productos.get(j).getIdProducto()){
                    
                    productosCompradosActuales.add(productosCompradosTotales.get(i));
                }
            }
        }
        
        
        barModel = null;
        barModel = new BarChartModel();
        
        ChartData data = new ChartData();

        //datos de la tabla de ventas
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Ventas");

        
        List<String> nombresProductos = new ArrayList<>();
        List<Number> ventasProductos = new ArrayList<>();
        List<Number> beneficioProductos = new ArrayList<>();
        
        for (int i=0; i<productos.size(); i++){
            
            nombresProductos.add(productos.get(i).getNombre());
            ventasProductos.add(contarRepeticionesProducto(productosCompradosActuales, productos.get(i)));
            beneficioProductos.add(contarRepeticionesProducto(productosCompradosActuales, productos.get(i)) * productos.get(i).getPrecio());
        }
        
        barDataSet.setData(ventasProductos);
        barDataSet.setBackgroundColor("rgba(75, 192, 192, 0.2)");
        barDataSet.setBorderColor("rgb(75, 192, 192)");
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);
        
        List<String> labels = nombresProductos;
        data.setLabels(labels);

        barModel.setData(data);
        
        
        //datos de la tabla de beneficios
        BarChartDataSet barDataSetProfits = new BarChartDataSet();
        barDataSetProfits.setLabel("Ingresos");

        barDataSetProfits.setData(beneficioProductos);
        barDataSetProfits.setBackgroundColor("rgba(255, 165, 0, 0.2)");
        barDataSetProfits.setBorderColor("rgb(255, 165, 0)");
        barDataSetProfits.setBorderWidth(1);
        
        ChartData dataProfit = new ChartData();


        dataProfit.addChartDataSet(barDataSetProfits);
        dataProfit.setLabels(nombresProductos);
        
        barModelProfit = null;
        barModelProfit = new BarChartModel();
        barModelProfit.setData(dataProfit);
        
        
        anchoGrafico = productos.size() > 6 ? productos.size() * 100 : 600;
    
    }
    
    public int contarRepeticionesProducto(List<productos> listaProductos, productos producto) {
    
        int contador = 0;
            
        for (int i=0; i<listaProductos.size(); i++){
            
            if(listaProductos.get(i).getIdProducto() == producto.getIdProducto()){
                
                contador++;
            }
        }
        
        return contador;
    }
    
    public vendedores getVendedor() {
        return vendedor;
    }

    public void setUsuario(vendedores vendedor) {
        this.vendedor = vendedor;
    }

    public vendedoresFacadeLocal getUsuarioEJB() {
        return vendedorEJB;
    }

    public void setUsuarioEJB(vendedoresFacadeLocal vendedorEJB) {
        this.vendedorEJB = vendedorEJB;
    }
    
    
    public List<productos> getProductos() {
        return productos;
    }

    public void setProductos(List<productos> productos) {
        this.productos = productos;
    }

    public productos getNuevoProducto() {
        return nuevoProducto;
    }

    public void setNuevoProducto(productos nuevoProducto) {
        this.nuevoProducto = nuevoProducto;
    }

    public vendedoresFacadeLocal getVendedorEJB() {
        return vendedorEJB;
    }

    public void setVendedorEJB(vendedoresFacadeLocal vendedorEJB) {
        this.vendedorEJB = vendedorEJB;
    }

    public productosFacadeLocal getProductoEJB() {
        return productoEJB;
    }

    public void setProductoEJB(productosFacadeLocal productoEJB) {
        this.productoEJB = productoEJB;
    }
    
    public void guardarNuevoProducto() {
    
        // sSystem.out.println("SE VA A GUARDAR UN PRODUCTO NUEVO: "+ nuevoProducto.getNombre());
        
        if (productos.size() < 10) {
            
            nuevoProducto.setVendedores(vendedor);
            nuevoProducto.setCantidad(1);

            productoEJB.create(nuevoProducto);

            productos = productoEJB.obtenerProductosDeVendedor(vendedor);
            
            nuevoProducto = new productos();
            
        crearTabla();
        } else {
        
            PrimeFaces.current().executeScript("PF('dlgMaxProductos').show();");
        }        
        
    }
    
    public void eliminarProducto(int idProducto){
        
        
        //System.out.println("se va a eliminar un producto "+ idProducto);
        productoEJB.ocultarProducto(idProducto);
    
        crearTabla();
    }
    
    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }
    
    public String irConfiguracion() {
        return "configVendedor.xhtml?faces-redirect=true";
    }
    
    public String irPaginaPrincipal() {
        return "principalVendedor.xhtml?faces-redirect=true";
    }
}
