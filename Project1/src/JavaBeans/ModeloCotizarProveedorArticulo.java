package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            int cantidad = vigencia.getCantidad();
            String departamento = vigencia.getCodDepartamento();
            
            //Le doy formato a la Fecha
            java.util.Date utilDate = vigencia.getFechaDesde();
            java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
            
            // SELECT * FROM VIGENCIA V INNER JOIN ARTICULO A ON V.CODARTICULO = A.CODARTICULO INNER JOIN PROVEEDOR P ON P.CODIGOPROV = V.CODIGOPROV WHERE V.CODARTICULO = 'BOR0001';
            
            //Crear sentencia SQL y Statement
            String miSql = "SELECT * FROM VIGENCIA V INNER JOIN ARTICULO A ON V.CODARTICULO = A.CODARTICULO INNER JOIN PROVEEDOR P ON P.CODIGOPROV = V.CODIGOPROV WHERE V.CODARTICULO = '" +articulo+ "' AND (FECHADESDE >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS') OR FECHAHASTA >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS'))";
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
                
                float precioSinDesc = (cantidad * precio);
                float precioTotal = (precioSinDesc) - (precioSinDesc * (descuento / 100));
                
                Vigencia temporal = new Vigencia(codArticulo, codProveedor, fechaDesde, fechaHasta, descuento, precio, tiempoEspera, periodoGracia, nombreArticulo, unidadMedida, nombreEmpresa, depto, municipio, telefono, correo, responsble, perGracia, cantidad, departamento, precioTotal);
                vigencias.add(temporal);
            }
            
        }catch (Exception e){
            
            e.printStackTrace();
            
        }
        
        return vigencias;
    }
    
    
    
    public List<Departamento> getVigenciasSinParametro(){
        List<Departamento> departamentos = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        
        try {        
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
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
   
        return departamentos;
    }
    


    public void agregarNuevaCompra(RequisicionVigenciaCompra nuevaCompra) {
        Connection miConexion = null;
        PreparedStatement miStatement = null;
        
        //Obtener la conexion
        try{
            
            miConexion = origenDatos.getConexion();
        
            //Crear sentencia sql que inserte
            String misql = "INSERT INTO REQUISICION_VIGENCIA_COMPRA(CODIGODEPARTAMENTO, CODARTICULO, CODIGOPROV, CANTART, PRECIOTOTAL) VALUES (?, ?, ?, ?, ?)";
            miStatement = miConexion.prepareStatement(misql);
            
            //Establecer los parametros para insertar el producto
            miStatement.setString(1,nuevaCompra.getCodigoDepartamento());
            
            miStatement.setString(2, nuevaCompra.getCodArticulo());
            
            miStatement.setString(3, nuevaCompra.getCodProveedor());
            
            miStatement.setInt(4, nuevaCompra.getCantArt());
            
            miStatement.setFloat(5, nuevaCompra.getPrecioTotal());
            
            //Ejecutar la instruccion sql
            miStatement.execute();
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
