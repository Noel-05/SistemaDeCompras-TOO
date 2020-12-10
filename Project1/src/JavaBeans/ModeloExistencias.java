package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ModeloExistencias {

    private ConexionBaseDatos origenDatos;

    public ModeloExistencias(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }

    public List<Inventario> getInventario() throws Exception{
		List<Inventario> inventario = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;

        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;  

        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        String miSql = "SELECT * FROM INVENTARIO";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String codArticulo = miResultset.getString("CODARTICULO");
            int min = miResultset.getInt("MINART");
            int max = miResultset.getInt("MAXART");
            int stock = miResultset.getInt("STOCK");

            //Enviar Mensaje
            String mensaje = "";
            if(stock < min){
                mensaje = "DEBE REQUERIR MAS ARTICULOS";}
            if(stock >= min && stock <= max){
                mensaje = "ESTA DENTRO DE LOS PARAMETROS";}
            if(stock > max){
                mensaje = "EXCESO DE PRODUCTOS";}

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
            
            Inventario temporal = new Inventario(codArticulo, min, max, stock, nombreArticulo, unidadMedida, mensaje);
            
            inventario.add(temporal);
        }
        
        return inventario;      
    }
}
