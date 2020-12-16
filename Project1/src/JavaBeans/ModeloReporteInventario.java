package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloReporteInventario {

    private ConexionBaseDatos origenDatos;

    public ModeloReporteInventario(ConexionBaseDatos origenDatos) {
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
        String miSql = "SELECT * FROM ARTICULO";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String codArt = miResultset.getString("CODARTICULO");
            String nomArt = miResultset.getString("NOMBREART");
            String uniM = miResultset.getString("UNIDADMEDIDA");
            
            Articulos temporal = new Articulos(codArt, nomArt, uniM);
            
            articulos.add(temporal);
        }
        
        return articulos;     
    }

    public List<Movimiento> getMovimientos(Movimiento mov) throws Exception{
		List<Movimiento> movimientos = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;

        Statement miStatementEmp = null;
        ResultSet miResultsetEmp = null;     

    
        //Establecer la conexion
        miConexion = origenDatos.getConexion();

        //Recupera el carnet y la fecha que se utilizara para el filtro
        java.util.Date utilDate = mov.getFecha();
        java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
        
        String articulo = mov.getArticulo();
        
        //Crear sentencia SQL y Statement
        // String miSql = "SELECT * FROM SOLICITUDART ORDER BY FECSOL";
        //String miSQl = "select * from solicitudart s inner join empleado e on s.carnetempleado=e.carnetempleado inner join catalagopuesto c on e.codigopuesto=c.codigopuesto inner join departamento d on c.codigodepartamento=d.codigodepartamento where d.codigodepartamento= '"+depto+"' and s.fecsol >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS') order by s.fecsol";
		String miSQl = "select * from inventario i inner join articulo a on i.codarticulo = a.codarticulo where a.codarticulo = '"+articulo+"'";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSQl);
        
        while(miResultset.next()){
            int idInv = miResultset.getInt("IDINVENTARIO");

            //Recuperar el nombre del articulo, y la unidad de medida segun el codigo de articulo que se esta recuperando TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS') 
            String buscar = "SELECT * FROM movimiento m where m.idinventario = '"+idInv+"' and m.fecmov >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS') order by m.fecmov ";
            miStatementBusc = miConexion.createStatement();
            miResultsetBusc = miStatementBusc.executeQuery(buscar);
            while(miResultsetBusc.next()){
                String carnet = miResultsetBusc.getString("CARNETEMPLEADO");
                String tipoMov = miResultsetBusc.getString("TIPO");
                int cantMov = miResultsetBusc.getInt("CANTMOV");
                Double valUnidad = miResultsetBusc.getDouble("VALUNIDAD");
                Double valTotal = miResultsetBusc.getDouble("VALTOTAL");
                Date fechaMov = miResultsetBusc.getDate("FECMOV");

	            Movimiento temporal = new Movimiento(carnet, tipoMov, cantMov, valUnidad, valTotal, fechaMov);
	            
	            movimientos.add(temporal);                
            }                      
            

        }


        return movimientos;    

    }

    public List<Movimiento> getAllMovimientos() throws Exception{

		List<Movimiento> movimientos = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        String miSql = "SELECT * FROM MOVIMIENTO m order by m.fecmov";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String carnet = miResultset.getString("CARNETEMPLEADO");
            String tipoMov = miResultset.getString("TIPO");
            int cantMov = miResultset.getInt("CANTMOV");
            Double valUnidad = miResultset.getDouble("VALUNIDAD");
            Double valTotal = miResultset.getDouble("VALTOTAL");
            Date fechaMov = miResultset.getDate("FECMOV");

            Movimiento temporal = new Movimiento(carnet, tipoMov, cantMov, valUnidad, valTotal, fechaMov);
            
            movimientos.add(temporal);
        }
        
        return movimientos;     

    }
}
