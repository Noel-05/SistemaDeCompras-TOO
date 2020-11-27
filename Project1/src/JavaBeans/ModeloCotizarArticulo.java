package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.time.Year;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ModeloCotizarArticulo {
    private ConexionBaseDatos origenDatos;
    
    
    public ModeloCotizarArticulo(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
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
    

    public List<Requisicion> obtenerRequisiciones(Requisicion requisicion) {
        List<Requisicion> requisiciones = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        try{
            //Establecer la conexion
            miConexion = origenDatos.getConexion();
            
            //Recupero el carnet que voy a buscar
            java.util.Date utilDate = requisicion.getFechaPedidoReq();
            java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
            
            //TO_DATE(FECHA, 'YYYY/MM/DD HH:MI:SS')
            
            //Crear sentencia SQL y Statement
            String miSql = "SELECT * FROM REQUISICION WHERE FECPEDIDOREQ >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS')";
            miStatement = miConexion.createStatement();
            
            //Ejecutar SQL
            miResultset = miStatement.executeQuery(miSql);
            
            while(miResultset.next()){
                int numReq = miResultset.getInt("NUMREQ");
                Date fechaPedido = miResultset.getDate("FECPEDIDOREQ");
                Date fechaEntrega = miResultset.getDate("FECENTREGAREQ");
                int autorizado = miResultset.getInt("AUTORIZADO");
                int entregado = miResultset.getInt("ENTREGADO");
                
                Requisicion temporal = new Requisicion(fechaPedido, fechaEntrega, autorizado, entregado);
                
                requisiciones.add(temporal);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return  requisiciones;
    }

}
