package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ModeloStockPrecios {

    private ConexionBaseDatos origenDatos;

    public ModeloStockPrecios(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }

    public List<CostoPromedio> getCostoPromedio() throws Exception{
	List<CostoPromedio> costoPromedio = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;

        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;  

        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        //String miSql = "SELECT * FROM INVENTARIO";
		String miSql = "select distinct c.idinv from costopromedio c";

        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            int idInvDistinto = miResultset.getInt("IDINV");

            //Recuperar el IdCostoPromedio mas alto segun el idInv
            //String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codArticulo+"'";
			String buscar = "select max(c.idcostoprom) from costopromedio c where c.idinv = '"+idInvDistinto+"'";
            miStatementBusc = miConexion.createStatement();
            miResultsetBusc = miStatementBusc.executeQuery(buscar);
            int maxIdCP = 0;
            while(miResultsetBusc.next()){
                maxIdCP = miResultsetBusc.getInt("max(c.idcostoprom)");

                //***********Recuperar valores del IdCostoPromedio Maximo
				Connection miConexion6 = null;
	            Statement miStatement6 = null;
	            ResultSet miResultset6 = null;                
	            //Establecer la conexion
	            miConexion6 = origenDatos.getConexion();                
	            //Recuperar el mayor valor del idCostoPromedio
	            String miSql6 = "select * from costopromedio c where c.idcostoprom = '"+maxIdCP+"'";
	            //String miSql4 = "select * from costopromedio where idcostoprom = '4'";                
	            miStatement6 = miConexion6.createStatement();                
	            //Ejecutar SQL
	            miResultset6 = miStatement6.executeQuery(miSql6); 
	            while(miResultset6.next()){
	                int idInv = miResultset6.getInt("IDINV");                        
	                double valTotal = miResultset6.getDouble("VALORTOTAL");
	                int cantidad = miResultset6.getInt("CANTIDAD");                        
	                double valUnidad = miResultset6.getDouble("VALORUNIDAD");

	                //***********Recuperar valores del IdCostoPromedio Maximo
					Connection miConexion3 = null;
		            Statement miStatement3 = null;
		            ResultSet miResultset3 = null;                
		            //Establecer la conexion
		            miConexion3 = origenDatos.getConexion();                
		            //Recuperar el mayor valor del idCostoPromedio
		            String miSql3 = "select * from articulo a inner join inventario i on a.codarticulo = i.codarticulo where i.idinventario = '"+idInv+"'";
		            //String miSql4 = "select * from costopromedio where idcostoprom = '4'";                
		            miStatement3 = miConexion3.createStatement();                
		            //Ejecutar SQL
		            miResultset3 = miStatement3.executeQuery(miSql3); 
		            while(miResultset3.next()){
		                String codArticulo = miResultset3.getString("CODARTICULO");                        
		                String nomArticulo = miResultset3.getString("NOMBREART");                        
		                String unidadArticulo = miResultset3.getString("UNIDADMEDIDA");

			            CostoPromedio temporal = new CostoPromedio(idInv, cantidad, valUnidad, valTotal, codArticulo, nomArticulo, unidadArticulo);
			            
			            costoPromedio.add(temporal);							                                        
		                }	                
	            }       

            }                 
            

        }
        
        return costoPromedio;          
    }
}
