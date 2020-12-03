package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloRequisicion {

    private ConexionBaseDatos origenDatos;


    public ModeloRequisicion(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }

//Requisicion
public List<Requisicion> getRequisicion() throws Exception{
        List<Requisicion> requisicion = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;

        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;

        Statement miStatementBuscDepto = null;
        ResultSet miResultsetBuscDepto = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        String miSql = "SELECT * FROM REQUISICION";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            int numReq = miResultset.getInt("NUMREQ");
            Date fechaPedido = miResultset.getDate("FECPEDIDOREQ");
            Date fechaEntrega = miResultset.getDate("FECENTREGAREQ");
            int autorizado = miResultset.getInt("AUTORIZADO");
            int entregado = miResultset.getInt("ENTREGADO");
            int cantidad = miResultset.getInt("CANTART");
            String codArticulo = miResultset.getString("CODARTICULO");
            String carnetEmpleado = miResultset.getString("CARNETEMPLEADO");

            //Recuperar el nombre del articulo, y la unidad de medida segun el codigo de articulo que se esta recuperando
            String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codArticulo+"'";
            miStatementBusc = miConexion.createStatement();
            miResultsetBusc = miStatementBusc.executeQuery(buscar);
            String nombreArticulo = null;
            String unidadMedida = null;
            while(miResultsetBusc.next()){
                nombreArticulo = miResultsetBusc.getString("NOMBREART");
                unidadMedida = miResultsetBusc.getString("UNIDADMEDIDA");
            }

            //Recupero el nombre Departamento al que pertenece el empleado
            String buscarDepto = "select * from empleado inner join catalagopuesto c on empleado.codigopuesto =c.codigopuesto inner join departamento d on c.codigodepartamento = d.codigodepartamento where carnetempleado = '"+carnetEmpleado+"'";
            miStatementBuscDepto = miConexion.createStatement();
            miResultsetBuscDepto = miStatementBuscDepto.executeQuery(buscarDepto);
            String codDepto = null;            
            String nombreDepartamento = null;
            while(miResultsetBuscDepto.next()){
                codDepto = miResultsetBuscDepto.getString("CODIGODEPARTAMENTO");                
                nombreDepartamento = miResultsetBuscDepto.getString("NOMBREDEPARTAMENTO");
            }
       
            Requisicion temporal = new Requisicion(fechaPedido, fechaEntrega, autorizado, entregado, cantidad, codArticulo, carnetEmpleado, nombreArticulo, unidadMedida, codDepto ,nombreDepartamento);
            requisicion.add(temporal);
        }
        
        return requisicion;
    }

public List<Departamento> getDepartamentos()  throws Exception{
        List<Departamento> departamentos = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        String miSql = "SELECT * FROM DEPARTAMENTO";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String codDepto = miResultset.getString("CODIGODEPARTAMENTO");
            String nombreDepto = miResultset.getString("NOMBREDEPARTAMENTO");
            String descDepto = miResultset.getString("DESCRIPCIONDEPARTAMENTO");
            
            Departamento temporal = new Departamento(codDepto, nombreDepto, descDepto);
            
            departamentos.add(temporal);
        }
        
        return departamentos;
    }

    public List<Requisicion> obtenerRequisicionDepto(Departamento consultaDepartamento) throws Exception{
        List<Requisicion> requisicionesDepto = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;

        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;

        Statement miStatementBuscDepto = null;
        ResultSet miResultsetBuscDepto = null;
        
        try{
            //Establecer la conexion
            miConexion = origenDatos.getConexion();
            
            //Recupero el departanmento que voy a buscar
            String depbusqueda = consultaDepartamento.getCodigoDepartamento();
            
            //Crear sentencia SQL y Statement
            String miSql = "select * from requisicion r inner join empleado e on e.carnetempleado = r.carnetempleado inner join catalagopuesto c on e.codigopuesto =c.codigopuesto inner join departamento d on c.codigodepartamento = d.codigodepartamento where d.codigodepartamento = '"+depbusqueda+"'";

            miStatement = miConexion.createStatement();
            
            //Ejecutar SQL
            miResultset = miStatement.executeQuery(miSql);
            
            while(miResultset.next()){
                int numReq = miResultset.getInt("NUMREQ");
                Date fechaPedido = miResultset.getDate("FECPEDIDOREQ");
                Date fechaEntrega = miResultset.getDate("FECENTREGAREQ");
                int autorizado = miResultset.getInt("AUTORIZADO");
                int entregado = miResultset.getInt("ENTREGADO");
                int cantidad = miResultset.getInt("CANTART");
                String codArticulo = miResultset.getString("CODARTICULO");
                String carnetEmpleado = miResultset.getString("CARNETEMPLEADO");

                //Recuperar el nombre del articulo, y la unidad de medida segun el codigo de articulo que se esta recuperando
                String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codArticulo+"'";
                miStatementBusc = miConexion.createStatement();
                miResultsetBusc = miStatementBusc.executeQuery(buscar);
                String nombreArticulo = null;
                String unidadMedida = null;
                while(miResultsetBusc.next()){
                    nombreArticulo = miResultsetBusc.getString("NOMBREART");
                    unidadMedida = miResultsetBusc.getString("UNIDADMEDIDA");
                }

                //Recupero el nombre Departamento al que pertenece el empleado
                String buscarDepto = "select * from empleado inner join catalagopuesto c on empleado.codigopuesto =c.codigopuesto inner join departamento d on c.codigodepartamento = d.codigodepartamento where carnetempleado = '"+carnetEmpleado+"'";
                miStatementBuscDepto = miConexion.createStatement();
                miResultsetBuscDepto = miStatementBuscDepto.executeQuery(buscarDepto);
                String codDepto = null;            
                String nombreDepartamento = null;
                while(miResultsetBuscDepto.next()){
                    codDepto = miResultsetBuscDepto.getString("CODIGODEPARTAMENTO");                
                    nombreDepartamento = miResultsetBuscDepto.getString("NOMBREDEPARTAMENTO");
                }
                
                Requisicion temporal = new Requisicion(fechaPedido, fechaEntrega, autorizado, entregado, cantidad, codArticulo, carnetEmpleado, nombreArticulo, unidadMedida, codDepto ,nombreDepartamento);
                requisicionesDepto.add(temporal);

            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return  requisicionesDepto;
    }

}