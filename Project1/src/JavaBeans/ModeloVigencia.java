package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ModeloVigencia {
    
    private static ConexionBaseDatos origenDatos;

    public ModeloVigencia(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }

    public List<Articulos> getArticulos() throws Exception {
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

    public void agregarNuevaVigencia(Vigencia nuevaVigencia) {
        
        Connection miConexion = null;
        PreparedStatement miStatement = null;
        
        //Obtener la conexion
        try{
            
            miConexion = origenDatos.getConexion();
        
            //Crear sentencia sql que inserte
            String misql = "INSERT INTO VIGENCIA(CODARTICULO, CODIGOPROV, FECHADESDE, FECHAHASTA, DESCUENTO, PRECIO, TIEMPOENTREGA, PERIODOGRACIA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            miStatement = miConexion.prepareStatement(misql);
            
            //Establecer los parametros para insertar la vigencia
            miStatement.setString(1,nuevaVigencia.getCodArticulo());
            miStatement.setString(2,nuevaVigencia.getCodProveedor());
            
            java.util.Date utilDate = nuevaVigencia.getFechaDesde();
            java.sql.Date fechaConvertidaDesde = new java.sql.Date(utilDate.getTime());
            miStatement.setDate(3, fechaConvertidaDesde);
            
            java.util.Date utilDate2 = nuevaVigencia.getFechaHasta();
            java.sql.Date fechaConvertidaHasta = new java.sql.Date(utilDate2.getTime());
            miStatement.setDate(4, fechaConvertidaHasta);
            
            miStatement.setFloat(5,nuevaVigencia.getDescuento());
            miStatement.setFloat(6,nuevaVigencia.getPrecio());
            miStatement.setInt(7,nuevaVigencia.getTiempoEspera());
            miStatement.setFloat(8,nuevaVigencia.getPeriodoGracia());
            
            //Ejecutar la instruccion sql
            miStatement.execute();
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
