package JavaBeans;

import Utils.ConexionBaseDatos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloRequisicion {

    private ConexionBaseDatos origenDatos;


    public ModeloRequisicion(ConexionBaseDatos origenDatos) {
        this.origenDatos = origenDatos;
    }
//Consulta Requisiciones
//Requisicion
public List<Requisicion> getRequisicion() throws Exception{
        List<Requisicion> requisicion = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;

        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;

        Statement miStatementBuscDepto = null;
        ResultSet miResultsetBuscDepto = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        String miSql = "SELECT * FROM REQUISICION";
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

            //Recuperar el nombre del articulo, y la unidad de medida segun el codigo de articulo que se esta recuperando
            String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codArticulo+"'";
            miStatementBusc = miConexion.createStatement();
            miResultsetBusc = miStatementBusc.executeQuery(buscar);
            String nombreArticulo = null;
            String unidadMedida = null;
            while(miResultsetBusc.next()){
                nombreArticulo = miResultsetBusc.getString("NOMBREART");
                unidadMedida = miResultsetBusc.getString("UNIDADMEDIDA");
            }

            //Recupero el nombre Departamento al que pertenece el empleado
            String buscarDepto = "select * from empleado inner join catalagopuesto c on empleado.codigopuesto =c.codigopuesto inner join departamento d on c.codigodepartamento = d.codigodepartamento where carnetempleado = '"+carnetEmpleado+"'";
            miStatementBuscDepto = miConexion.createStatement();
            miResultsetBuscDepto = miStatementBuscDepto.executeQuery(buscarDepto);
            String codDepto = null;            
            String nombreDepartamento = null;
            while(miResultsetBuscDepto.next()){
                codDepto = miResultsetBuscDepto.getString("CODIGODEPARTAMENTO");                
                nombreDepartamento = miResultsetBuscDepto.getString("NOMBREDEPARTAMENTO");
            }
       
            Requisicion temporal = new Requisicion(fechaPedido, fechaEntrega, autorizado, entregado, cantidad, codArticulo, carnetEmpleado, nombreArticulo, unidadMedida, codDepto ,nombreDepartamento);
            requisicion.add(temporal);
        }
        
        return requisicion;
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

    public List<Requisicion> obtenerRequisicionDepto(Departamento consultaDepartamento) throws Exception{
        List<Requisicion> requisicionesDepto = new ArrayList<>();
        
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
            
            //Recupero el departanmento que voy a buscar
            String depbusqueda = consultaDepartamento.getCodigoDepartamento();
            
            //Crear sentencia SQL y Statement
            String miSql = "select * from requisicion r inner join empleado e on e.carnetempleado = r.carnetempleado inner join catalagopuesto c on e.codigopuesto =c.codigopuesto inner join departamento d on c.codigodepartamento = d.codigodepartamento where d.codigodepartamento = '"+depbusqueda+"'";

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

                //Recuperar el nombre del articulo, y la unidad de medida segun el codigo de articulo que se esta recuperando
                String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codArticulo+"'";
                miStatementBusc = miConexion.createStatement();
                miResultsetBusc = miStatementBusc.executeQuery(buscar);
                String nombreArticulo = null;
                String unidadMedida = null;
                while(miResultsetBusc.next()){
                    nombreArticulo = miResultsetBusc.getString("NOMBREART");
                    unidadMedida = miResultsetBusc.getString("UNIDADMEDIDA");
                }

                //Recupero el nombre Departamento al que pertenece el empleado
                String buscarDepto = "select * from empleado inner join catalagopuesto c on empleado.codigopuesto =c.codigopuesto inner join departamento d on c.codigodepartamento = d.codigodepartamento where carnetempleado = '"+carnetEmpleado+"'";
                miStatementBuscDepto = miConexion.createStatement();
                miResultsetBuscDepto = miStatementBuscDepto.executeQuery(buscarDepto);
                String codDepto = null;            
                String nombreDepartamento = null;
                while(miResultsetBuscDepto.next()){
                    codDepto = miResultsetBuscDepto.getString("CODIGODEPARTAMENTO");                
                    nombreDepartamento = miResultsetBuscDepto.getString("NOMBREDEPARTAMENTO");
                }
                
                Requisicion temporal = new Requisicion(fechaPedido, fechaEntrega, autorizado, entregado, cantidad, codArticulo, carnetEmpleado, nombreArticulo, unidadMedida, codDepto ,nombreDepartamento);
                requisicionesDepto.add(temporal);

            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return  requisicionesDepto;
    }

 //Crear Requisicion   

    public List<SolicitudArt> getSolicitudesArt(SolicitudArt soliArt) throws Exception{
        List<SolicitudArt> solicitudes = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        Statement miStatementBusc = null;
        ResultSet miResultsetBusc = null;

        Statement miStatementEmp = null;
        ResultSet miResultsetEmp = null;

        Connection miConexionFil = null;
        Statement miStatementFil = null;
        ResultSet miResultsetFil = null;        

    
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        miConexionFil = origenDatos.getConexion();

        //Recupera el carnet y la fecha que se utilizara para el filtro
        java.util.Date utilDate = soliArt.getFechaSol();
        java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
        
        String empleado = soliArt.getCarnetEmpleado();

        //Recupera el nombre del depto del qu solicita
        String buscarDepto = "select * from empleado e inner join catalagopuesto c on e.codigopuesto = c.codigopuesto INNER JOIN departamento d on c.codigodepartamento = d.codigodepartamento where e.carnetempleado = '"+empleado+"'";
        miStatementFil = miConexionFil.createStatement();
        miResultsetFil = miStatementFil.executeQuery(buscarDepto);
        String depto = null;
        while(miResultsetFil.next()){
            depto = miResultsetFil.getString("CODIGODEPARTAMENTO");
        }

        
        //Crear sentencia SQL y Statement
        // String miSql = "SELECT * FROM SOLICITUDART ORDER BY FECSOL";
        String miSQl = "select * from solicitudart s inner join empleado e on s.carnetempleado=e.carnetempleado inner join catalagopuesto c on e.codigopuesto=c.codigopuesto inner join departamento d on c.codigodepartamento=d.codigodepartamento where d.codigodepartamento= '"+depto+"' and s.fecsol >= TO_DATE('"+fechaConvertida+"', 'YYYY/MM/DD HH:MI:SS') order by s.fecsol";
        
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSQl);
        
        while(miResultset.next()){
            String carnetEmpleado = miResultset.getString("CARNETEMPLEADO");
            String codigoArticulo = miResultset.getString("CODARTICULO");
            int cantidadArticulo = miResultset.getInt("CANTARTSOL");
            Date fechaSol = miResultset.getDate("FECSOL");          
            
            //Recupera el nombre del articulo segun el codigo de articulo que se esta recuperando
            String buscar = "SELECT * FROM ARTICULO WHERE CODARTICULO = '"+codigoArticulo+"'";
            miStatementBusc = miConexion.createStatement();
            miResultsetBusc = miStatementBusc.executeQuery(buscar);
            String nombreArticulo = null;
            while(miResultsetBusc.next()){
                nombreArticulo = miResultsetBusc.getString("NOMBREART");
            }

            //Recupera el nombre del empleado segun el ccarnet del Empleado que se esta recuperando
            String buscarEmp = "SELECT * FROM EMPLEADO WHERE CARNETEMPLEADO = '"+carnetEmpleado+"'";
            miStatementEmp = miConexion.createStatement();
            miResultsetEmp = miStatementEmp.executeQuery(buscarEmp);
            String nombreEmpleado = null;
            while(miResultsetEmp.next()){
                nombreEmpleado = miResultsetEmp.getString("NOMBREEMPLEADO");
            }  
            
            
            SolicitudArt temporal = new SolicitudArt(carnetEmpleado, codigoArticulo, cantidadArticulo, fechaSol, nombreArticulo, nombreEmpleado);
            
            solicitudes.add(temporal);
        }


        return solicitudes;
    }

    public List<Articulos> getArticulos(Articulos articulo) throws Exception{
        List<Articulos> articulos = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;

        Connection miConexionDep = null;
        Statement miStatementDep = null;
        ResultSet miResultsetDep = null;

        try{

        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        miConexionDep = origenDatos.getConexion();

        String carnet = articulo.getCodCarnet();

        //Obtener Departamento
        String buscarDepto = "select * from empleado e inner join catalagopuesto c on e.codigopuesto = c.codigopuesto INNER JOIN departamento d on c.codigodepartamento = d.codigodepartamento where e.carnetempleado = '"+carnet+"'";
        miStatementDep = miConexionDep.createStatement();
        miResultsetDep = miStatementDep.executeQuery(buscarDepto);
        String depto = null;
        while(miResultsetDep.next()){
            depto = miResultsetDep.getString("CODIGODEPARTAMENTO");
        }
        

        //Crear sentencia SQL y Statement
        //Recuperamos los articulos cuales tienen relacion con el departamento
        // String miSql = "SELECT * FROM ARTICULO ORDER BY CODARTICULO";
        String miSql = "select distinct a.codarticulo, a.nombreart, a.unidadmedida from articulo a inner join solicitudart s on a.codarticulo = s.codarticulo inner join empleado e on s.carnetempleado = e.carnetempleado inner join catalagopuesto c on e.codigopuesto = c.codigopuesto inner join departamento d on c.codigodepartamento = d.codigodepartamento where c.codigodepartamento = '"+depto+"'";
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String codArticulo = miResultset.getString("CODARTICULO");
            String nombreArticulo = miResultset.getString("NOMBREART");
            String unidadMedida = miResultset.getString("UNIDADMEDIDA");
            
            Articulos temporal = new Articulos(codArticulo, nombreArticulo, unidadMedida);
            
            articulos.add(temporal);
        }


        }catch(Exception e){
            e.printStackTrace();
        }
        
        return articulos;
    }


    public List<Departamento> getDepartamentosPar(Departamento departamento) {
        List<Departamento> departamentos = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;

        String carnet = departamento.getCarnetEmpleado();

        
        try{

        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        String miSql = "select * from departamento d inner join catalagopuesto c on d.codigodepartamento = c.codigodepartamento inner join empleado e on c.codigopuesto = e.codigopuesto where e.carnetempleado = '"+carnet+"'";
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

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return departamentos;
    }

    //Obtener el carnet del Empleado para mostrarlo en requerirCompra.jsp
    public List<Empleado> getEmpleados(Empleado emp) {
        List<Empleado> empleados = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;

        String carnet = emp.getCarnetEmpleado();

        
        try{

        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        
        //Crear sentencia SQL y Statement
        // String miSql = "select * from departamento d inner join catalagopuesto c on d.codigodepartamento = c.codigodepartamento inner join empleado e on c.codigopuesto = e.codigopuesto where e.carnetempleado = '"+carnet+"'";
        String miSql = "select * from empleado e WHERE e.carnetempleado = '"+carnet+"'";        
        miStatement = miConexion.createStatement();
        
        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            String codCarnet = miResultset.getString("CARNETEMPLEADO");
            
            Empleado temporal = new Empleado(codCarnet);
            
            empleados.add(temporal);
        }

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return empleados;
    }

    //Recuperamos el ultimo valor de numReq de la Requisicion
    public List<Requisicion> getNumeroRequisicion() throws Exception{
        List<Requisicion> requisiciones = new ArrayList<>();
        
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;

        Connection miConexionNum = null;
        Statement miStatementNum = null;
        ResultSet miResultsetNum = null;
        
        //Establecer la conexion
        miConexion = origenDatos.getConexion();
        miConexionNum = origenDatos.getConexion();
        
        //SQL que recupera el ultimo valor
        String consultaReqS = "select max(numreq) from requisicion";
        miStatementNum = miConexionNum.createStatement();
        miResultsetNum = miStatementNum.executeQuery(consultaReqS);
        int numeroRequisicion = 0;
        while(miResultsetNum.next()){
            //La columna MAX(REQ) es creada por la consulta de consultaReS
            numeroRequisicion = miResultsetNum.getInt("MAX(NUMREQ)");
        }

        String miSql = "select * from requisicion r where r.numreq = '"+numeroRequisicion+"'";
        miStatement = miConexion.createStatement();

        //Ejecutar SQL
        miResultset = miStatement.executeQuery(miSql);
        
        while(miResultset.next()){
            int numReq = miResultset.getInt("NUMREQ") + 1;
       
            Requisicion temporal = new Requisicion(numReq);
            requisiciones.add(temporal);
        }
        
        return requisiciones;

    }

    public void agregarNuevaRequisicion(Requisicion nuevaRequisicion) {
        Connection miConexion = null;
        PreparedStatement miStatement = null;
        
        //Obtener la conexion
        try{
            
            miConexion = origenDatos.getConexion();
       
            //Crear sentencia sql que inserte la requisicion a la base
            // String misql = "INSERT INTO SOLICITUDART(CARNETEMPLEADO, CODARTICULO, CANTARTSOL, FECSOL) VALUES (?, ?, ?, ?)";
            String misql = "insert into requisicion (numreq, fecpedidoreq, fecentregareq, cantart, codarticulo, carnetempleado) VALUES (?, ?, ?, ?, ?, ?)";
            miStatement = miConexion.prepareStatement(misql);
            
            //Establecer los parametros para insertar la requisicion
            miStatement.setInt(1,nuevaRequisicion.getNumReq());

            java.util.Date utilDate1 = nuevaRequisicion.getFechaPedidoReq();
            java.sql.Date fechaConvertidaPed = new java.sql.Date(utilDate1.getTime());
            miStatement.setDate(2, fechaConvertidaPed);

            java.util.Date utilDate = nuevaRequisicion.getFechaEntregaReq();
            java.sql.Date fechaConvertidaEn = new java.sql.Date(utilDate.getTime());
            miStatement.setDate(3, fechaConvertidaEn);

            miStatement.setInt(4, nuevaRequisicion.getCantArt());

            miStatement.setString(5, nuevaRequisicion.getCodArticulo());            
            
            miStatement.setString(6, nuevaRequisicion.getCarnetEmpleado());            


            
            //Ejecutar la instruccion sql
            miStatement.execute();
        
        }catch(Exception e){
            e.printStackTrace();
        }    

    }
}
