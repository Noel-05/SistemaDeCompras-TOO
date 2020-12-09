package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloMovimiento {

    private ConexionBaseDatos origenDatos;

    public ModeloMovimiento(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }

    public List<Inventario> getInventario() throws Exception{
		List<Inventario> inventarios = new ArrayList<>();
        
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
            String idInv = miResultset.getString("IDINVENTARIO");
            String codArticulo = miResultset.getString("CODARTICULO");


            //Recuperar el nombre del articulo, y la unidad de medida segun el codigo de articulo que se esta recuperando
            String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codArticulo+"'";
            miStatementBusc = miConexion.createStatement();
            miResultsetBusc = miStatementBusc.executeQuery(buscar);
            String nombreArticulo = null;
            while(miResultsetBusc.next()){
                nombreArticulo = miResultsetBusc.getString("NOMBREART");
            } 
            
            Inventario temporal = new Inventario(idInv, codArticulo, nombreArticulo);
            
            inventarios.add(temporal);
        }
        
        return inventarios;      
    }

    public void agregarNuevoMovimiento(Movimiento nuevoMov) {
        Connection miConexion = null;
        PreparedStatement miStatement = null;

        //ActualizarInventario
        Connection miConexion1 = null;
        PreparedStatement miStatement1 = null; 

        //ActualizarInventario
        Connection miConexion2 = null;
        Statement miStatement2 = null; 
        ResultSet miResultset2 = null;

        //Obtener la conexion
        try{
        	//**************************INSERTAR**********************************
	        //Operaciones con Variables
	        Double valTotal = (nuevoMov.getCantMov()) * (nuevoMov.getValUnidad());
	        //Obtener la conexion            
            miConexion = origenDatos.getConexion();
       
            //Crear sentencia sql que inserte
            String misql = "INSERT INTO MOVIMIENTO(IDINVENTARIO, CARNETEMPLEADO, TIPO, CANTMOV, VALUNIDAD, VALTOTAL, FECMOV) VALUES (?, ?, ?, ?, ?, ?, ?)";
            miStatement = miConexion.prepareStatement(misql);
            


            //Establecer los parametros para insertar el producto
            miStatement.setInt(1,nuevoMov.getIdInv());
            
            miStatement.setString(2, nuevoMov.getCarnetEmpleado());
            
            miStatement.setString(3, nuevoMov.getTipo());

            miStatement.setInt(4, nuevoMov.getCantMov());

            miStatement.setDouble(5, nuevoMov.getValUnidad());

            miStatement.setDouble(6, valTotal);
            
            java.util.Date utilDate = nuevoMov.getFecMov();
            java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
            miStatement.setDate(7, fechaConvertida);
		    
            //Ejecutar la instruccion sql
            miStatement.execute();

        	//**************************RECUPERARSTOCK**********************************
			//Establecer la conexion
	        miConexion2 = origenDatos.getConexion();
	        
	        //Crear sentencia SQL y Statement
	        String misql1 = "SELECT * FROM INVENTARIO WHERE IDINVENTARIO = '"+nuevoMov.getIdInv()+"'";
	        miStatement2 = miConexion2.createStatement();
	        
	        //Ejecutar SQL
	        miResultset2 = miStatement2.executeQuery(misql1);
	        
	        while(miResultset2.next()){
                int stock = miResultset2.getInt("STOCK");


                int nuevoValor=0;
                if("compra".equals(nuevoMov.getTipo())) {
				    nuevoValor = stock + nuevoMov.getCantMov();
				}else{
					nuevoValor = stock - nuevoMov.getCantMov();
				}

	        	//**************************ACTUALIZAR INVENTARIO**********************************
	            miConexion1 = origenDatos.getConexion();//Inv

				//Crear setencia sql
		        String miSQL = "update inventario SET stock = ? WHERE idinventario = ?";
		        miStatement1 = miConexion1.prepareStatement(miSQL);
		        
		        miStatement1.setInt(1, nuevoValor);
		        
		        miStatement1.setInt(2, nuevoMov.getIdInv());
		        
		        // Ejecutar la instruccion sql
		        miStatement1.execute();
	            
	            
	        }




        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
