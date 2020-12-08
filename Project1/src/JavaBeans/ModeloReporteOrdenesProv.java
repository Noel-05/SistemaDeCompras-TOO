package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ModeloReporteOrdenesProv {

    private ConexionBaseDatos origenDatos;

    public ModeloReporteOrdenesProv(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }

    public List<Ordenes> getOrdenes(Ordenes ordenRec) throws Exception{
	List<Ordenes> ordenes = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;
    
        //Establecer la conexion
        miConexion = origenDatos.getConexion();

        //Recupera el la fecha que se utilizara para el filtro
        java.util.Date utilDate = ordenRec.getFecha();
        java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
                
        //Crear sentencia SQL y Statement
        //String miSQl = "select * from solicitudart s inner join empleado e on s.carnetempleado=e.carnetempleado inner join catalagopuesto c on e.codigopuesto=c.codigopuesto inner join departamento d on c.codigodepartamento=d.codigodepartamento where d.codigodepartamento= '"+depto+"' and s.fecsol >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS') order by s.fecsol";
        String miSQl = "SELECT r.codigoprov, COUNT(r.codigoprov), SUM(r.preciototal) from requisicion_vigencia_compra r WHERE r.fechapedidoreq >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS') group by r.codigoprov";
        
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSQl);
        
        while(miResultset.next()){
            String codigoProv = miResultset.getString("CODIGOPROV");
            int sumaOrdenes = miResultset.getInt("COUNT(R.CODIGOPROV)");
            double montoTotal = miResultset.getDouble("SUM(R.PRECIOTOTAL)");

            // //Recuperar el nombre del articulo, y la unidad de medida segun el codigo de articulo que se esta recuperando
            // String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codigoArticulo+"'";
            // miStatementBusc = miConexion.createStatement();
            // miResultsetBusc = miStatementBusc.executeQuery(buscar);
            // String nombreArticulo = null;
            // String unidadMedida = null;
            // while(miResultsetBusc.next()){
            //     nombreArticulo = miResultsetBusc.getString("NOMBREART");
            //     unidadMedida = miResultsetBusc.getString("UNIDADMEDIDA");
            // }                      
            
            Ordenes temporal = new Ordenes(codigoProv, montoTotal, sumaOrdenes);
            
            ordenes.add(temporal);
        }


        return ordenes; 
    }
}
