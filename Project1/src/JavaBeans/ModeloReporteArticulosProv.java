package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloReporteArticulosProv {

    private ConexionBaseDatos origenDatos;
	
    public ModeloReporteArticulosProv(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }

    public List<Proveedor> getProveedores() throws Exception{
		List<Proveedor> proveedores = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        String miSql = "SELECT * FROM PROVEEDOR";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String codProv = miResultset.getString("CODIGOPROV");
            String nomProv = miResultset.getString("NOMEMPRESA");
            
            Proveedor temporal = new Proveedor(codProv, nomProv);
            
            proveedores.add(temporal);
        }
        
        return proveedores;        
    }

    public List<Vigencia> getVigencias(Vigencia vig) throws Exception{
	List<Vigencia> vigencias = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;

        // Statement miStatementEmp = null;
        // ResultSet miResultsetEmp = null;     

    
        //Establecer la conexion
        miConexion = origenDatos.getConexion();

        //Recupera el carnet y la fecha que se utilizara para el filtro
        // java.util.Date utilDate = soliArt.getFechaSol();
        // java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
        
        String prov = vig.getCodProveedor();
        
        //Crear sentencia SQL y Statement
		//String miSQl = "select s.codarticulo, sum(s.cantartsol) from solicitudart s inner join empleado e on s.carnetempleado=e.carnetempleado inner join catalagopuesto c on e.codigopuesto=c.codigopuesto inner join departamento d on c.codigodepartamento=d.codigodepartamento where d. codigodepartamento= '"+depto+"' and s.fecsol >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS') group by s.codarticulo order by s.codarticulo";
		String miSQl = "select * from vigencia v where v.codigoprov = '"+prov+"' AND v.fechahasta >= current_date";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSQl);
        
        while(miResultset.next()){
            String codArticulo = miResultset.getString("CODARTICULO");        	
            String codigoProv = miResultset.getString("CODIGOPROV");
            Date fechaDesde = miResultset.getDate("FECHADESDE");
            Date fechaHasta = miResultset.getDate("FECHAHASTA");
            // String nombreProv = miResultset.getString("NOMEMPRESA");
            // String nombreArt = miResultset.getString("NOMBREART");
            //int cantidad = miResultset.getInt("CANTART");
            float descuento = miResultset.getFloat("DESCUENTO");
            float precio = miResultset.getFloat("PRECIO");
            int entregaInmediata = miResultset.getInt("TIEMPOENTREGA");
            int periodoGracia = miResultset.getInt("PERIODOGRACIA");
            //float precioTotal = miResultset.getFloat("PRECIOTOTAL");

            String perGracia = "";
            if(periodoGracia == 0){
                perGracia = "SI";
            }else{
                perGracia = "NO";
            }

            //Recuperar el nombre del articulo, y la unidad de medida segun el codigo de articulo que se esta recuperando
            String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codArticulo+"'";
            miStatementBusc = miConexion.createStatement();
            miResultsetBusc = miStatementBusc.executeQuery(buscar);
            String nombreArticulo = null;
            String unidadMedida = null;
            while(miResultsetBusc.next()){
                nombreArticulo = miResultsetBusc.getString("NOMBREART");
            }                      
            
            Vigencia temporal = new Vigencia(fechaDesde, codArticulo, codigoProv, fechaHasta, descuento, precio, entregaInmediata, periodoGracia, perGracia, nombreArticulo);
            
            vigencias.add(temporal);
        }


        return vigencias;    }

    public List<Proveedor> getProveedoresPar(Proveedor codProv) throws Exception{
		List<Proveedor> proveedores = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();

        String prov = codProv.getCodigoProv();

        
        //Crear sentencia SQL y Statement
        String miSql = "SELECT * FROM PROVEEDOR p where p.codigoprov = '"+prov+"'";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String codProveedor = miResultset.getString("CODIGOPROV");
            String nomProv = miResultset.getString("NOMEMPRESA");
            
            Proveedor temporal = new Proveedor(codProveedor, nomProv);
            
            proveedores.add(temporal);
        }
        
        return proveedores;         }
}
