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

            //**************************CONSULTA IDMovimiento MAXIMO***************
            Connection miConexion6 = null;
            Statement miStatement6 = null;
            ResultSet miResultset6 = null;                
            //Establecer la conexion
            miConexion6 = origenDatos.getConexion();                
            //Recuperar el mayor valor del idCostoPromedio
            String miSql6 = "select max(m.idmov) from movimiento m ";
            //String miSql4 = "select * from costopromedio where idcostoprom = '4'";                
            miStatement6 = miConexion6.createStatement();                
            //Ejecutar SQL
            miResultset6 = miStatement6.executeQuery(miSql6); 
            int maximoIndiceMov = 0;               
            while(miResultset6.next()){
                maximoIndiceMov = miResultset6.getInt("max(m.idmov)");                        
            }         

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

                //**************************CONSULTAS COSTOPROMEDIO***************

                //**************************CONSULTA IDCP MAXIMO***************
                List<CostoPromedio> costoPromedio = new ArrayList<>();                
                Connection miConexion3 = null;
                Statement miStatement3 = null;
                ResultSet miResultset3 = null;                
                //Establecer la conexion
                miConexion3 = origenDatos.getConexion();                
                //Recuperar el mayor valor del idCostoPromedio
                String miSql3 = "select max(c.idcostoprom) from costopromedio c where c.idinv = '"+nuevoMov.getIdInv()+"'";
                //String miSql4 = "select * from costopromedio where idcostoprom = '4'";                
                miStatement3 = miConexion3.createStatement();                
                //Ejecutar SQL
                miResultset3 = miStatement3.executeQuery(miSql3); 
                int maximoIndice = 0;               
                while(miResultset3.next()){
                    maximoIndice = miResultset3.getInt("max(c.idcostoprom)");    

                }

                //**************************Recuperar valores del IDCP MAXIMO***************                
                Connection miConexion4 = null;
                Statement miStatement4 = null;
                ResultSet miResultset4 = null;                
                //Establecer la conexion
                miConexion4 = origenDatos.getConexion();                
                //Recuperar los valorres del idCostoPromedio mas alto
                String miSql4 = "select * from costopromedio where idcostoprom = '"+maximoIndice+"'";                
                miStatement4 = miConexion4.createStatement();                
                //Ejecutar SQL
                miResultset4 = miStatement4.executeQuery(miSql4); 
                double valorUnidadCP=0;               
                double valorTotalCP=0;
                int cantidadCP = 0;
                while(miResultset4.next()){
                    cantidadCP = miResultset4.getInt("CANTIDAD");
                    valorUnidadCP = miResultset4.getDouble("VALORUNIDAD");  
                    valorTotalCP = miResultset4.getDouble("VALORTOTAL");  

                    //**************************INSERTAR COSTO PROMEDIO**********************************
                    Connection miConexion5 = null;
                    PreparedStatement miStatement5 = null;
                    //Operaciones con Variables
                    double nuevoValorUnidadCP=0;
                    int nuevoCantidadCP=0;
                    double nuevoValorTotal=0;
                    double valorNuevaEntrada=0;

                    if("compra".equals(nuevoMov.getTipo())) {
                        valorNuevaEntrada = nuevoMov.getCantMov()*nuevoMov.getValUnidad();
                        nuevoCantidadCP = cantidadCP + nuevoMov.getCantMov();
                        nuevoValorUnidadCP = (valorTotalCP + valorNuevaEntrada)/(cantidadCP + nuevoMov.getCantMov());
                        nuevoValorTotal =  nuevoCantidadCP*nuevoValorUnidadCP;
                    }else{
                        nuevoCantidadCP = cantidadCP - nuevoMov.getCantMov();
                        //nuevoValorUnidadCP = valorUnidadCP;
                        nuevoValorTotal =  nuevoCantidadCP*valorUnidadCP;
                    }                
                    //Obtener la conexion            
                    miConexion5 = origenDatos.getConexion();
               
                    //Crear sentencia sql que inserte
                    String misql5 = "INSERT INTO COSTOPROMEDIO (IDMOV, CANTIDAD, VALORUNIDAD, VALORTOTAL, IDINV) VALUES (?, ?, ?, ?, ?)";
                    
                    miStatement5 = miConexion5.prepareStatement(misql5);
                    
                    //Establecer los parametros para insertar el producto
                    miStatement5.setInt(1, maximoIndiceMov);
                    
                    miStatement5.setInt(2, nuevoCantidadCP);//ya

                    if("compra".equals(nuevoMov.getTipo())) {
                        miStatement5.setDouble(3, nuevoValorUnidadCP);//ya    
                    }else{
                        miStatement5.setDouble(3, valorUnidadCP);//ya    
                    }                     

                    miStatement5.setDouble(4, nuevoValorTotal);//ya

                    miStatement5.setInt(5, nuevoMov.getIdInv());//ya 
                    
                    //Ejecutar la instruccion sql
                    miStatement5.execute();

                }                    

               
             
	        }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
