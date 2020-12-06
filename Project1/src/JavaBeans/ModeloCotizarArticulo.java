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
        
        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;
        
        Statement miStatementBuscDepto = null;
        ResultSet miResultsetBuscDepto = null;
        
        try{
            //Establecer la conexion
            miConexion = origenDatos.getConexion();
            
            //Recupero el carnet que voy a buscar
            java.util.Date utilDate = requisicion.getFechaPedidoReq();
            java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
            
            String departamento = requisicion.getCodigoDepartamento();
            
            //TO_DATE(FECHA, 'YYYY/MM/DD HH:MI:SS')
            
            // SELECT * FROM REQUISICION R INNER JOIN EMPLEADO E ON R.CARNETEMPLEADO = E.CARNETEMPLEADO INNER JOIN CATALAGOPUESTO C ON E.CODIGOPUESTO = C.CODIGOPUESTO WHERE CODIGODEPARTAMENTO = 'INF001' AND FECPEDIDOREQ >= '21/11/20';
            
            //Crear sentencia SQL y Statement
            String miSql = "SELECT * FROM REQUISICION R INNER JOIN EMPLEADO E ON R.CARNETEMPLEADO = E.CARNETEMPLEADO INNER JOIN CATALAGOPUESTO C ON E.CODIGOPUESTO = C.CODIGOPUESTO WHERE CODIGODEPARTAMENTO = '"+departamento+"' AND FECPEDIDOREQ >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS')";
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
                
                
                //Recupero el nombre del articulo segun el codigo de articulo que se esta recuperando
                String buscarArticulo = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codArticulo+"'";
                miStatementBusc = miConexion.createStatement();
                miResultsetBusc = miStatementBusc.executeQuery(buscarArticulo);
                String nombreArticulo = null;
                while(miResultsetBusc.next()){
                    nombreArticulo = miResultsetBusc.getString("NOMBREART");
                }
                
                
                // SELECT * FROM EMPLEADO E INNER JOIN CATALAGOPUESTO C ON E.CODIGOPUESTO = C.CODIGOPUESTO INNER JOIN DEPARTAMENTO D ON D.CODIGODEPARTAMENTO = C.CODIGODEPARTAMENTO;
                
                //Recupero el nombre Departamento al que pertenece el empleado
                String buscarDepto = "SELECT * FROM EMPLEADO E INNER JOIN CATALAGOPUESTO C ON E.CODIGOPUESTO = C.CODIGOPUESTO INNER JOIN DEPARTAMENTO D ON D.CODIGODEPARTAMENTO = C.CODIGODEPARTAMENTO WHERE E.CARNETEMPLEADO='"+carnetEmpleado+"'";
                miStatementBuscDepto = miConexion.createStatement();
                miResultsetBuscDepto = miStatementBuscDepto.executeQuery(buscarDepto);
                String codigoDepartamento = null;
                String nombreDepartamento = null;
                String codigoPuesto = null;
                while(miResultsetBuscDepto.next()){
                    codigoDepartamento = miResultsetBuscDepto.getString("CODIGODEPARTAMENTO");
                    nombreDepartamento = miResultsetBuscDepto.getString("NOMBREDEPARTAMENTO");
                    codigoPuesto = miResultsetBuscDepto.getString("CODIGOPUESTO");
                }
                
                
                /*if( (codigoPuesto.equals("GERINF")) || (codigoPuesto.equals("GERCOMP")) || (codigoPuesto.equals("GERFIN")) ){
                    Requisicion temporal = new Requisicion(fechaPedido, fechaEntrega, autorizado, entregado, cantidad, codArticulo, carnetEmpleado, nombreArticulo, codigoDepartamento, nombreDepartamento);
                    requisiciones.add(temporal);
                }else{
                }*/
                
                if(autorizado == 1){
                    Requisicion temporal = new Requisicion(fechaPedido, fechaEntrega, autorizado, entregado, cantidad, codArticulo, carnetEmpleado, nombreArticulo, codigoDepartamento, nombreDepartamento);
                    requisiciones.add(temporal);
                }
                
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return  requisiciones;
    }
    

    public List<RequisicionVigenciaCompra> obtenerOrdenes(RequisicionVigenciaCompra consultaOrdenes) {
        List<RequisicionVigenciaCompra> ordenesCompraProveedor = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        try{
            //Establecer la conexion
            miConexion = origenDatos.getConexion();
            
            //Recupero el departamento que voy a buscar
            String departamentoBusq = consultaOrdenes.getCodigoDepartamento();
            
            // SELECT * FROM REQUISICION_VIGENCIA_COMPRA RVC INNER JOIN VIGENCIA V ON RVC.CODARTICULO = V.CODARTICULO AND RVC.CODIGOPROV = V.CODIGOPROV INNER JOIN PROVEEDOR P ON P.CODIGOPROV = RVC.CODIGOPROV INNER JOIN ARTICULO A ON A.CODARTICULO = RVC.CODARTICULO WHERE CODIGODEPARTAMENTO = 'INF001'
            
            //Crear sentencia SQL y Statement
            String miSql = "SELECT * FROM REQUISICION_VIGENCIA_COMPRA RVC INNER JOIN VIGENCIA V ON RVC.CODARTICULO = V.CODARTICULO AND RVC.CODIGOPROV = V.CODIGOPROV INNER JOIN PROVEEDOR P ON P.CODIGOPROV = RVC.CODIGOPROV INNER JOIN ARTICULO A ON A.CODARTICULO = RVC.CODARTICULO WHERE CODIGODEPARTAMENTO = '"+departamentoBusq+"'";
            miStatement = miConexion.createStatement();
            
            //Ejecutar SQL
            miResultset = miStatement.executeQuery(miSql);
            
            while(miResultset.next()){
                
                String codigoProv = miResultset.getString("CODIGOPROV");
                String nombreProv = miResultset.getString("NOMEMPRESA");
                String codArticulo = miResultset.getString("CODARTICULO");
                String nombreArt = miResultset.getString("NOMBREART");
                int cantidad = miResultset.getInt("CANTART");
                float descuento = miResultset.getFloat("DESCUENTO");
                float precio = miResultset.getFloat("PRECIO");
                int periodoGracia = miResultset.getInt("PERIODOGRACIA");
                int entregaInmediata = miResultset.getInt("TIEMPOENTREGA");
                float precioTotal = miResultset.getFloat("PRECIOTOTAL");
                
                String perGracia = "";
                if(periodoGracia == 0){
                    perGracia = "SI";
                }else{
                    perGracia = "NO";
                }
                
                RequisicionVigenciaCompra temporal = new RequisicionVigenciaCompra(departamentoBusq, codArticulo, codigoProv, cantidad, nombreProv, nombreArt, descuento, precio, periodoGracia, entregaInmediata, perGracia, precioTotal);
                
                ordenesCompraProveedor.add(temporal);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return ordenesCompraProveedor;
    }

}
