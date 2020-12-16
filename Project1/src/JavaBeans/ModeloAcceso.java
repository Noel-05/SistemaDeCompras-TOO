package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ModeloAcceso {

    private ConexionBaseDatos origenDatos;
    
    public ModeloAcceso(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }

    public ModeloAcceso() {
    	
    }

    public int validar(String usuario, String contra) throws Exception{
    	int nivel=0;
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
     
        //Establecer la conexion
        miConexion = origenDatos.getConexion();

        
        //Crear sentencia SQL y Statement
		String miSQl = "select nivel from usuario where nomusuario = '"+usuario+"' and clave = '"+contra+"'";
        
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSQl);
        
        while(miResultset.next()){
            nivel = miResultset.getInt("NIVEL");                      
            
            //SolicitudArt temporal = new SolicitudArt(codigoArticulo, nombreArticulo, unidadMedida, sumaArticulos);
            //solicitudes.add(temporal);
        }
        return nivel;    
        
    }

}
