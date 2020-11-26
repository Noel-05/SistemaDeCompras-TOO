package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;

public class ModeloSolicitudArticulo {
    private ConexionBaseDatos origenDatos;
    
    public ModeloSolicitudArticulo(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }
    
    public List<Articulos> getArticulos() throws Exception{
        List<Articulos> articulos = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        String miSql = "SELECT * FROM ARTICULO";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String codArticulo = miResultset.getString("CODARTICULO");
            String nombreArticulo = miResultset.getString("NOMBREART");
            String unidadMedida = miResultset.getString("UNIDADMEDIDA");
            
            Articulos temporal = new Articulos(codArticulo, nombreArticulo, unidadMedida);
            
            articulos.add(temporal);
        }
        
        return articulos;
    }
    
    public List<SolicitudArt> getSolicitudesArt() throws Exception{
        List<SolicitudArt> solicitudes = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        
        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        String miSql = "SELECT * FROM SOLICITUDART";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String carnetEmpleado = miResultset.getString("CARNETEMPLEADO");
            String codigoArticulo = miResultset.getString("CODARTICULO");
            int cantidadArticulo = miResultset.getInt("CANTARTSOL");
            Date fechaSol = miResultset.getDate("FECSOL");
            
            
            //Recupero el nombre del articulo segun el codigo de articulo que se esta recuperando
            String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codigoArticulo+"'";
            miStatementBusc = miConexion.createStatement();
            miResultsetBusc = miStatementBusc.executeQuery(buscar);
            String nombreArticulo = null;
            while(miResultsetBusc.next()){
                nombreArticulo = miResultsetBusc.getString("NOMBREART");
            }
            
            
            SolicitudArt temporal = new SolicitudArt(carnetEmpleado, codigoArticulo, cantidadArticulo, fechaSol, nombreArticulo);
            
            solicitudes.add(temporal);
        }
        
        return solicitudes;
    }
    
    public void agregarNuevaSolicitud(SolicitudArt nuevaSolicitud) {
        Connection miConexion = null;
        PreparedStatement miStatement = null;
        
        //Obtener la conexion
        try{
            
            miConexion = origenDatos.getConexion();
       
            //Crear sentencia sql que inserte
            String misql = "INSERT INTO SOLICITUDART(CARNETEMPLEADO, CODARTICULO, CANTARTSOL, FECSOL) VALUES (?, ?, ?, ?)";
            miStatement = miConexion.prepareStatement(misql);
            
            //Establecer los parametros para insertar el producto
            miStatement.setString(1,nuevaSolicitud.getCarnetEmpleado());
            
            miStatement.setString(2, nuevaSolicitud.getCodArticulo());
            
            miStatement.setInt(3, nuevaSolicitud.getCantArticulo());
            
            java.util.Date utilDate = nuevaSolicitud.getFechaSol();
            java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
            miStatement.setDate(4, fechaConvertida);
            
            //Ejecutar la instruccion sql
            miStatement.execute();
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
