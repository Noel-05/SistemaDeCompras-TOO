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
        String miSql = "SELECT * FROM ARTICULO ORDER BY CODARTICULO";
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
        String miSql = "SELECT * FROM SOLICITUDART ORDER BY FECSOL DESC";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String carnetEmpleado = miResultset.getString("CARNETEMPLEADO");
            String codigoArticulo = miResultset.getString("CODARTICULO");
            int cantidadArticulo = miResultset.getInt("CANTARTSOL");
            Date fechaSol = miResultset.getDate("FECSOL");

            int idSol = miResultset.getInt("IDSOL");
            
            
            //Recupero el nombre del articulo segun el codigo de articulo que se esta recuperando
            String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codigoArticulo+"'";
            miStatementBusc = miConexion.createStatement();
            miResultsetBusc = miStatementBusc.executeQuery(buscar);
            String nombreArticulo = null;
            while(miResultsetBusc.next()){
                nombreArticulo = miResultsetBusc.getString("NOMBREART");
            }
            
            
            SolicitudArt temporal = new SolicitudArt(carnetEmpleado, codigoArticulo, cantidadArticulo, fechaSol, nombreArticulo, idSol);
            
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
    
    
    public List<Empleado> getEmpleados() throws Exception{
        List<Empleado> empleados = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        String miSql = "SELECT * FROM EMPLEADO ORDER BY CARNETEMPLEADO";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String carnetEmpleado = miResultset.getString("CARNETEMPLEADO");
            String codigoPuesto = miResultset.getString("CODIGOPUESTO");
            String nombreEmpleado = miResultset.getString("NOMBREEMPLEADO");
            String apellidoEmpleado = miResultset.getString("APELLIDOEMPLEADO");
            String generoEmpleado = miResultset.getString("GENERO");
            String deptoEmpleado = miResultset.getString("DEPTO");
            String municipioEmpleado = miResultset.getString("MUNICIPIO");
            String estadoCivilEmpleado = miResultset.getString("ESTADOCIVIL");
            
            Empleado temporal = new Empleado(carnetEmpleado, codigoPuesto, nombreEmpleado, apellidoEmpleado, generoEmpleado, deptoEmpleado, municipioEmpleado, estadoCivilEmpleado);
            
            empleados.add(temporal);
        }
        
        return empleados;
    }


    public List<SolicitudArt> obtenerSolicitudEmpleado(Empleado consultaEmpleado) throws Exception{
        List<SolicitudArt> solicitudesEmpleado = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;
        
        try{
            //Establecer la conexion
            miConexion = origenDatos.getConexion();
            
            //Recupero el carnet que voy a buscar
            String carnetBusqueda = consultaEmpleado.getCarnetEmpleado();
            
            //Crear sentencia SQL y Statement
            String miSql = "SELECT * FROM SOLICITUDART WHERE CARNETEMPLEADO='"+carnetBusqueda+"'";
            miStatement = miConexion.createStatement();
            
            //Ejecutar SQL
            miResultset = miStatement.executeQuery(miSql);
            
            while(miResultset.next()){
                String carnetEmpleado = miResultset.getString("CARNETEMPLEADO");
                String codigoArticulo = miResultset.getString("CODARTICULO");
                int cantidadArticulo = miResultset.getInt("CANTARTSOL");
                Date fechaSol = miResultset.getDate("FECSOL");
                int idSol = miResultset.getInt("IDSOL");
                
                //Recupero el nombre del articulo segun el codigo de articulo que se esta recuperando
                String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codigoArticulo+"'";
                miStatementBusc = miConexion.createStatement();
                miResultsetBusc = miStatementBusc.executeQuery(buscar);
                String nombreArticulo = null;
                while(miResultsetBusc.next()){
                    nombreArticulo = miResultsetBusc.getString("NOMBREART");
                }
                
                SolicitudArt temporal = new SolicitudArt(carnetEmpleado, codigoArticulo, cantidadArticulo, fechaSol, nombreArticulo, idSol);
                
                solicitudesEmpleado.add(temporal);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return  solicitudesEmpleado;
    }


    public SolicitudArt obtenerSolicitud(int idSol) {
        SolicitudArt temporal =  null;
        
        Connection miConexion = null;
        PreparedStatement miStatement = null;
        ResultSet miResultset = null;
        
        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;
        
        int idSolicitud = idSol;
        
        try{
            
            // Establecer la conexion
            miConexion = origenDatos.getConexion();
            
            // Crear y ejecutar la consulta
            String miSql = "SELECT * FROM SOLICITUDART WHERE IDSOL = ? ";
            
            miStatement = miConexion.prepareStatement(miSql);
            
            miStatement.setInt(1, idSolicitud);
            
            miResultset = miStatement.executeQuery();
            
            // Recuperar los datos   
            if(miResultset.next()){
                
                String carnetEmpleado = miResultset.getString("CARNETEMPLEADO");
                String codigoArticulo = miResultset.getString("CODARTICULO");
                int cantidadArticulo = miResultset.getInt("CANTARTSOL");
                Date fechaSol = miResultset.getDate("FECSOL");
                int idSolici = miResultset.getInt("IDSOL");
                
                
                //Recupero el nombre del articulo segun el codigo de articulo que se esta recuperando
                String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codigoArticulo+"'";
                miStatementBusc = miConexion.createStatement();
                miResultsetBusc = miStatementBusc.executeQuery(buscar);
                String nombreArticulo = null;
                while(miResultsetBusc.next()){
                    nombreArticulo = miResultsetBusc.getString("NOMBREART");
                }
                
                temporal = new SolicitudArt(carnetEmpleado, codigoArticulo, cantidadArticulo, fechaSol, nombreArticulo, idSolici);
                
                //temporal = new SolicitudArt(carnetEmpleado, codigoArticulo, cantidadArticulo, fechaSol, idSolici);
                
            }else{
                throw new Exception("No encontramos la solicitud codigo = "+idSolicitud);
            }
            
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
        
        return temporal;
        
    }

    public void actualizarSolicitud(SolicitudArt solicitudActualizada) throws Exception{
        Connection miConexion = null;
        PreparedStatement miStatement = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear setencia sql
        String miSql = "UPDATE SOLICITUDART SET CARNETEMPLEADO = ?, CODARTICULO = ?, CANTARTSOL = ?, FECSOL = ? WHERE IDSOL = ?";
        
        miStatement = miConexion.prepareStatement(miSql);
        
        miStatement.setString(1, solicitudActualizada.getCarnetEmpleado());
        
        miStatement.setString(2, solicitudActualizada.getCodArticulo());
        
        miStatement.setInt(3, solicitudActualizada.getCantArticulo());
        
        java.util.Date utilDate = solicitudActualizada.getFechaSol();
        java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
        miStatement.setDate(4, fechaConvertida);
        
        miStatement.setInt(5, solicitudActualizada.getIdSol());   
        
        // Ejecutar la instruccion sql
        miStatement.execute();
    }


    public void borrarSolicitud(int idSoli)throws Exception{
        Connection miConexion = null;
        PreparedStatement miStatement = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia sql
        String miSql = "DELETE FROM SOLICITUDART WHERE IDSOL = ?";
        
        miStatement = miConexion.prepareStatement(miSql);
        
        miStatement.setInt(1, idSoli);   
        
        // Ejecutar la instruccion sql
        miStatement.execute();
    }
}
