package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.OracleConnection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class ConexionBaseDatos {  
    
  final static String DB_URL="jdbc:oracle:thin:@dbsiscompras_high?TNS_ADMIN=/Users/noelr/Desktop/WalletPrueba";
  final static String DB_USER = "ADMIN";
  final static String DB_PASSWORD = "Proyecto_TOO115";
  
    public static Connection getConexion(){
            Connection con=null;
            try{
                
                String driver = "oracle.jdbc.driver.OracleDriver";
                
                Class.forName(driver);
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 
                System.out.println("CONEXION EXITOSA");
                
            }catch (Exception e)
            {
                //e.printStackTrace();
                System.out.println("NO SE PUDO CONECTAR CON LA BASE DE DATOS, ERROR: " + e);
            }      
            
            return con;    
        }
        
        
        public static void main(String args[]) {            
            
            ConexionBaseDatos.getConexion();
            
        }
}



  /*public static void main(String args[]) throws SQLException {
    Properties info = new Properties();     
    info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
    info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);          
    info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");    
  
    OracleDataSource ods = new OracleDataSource();
    ods.setURL(DB_URL);    
    ods.setConnectionProperties(info);

    try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
        
      DatabaseMetaData dbmd = connection.getMetaData();       
      System.out.println("Driver Name: " + dbmd.getDriverName());
      System.out.println("Driver Version: " + dbmd.getDriverVersion());
      System.out.println("Default Row Prefetch Value is: " + connection.getDefaultRowPrefetch());
      System.out.println("Database Username is: " + connection.getUserName());
      System.out.println("CONEXION EXITOSA!!!!!!");
      System.out.println();
        
      printArticulos(connection);
        
    }catch(Exception e){
        
        System.out.println("NO SE PUDO CONECTAR CON LA BASE DE DATOS, ERROR: " + e);
        
    }
  }
  
    //Metodo para recuperar de la BD segun Git
  
    public static void printArticulos(Connection connection) throws SQLException {
        // Statement and ResultSet are AutoCloseable and closed automatically. 
        String sentenciaSql = "SELECT * FROM ARTICULO";
        try (Statement statement = connection.createStatement()) {      
          try (ResultSet resultSet = statement.executeQuery(sentenciaSql)) {
            System.out.println("CODIGO" + "  " + "NOMBRE");
            System.out.println("---------------------");
            while (resultSet.next())
              System.out.println(resultSet.getString(1) + " "
                  + resultSet.getString(2) + " ");       
          }
        }   
      }*/
