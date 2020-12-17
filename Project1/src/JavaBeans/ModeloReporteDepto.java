package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloReporteDepto {

    private ConexionBaseDatos origenDatos;
	
    public ModeloReporteDepto(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }

    public List<SolicitudArt> getSolicitudesArt(SolicitudArt soliArt) throws Exception{
	   List<SolicitudArt> solicitudes = new ArrayList<>();
        
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
        java.util.Date utilDate = soliArt.getFechaSol();
        java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
        
        String depto = soliArt.getCodDepto();
        
        //Crear sentencia SQL y Statement
        // String miSql = "SELECT * FROM SOLICITUDART ORDER BY FECSOL";
        //String miSQl = "select * from solicitudart s inner join empleado e on s.carnetempleado=e.carnetempleado inner join catalagopuesto c on e.codigopuesto=c.codigopuesto inner join departamento d on c.codigodepartamento=d.codigodepartamento where d.codigodepartamento= '"+depto+"' and s.fecsol >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS') order by s.fecsol";
		String miSQl = "select s.codarticulo, sum(s.cantartsol) from solicitudart s inner join empleado e on s.carnetempleado=e.carnetempleado inner join catalagopuesto c on e.codigopuesto=c.codigopuesto inner join departamento d on c.codigodepartamento=d.codigodepartamento where d. codigodepartamento= '"+depto+"' and s.fecsol >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS') group by s.codarticulo order by s.codarticulo";
        
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSQl);
        
        while(miResultset.next()){
            String codigoArticulo = miResultset.getString("CODARTICULO");
            int sumaArticulos = miResultset.getInt("SUM(S.CANTARTSOL)");

            //Recuperar el nombre del articulo, y la unidad de medida segun el codigo de articulo que se esta recuperando
            String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codigoArticulo+"'";
            miStatementBusc = miConexion.createStatement();
            miResultsetBusc = miStatementBusc.executeQuery(buscar);
            String nombreArticulo = null;
            String unidadMedida = null;
            while(miResultsetBusc.next()){
                nombreArticulo = miResultsetBusc.getString("NOMBREART");
                unidadMedida = miResultsetBusc.getString("UNIDADMEDIDA");
            }                      
            
            SolicitudArt temporal = new SolicitudArt(codigoArticulo, nombreArticulo, unidadMedida, sumaArticulos);
            
            solicitudes.add(temporal);
        }


        return solicitudes;    
    }

    public List<Departamento> getDepartamentosPar(Departamento departamento) {
		List<Departamento> departamentos = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;

        String codDepto = departamento.getCodigoDepartamento();

        
        try{

        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        //String miSql = "select * from departamento d inner join catalagopuesto c on d.codigodepartamento = c.codigodepartamento inner join empleado e on c.codigopuesto = e.codigopuesto where e.carnetempleado = '"+carnet+"'";
        String miSql = "select * from departamento d where d.codigodepartamento = '"+codDepto+"'";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String codigoDepto = miResultset.getString("CODIGODEPARTAMENTO");
            String nombreDepto = miResultset.getString("NOMBREDEPARTAMENTO");
            String descDepto = miResultset.getString("DESCRIPCIONDEPARTAMENTO");
            
            Departamento temporal = new Departamento(codigoDepto, nombreDepto, descDepto);
            
            departamentos.add(temporal);
        }

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return departamentos;    }

    public List<Departamento> getDepartamentos() throws Exception{
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

    public List<Articulos> getArticulos() {
        return null;
    }
}
