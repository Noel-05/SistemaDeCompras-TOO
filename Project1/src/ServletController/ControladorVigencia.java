package ServletController;

import JavaBeans.Articulos;
import JavaBeans.Departamento;
import JavaBeans.Empleado;
import JavaBeans.ModeloVigencia;
import JavaBeans.RequisicionVigenciaCompra;
import JavaBeans.Vigencia;
import JavaBeans.SolicitudArt;

import Utils.ConexionBaseDatos;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ControladorVigencia", urlPatterns = { "/controladorvigencia" })
public class ControladorVigencia extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    
    private ModeloVigencia modeloVigencia;
    private ConexionBaseDatos origenDatos;
    

    public void init() throws ServletException {
        super.init();
        
        try{
            modeloVigencia = new ModeloVigencia(origenDatos);
        }catch(Exception e){
            throw new ServletException();
        }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Leer el parametro enviado
        String inst = request.getParameter("instruccion");
        
        if(inst==null) inst="listar";
        
        switch(inst){
        
        case "listar":
            obtenerArticulos(request, response);
            break;
        
        case "insertarBD":
            insertarVigencia(request, response);
            break;
        
        case "detalle":
            obtenerVigencias(request, response);
            break;
        case "recuperar":
                try {
                    cargarVigencia(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            break;
        
        case "actualizarBD":
                try {
                    actualizarVigencia(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            break;
        case "eliminar":
                try {
                    eliminarVigencia(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        
        }
        
    }
    
    
    private void obtenerArticulos(HttpServletRequest request, HttpServletResponse response){
        //Obtener la lista de Articulos desde el modelo
        
        List<Articulos> articulos;
        
        try{
            
            articulos = modeloVigencia.getArticulos();
           
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAARTICULOS", articulos);
            
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/gestionarArticulo.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }
    

    private void insertarVigencia(HttpServletRequest request, HttpServletResponse response) {
        
        // Leer la información del producto que viene del formulario
        
        String proveedor = request.getParameter("proveedor");
        String articulo = request.getParameter("articulo");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDesde = null;
        Date fechaHasta = null;
        try {
            fechaDesde = formatoFecha.parse(request.getParameter("fechaDesde"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        try {
            fechaHasta = formatoFecha.parse(request.getParameter("fechaHasta"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        float precio = Float.parseFloat(request.getParameter("precio"));
        float descuento = Float.parseFloat(request.getParameter("descuento"));
        
        int tiempoEntrega = Integer.parseInt(request.getParameter("tiempoEntrega"));
        int periodoGracia = Integer.parseInt(request.getParameter("periodoGracia"));
        
        //Crear un objeto de tipo Vigencia
        
        Vigencia nuevaVigencia = new Vigencia(articulo, proveedor, fechaDesde, fechaHasta, descuento, precio, tiempoEntrega, periodoGracia);        
        //Enviar el objeto al Modelo y despues insertar el objeto en la BD
        
        modeloVigencia.agregarNuevaVigencia(nuevaVigencia);
        
        obtenerArticulos(request, response);
        
    }
    

    private void obtenerVigencias(HttpServletRequest request, HttpServletResponse response) {
        // String departamento = request.getParameter("departamento");
        
        //Crear un objeto de tipo RequisicionVigenciaCompra
        Vigencia consultaVigencias = new Vigencia();
        
        List<Vigencia> vigencias;
        List<Articulos> articulos;
        
        try{
            
            vigencias = modeloVigencia.obtenerVigencias(consultaVigencias);
            articulos = modeloVigencia.getArticulos();
            
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAVIGENCIAS", vigencias);
            request.setAttribute("LISTAARTICULOS", articulos);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/gestionarArticulo.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void cargarVigencia(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        //Leer el Código del articulo
        String codArticulo = request.getParameter("codArticulo");
        //Enviar Código al modelo
        Vigencia vigencia = modeloVigencia.getVigencia(codArticulo);
        //Colocar el atributo correspondiente al Código Articulo
        request.setAttribute("VIGENCIAACTUALIZAR", vigencia);
        
        RequestDispatcher miDispatcher = request.getRequestDispatcher("/actualizarVigencia.jsp");
        miDispatcher.forward(request, response);
    }

    private void actualizarVigencia(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        //Leer los datos que vienen del formulario
        
        String proveedor = request.getParameter("proveedor");
        String articulo = request.getParameter("articulo");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDesde = null;
        Date fechaHasta = null;
        try {
            fechaDesde = formatoFecha.parse(request.getParameter("fechaDesde"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        try {
            fechaHasta = formatoFecha.parse(request.getParameter("fechaHasta"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        float precio = Float.parseFloat(request.getParameter("precio"));
        float descuento = Float.parseFloat(request.getParameter("descuento"));
        
        int tiempoEntrega = Integer.parseInt(request.getParameter("tiempoEntrega"));
        int periodoGracia = Integer.parseInt(request.getParameter("periodoGracia"));
        
        //Crear un objeto de tipo Vigencia
        
        Vigencia VigenciaActualizada = new Vigencia(articulo, proveedor, fechaDesde, fechaHasta, descuento, precio, tiempoEntrega, periodoGracia); 
        
        //Actualizar la BD
        modeloVigencia.actualizarVigencia(VigenciaActualizada);
        obtenerArticulos(request, response);
        
    }

    private void eliminarVigencia(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String codArticulo = request.getParameter("codArticulo");
        modeloVigencia.borrarVigencia(codArticulo);
        obtenerArticulos(request, response);
        
        
    }
}
