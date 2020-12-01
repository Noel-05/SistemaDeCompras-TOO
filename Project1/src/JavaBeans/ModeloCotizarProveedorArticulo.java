package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloCotizarProveedorArticulo {
    private ConexionBaseDatos origenDatos;
    
    
    public ModeloCotizarProveedorArticulo(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }


    public List<Vigencia> getVigencias(Vigencia vigencia){
        List<Vigencia> vigencias = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        try{
            
            String articulo = vigencia.getCodArticulo();
            
            // SELECT * FROM VIGENCIA V INNER JOIN ARTICULO A ON V.CODARTICULO = A.CODARTICULO INNER JOIN PROVEEDOR P ON P.CODIGOPROV = V.CODIGOPROV WHERE V.CODARTICULO = 'BOR0001';
            
            //Crear sentencia SQL y Statement
            String miSql = "SELECT * FROM VIGENCIA V INNER JOIN ARTICULO A ON V.CODARTICULO = A.CODARTICULO INNER JOIN PROVEEDOR P ON P.CODIGOPROV = V.CODIGOPROV WHERE V.CODARTICULO = '" +articulo+ "'";
            miStatement = miConexion.createStatement();
            
            //Ejecutar SQL
            miResultset = miStatement.executeQuery(miSql);
            
            while(miResultset.next()){
                
                String codArticulo = miResultset.getString("CODARTICULO");
                String codProveedor = miResultset.getNString("CODIGOPROV");
                Date fechaDesde = miResultset.getDate("FECHADESDE");
                Date fechaHasta = miResultset.getDate("FECHAHASTA");
                float descuento = miResultset.getFloat("DESCUENTO");
                float precio = miResultset.getFloat("PRECIO");
                int tiempoEspera = miResultset.getInt("TIEMPOENTREGA");
                int periodoGracia = miResultset.getInt("PERIODOGRACIA");
                
                String nombreArticulo = miResultset.getString("NOMBREART");
                String unidadMedida = miResultset.getString("UNIDADMEDIDA");
                
                String nombreEmpresa = miResultset.getString("NOMEMPRESA");
                String depto = miResultset.getString("DEPTO");
                String municipio = miResultset.getString("MUNICIPIO");
                String telefono = miResultset.getString("TEL");
                String correo = miResultset.getString("CORREO"); 
                String responsble = miResultset.getString("RESPONSABLE");
                //String carnetEmpleado = miResultset.getString("CARNETEMPLEADO");
                
                String perGracia = "";
                if(periodoGracia == 0){
                    perGracia = "SI";
                }else{
                    perGracia = "NO";
                }
                
                Vigencia temporal = new Vigencia(codArticulo, codProveedor, fechaDesde, fechaHasta, descuento, precio, tiempoEspera, periodoGracia, nombreArticulo, unidadMedida, nombreEmpresa, depto, municipio, telefono, correo, responsble, perGracia);
                vigencias.add(temporal);
            }
            
        }catch (Exception e){
            
            e.printStackTrace();
            
        }
        
        return vigencias;
    }
   
}
