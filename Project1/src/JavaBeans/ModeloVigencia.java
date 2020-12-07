package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.Date;
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

    public List<Vigencia> obtenerVigencias(Vigencia consultaVigencias) {
        //List<RequisicionVigenciaCompra> ordenesCompraProveedor = new ArrayList<>();
        List<Vigencia> vigencias = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        try{
            //Establecer la conexion
            miConexion = origenDatos.getConexion();
            
            //Recupero el departamento que voy a buscar
            String proveedorBusq = consultaVigencias.getCodProveedor();
            
            //Crear sentencia SQL y Statement
            String miSql = "select * from vigencia v inner join proveedor p on v.codigoprov = p.codigoprov inner join articulo a on v.codarticulo = a.codarticulo where v.codigoprov = '"+proveedorBusq+"'";
            miStatement = miConexion.createStatement();
            
            //Ejecutar SQL
            miResultset = miStatement.executeQuery(miSql);
            
            while(miResultset.next()){
                
                String codigoProv = miResultset.getString("CODIGOPROV");
                String nombreProv = miResultset.getString("NOMEMPRESA");
                String codArticulo = miResultset.getString("CODARTICULO");
                String nombreArt = miResultset.getString("NOMBREART");
                float descuento = miResultset.getFloat("DESCUENTO");
                float precio = miResultset.getFloat("PRECIO");
                int periodoGracia = miResultset.getInt("PERIODOGRACIA");
                int entregaInmediata = miResultset.getInt("TIEMPOENTREGA");
                Date fechaDesde = miResultset.getDate("FECHADESDE");
                Date fechaHasta = miResultset.getDate("FECHAHASTA");
                
                String perGracia = "";
                if(periodoGracia == 0){
                    perGracia = "SI";
                }else{
                    perGracia = "NO";
                }
                
                //RequisicionVigenciaCompra temporal = new RequisicionVigenciaCompra(departamentoBusq, codArticulo, codigoProv, cantidad, nombreProv, nombreArt, descuento, precio, periodoGracia, entregaInmediata, perGracia, precioTotal);
                Vigencia temporal = new Vigencia(codArticulo, codigoProv, fechaDesde, fechaHasta, descuento, precio, entregaInmediata, periodoGracia, nombreArt, nombreProv, perGracia);
                
                vigencias.add(temporal);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return vigencias;
    }

    public Vigencia getVigencia(String codArticulo, String codProveedor) {
        
        Vigencia temporal =  null;
        
        Connection miConexion = null;
        PreparedStatement miStatement = null;
        ResultSet miResultset = null;
        
        
        String cArticulo = codArticulo;
        String proveedor = codProveedor;
        
        try{

            //Establecer la conexión con la BD
            
            miConexion = origenDatos.getConexion();
            
            //Crear sql que busque el producto
            
            String miSql = "SELECT * FROM VIGENCIA V INNER JOIN PROVEEDOR P ON V.CODIGOPROV = P.CODIGOPROV INNER JOIN ARTICULO A ON V.CODARTICULO = A.CODARTICULO WHERE V.CODARTICULO = ? AND V.CODIGOPROV = ?";        
            
            //Crear la consultar preparada
            
            miStatement = miConexion.prepareStatement(miSql);
                
            //Ejecutar la consulta
            miStatement.setString(1, cArticulo);
            miStatement.setString(2, proveedor);
            miResultset = miStatement.executeQuery();
            
            //Obtener datos de respuesta
                
            if(miResultset.next()){
                
                String codigoArt = miResultset.getString("CODARTICULO");
                String codigoProv = miResultset.getString("CODIGOPROV");
                String nombreProv = miResultset.getString("NOMEMPRESA");
                String nombreArt = miResultset.getString("NOMBREART");
                float descuento = miResultset.getFloat("DESCUENTO");
                float precio = miResultset.getFloat("PRECIO");
                int periodoGracia = miResultset.getInt("PERIODOGRACIA");
                int entregaInmediata = miResultset.getInt("TIEMPOENTREGA");
                Date fechaDesde = miResultset.getDate("FECHADESDE");
                Date fechaHasta = miResultset.getDate("FECHAHASTA");
                
                //temporal = new Vigencia(codigoArt, codigoProv, fechaDesde, fechaHasta, descuento, precio, entregaInmediata, periodoGracia, nombreArt, nombreProv);
                temporal = new Vigencia(codigoArt, codigoProv, fechaDesde, fechaHasta, descuento, precio, entregaInmediata, periodoGracia, nombreProv, nombreArt);
            
            }else{
            
                throw new Exception("No encontramos la vigencia codigo = "+cArticulo+", +"+proveedor);
            
            }

        }catch(Exception e){
            
            e.printStackTrace();
        }
        
        return temporal;
    }

    public void actualizarVigencia(Vigencia VigenciaActualizada) throws Exception {
        
        Connection miConexion = null;
        PreparedStatement miStatement = null;
        
        miConexion = origenDatos.getConexion();
        //Crear Sentencia SQL
        
        String miSql = "UPDATE VIGENCIA SET FECHADESDE = ?, FECHAHASTA = ?, DESCUENTO = ?, PRECIO = ?, TIEMPOENTREGA = ?, PERIODOGRACIA = ?  WHERE CODARTICULO = ? AND CODIGOPROV = ?";
        
        //Crear la consulta preparada
            
        miStatement = miConexion.prepareStatement(miSql);
        
        //Establecer los parámetros
        
        java.util.Date utilDate = VigenciaActualizada.getFechaDesde();
        java.sql.Date fechaConvertidaDesde = new java.sql.Date(utilDate.getTime());
        miStatement.setDate(1, fechaConvertidaDesde);
        
        java.util.Date utilDate2 = VigenciaActualizada.getFechaHasta();
        java.sql.Date fechaConvertidaHasta = new java.sql.Date(utilDate2.getTime());
        miStatement.setDate(2, fechaConvertidaHasta);
        
        miStatement.setFloat(3,VigenciaActualizada.getDescuento());
        
        miStatement.setFloat(4,VigenciaActualizada.getPrecio());
        
        miStatement.setInt(5,VigenciaActualizada.getTiempoEspera());
        
        miStatement.setFloat(6,VigenciaActualizada.getPeriodoGracia());
        
        miStatement.setString(7,VigenciaActualizada.getCodArticulo());
        
        miStatement.setString(8,VigenciaActualizada.getCodProveedor());
        
        //Ejecutar la instruccion sql
        miStatement.execute();
        
    }

    public void borrarVigencia(String codArticulo) throws Exception{
        Connection miConexion = null;
        PreparedStatement miStatement = null;
        
        miConexion = origenDatos.getConexion();
        
        String miSql="DELETE FROM VIGENCIA WHERE CODARTICULO = ? ";
        
        miStatement = miConexion.prepareStatement(miSql);
        
        miStatement.setString(1, codArticulo);  
        
        miStatement.execute();
    }
}
