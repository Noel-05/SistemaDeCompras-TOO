package JavaBeans;

import java.util.Date;

public class Requisicion {
    private int numReq;
    private Date fechaPedidoReq;
    private Date fechaEntregaReq;
    private int autorizado;
    private int entregado;
    private int cantArt;
    private String codArticulo;
    private String CarnetEmpleado;
    
    private String nombreArticulo;
    private String codigoDepartamento;
    private String unidadMedida;
    private String nombreDepartamento;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String estadoAut;
    private Date fechaNueva;


    public Requisicion(){
        
    }

    public Requisicion(Date fechaPedidoReq, Date fechaEntregaReq, int autorizado, int entregado, int cantArt,
                       String codArticulo, String CarnetEmpleado) {
        this.fechaPedidoReq = fechaPedidoReq;
        this.fechaEntregaReq = fechaEntregaReq;
        this.autorizado = autorizado;
        this.entregado = entregado;
        this.cantArt = cantArt;
        this.codArticulo = codArticulo;
        this.CarnetEmpleado = CarnetEmpleado;
    }
    
    public Requisicion(int numRequisicion, Date fechaPedidoReq, Date fechaEntregaReq, String CarnetEmpleado, String nombreEmpleado, String apellidoEmpleado, String nombreDepartamento) {
        this.numReq = numRequisicion;
        this.fechaPedidoReq = fechaPedidoReq;
        this.fechaEntregaReq = fechaEntregaReq;       
        this.CarnetEmpleado = CarnetEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.nombreDepartamento = nombreDepartamento;
    }
        
    public Requisicion(int numRequisicion, Date fechaPedidoReq, Date fechaEntregaReq, int autorizado, String CarnetEmpleado, String nombreEmpleado, String apellidoEmpleado, String nombreDepartamento) {
        this.numReq = numRequisicion;
        this.fechaPedidoReq = fechaPedidoReq;
        this.fechaEntregaReq = fechaEntregaReq;
        this.autorizado = autorizado;
        this.CarnetEmpleado = CarnetEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.nombreDepartamento = nombreDepartamento;
    }

    public Requisicion(Date fechaPedidoReq, Date fechaEntregaReq, int autorizado, int entregado, int cantArt,
                       String codArticulo, String CarnetEmpleado, String nombreArticulo, String codigoDepartamento,
                       String nombreDepartamento) {
        this.fechaPedidoReq = fechaPedidoReq;
        this.fechaEntregaReq = fechaEntregaReq;
        this.autorizado = autorizado;
        this.entregado = entregado;
        this.cantArt = cantArt;
        this.codArticulo = codArticulo;
        this.CarnetEmpleado = CarnetEmpleado;
        this.nombreArticulo = nombreArticulo;
        this.codigoDepartamento = codigoDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }
    //Contructor nombre de articulo, departamento y unidad de medida
    public Requisicion(int numReq, Date fechaPedidoReq, Date fechaEntregaReq, int autorizado, int entregado, int cantArt,
                       String codArticulo, String CarnetEmpleado, String nombreArticulo, String unidadMedida, 
                       String codigoDepartamento, String nombreDepartamento) {
        this.numReq = numReq;
        this.fechaPedidoReq = fechaPedidoReq;
        this.fechaEntregaReq = fechaEntregaReq;
        this.autorizado = autorizado;
        this.entregado = entregado;
        this.cantArt = cantArt;
        this.codArticulo = codArticulo;
        this.CarnetEmpleado = CarnetEmpleado;
        this.nombreArticulo = nombreArticulo;
        this.unidadMedida = unidadMedida;
        this.codigoDepartamento = codigoDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }

    public Requisicion(Date fechaPedidoReq, Date fechaEntregaReq, int autorizado, int entregado, int cantArt,
                       String codArticulo, String CarnetEmpleado, String nombreArticulo, String unidadMedida, 
                       String codigoDepartamento, String nombreDepartamento) {
        this.fechaPedidoReq = fechaPedidoReq;
        this.fechaEntregaReq = fechaEntregaReq;
        this.autorizado = autorizado;
        this.entregado = entregado;
        this.cantArt = cantArt;
        this.codArticulo = codArticulo;
        this.CarnetEmpleado = CarnetEmpleado;
        this.nombreArticulo = nombreArticulo;
        this.unidadMedida = unidadMedida;
        this.codigoDepartamento = codigoDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }
    public Requisicion(Date fechaPedidoReq, String codigoDepartamento) {
        this.fechaPedidoReq = fechaPedidoReq;
        this.codigoDepartamento = codigoDepartamento;
    }

    public Requisicion(String CarnetEmpleado, Date fechaPedidoReq) {
        this.fechaPedidoReq = fechaPedidoReq;
        this.CarnetEmpleado = CarnetEmpleado;
    }

    public Requisicion(int numReq) {
        this.numReq = numReq;
    }


    public Requisicion(int numReq, String estadoAut) {
        this.numReq = numReq;
        this.estadoAut = estadoAut;
    }
    
    

    //Constructor para crear la Requisicion sin autorizacion y entregado
    public Requisicion(int numReq, Date fechaPedidoReq, Date fechaEntregaReq, int cantArt, String codArticulo,
                       String CarnetEmpleado) {
        this.numReq = numReq;
        this.fechaPedidoReq = fechaPedidoReq;
        this.fechaEntregaReq = fechaEntregaReq;
        this.cantArt = cantArt;
        this.codArticulo = codArticulo;
        this.CarnetEmpleado = CarnetEmpleado;
    }


    public Requisicion(String CarnetEmpleado, String estadoAut, Date fechaNueva) {
        this.CarnetEmpleado = CarnetEmpleado;
        this.estadoAut = estadoAut;
        this.fechaNueva = fechaNueva;
    }

    public void setFechaNueva(Date fechaNueva) {
        this.fechaNueva = fechaNueva;
    }

    public Date getFechaNueva() {
        return fechaNueva;
    }

    public void setEstadoAut(String estadoAut) {
        this.estadoAut = estadoAut;
    }

    public String getEstadoAut() {
        return estadoAut;
    }

    public void setNumReq(int numReq) {
        this.numReq = numReq;
    }

    public int getNumReq() {
        return numReq;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setFechaPedidoReq(Date fechaPedidoReq) {
        this.fechaPedidoReq = fechaPedidoReq;
    }

    public Date getFechaPedidoReq() {
        return fechaPedidoReq;
    }

    public void setFechaEntregaReq(Date fechaEntregaReq) {
        this.fechaEntregaReq = fechaEntregaReq;
    }

    public Date getFechaEntregaReq() {
        return fechaEntregaReq;
    }

    public void setAutorizado(int autorizado) {
        this.autorizado = autorizado;
    }

    public int getAutorizado() {
        return autorizado;
    }

    public void setEntregado(int entregado) {
        this.entregado = entregado;
    }

    public int getEntregado() {
        return entregado;
    }

    public void setCantArt(int cantArt) {
        this.cantArt = cantArt;
    }

    public int getCantArt() {
        return cantArt;
    }

    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getCodArticulo() {
        return codArticulo;
    }

    public void setCarnetEmpleado(String CarnetEmpleado) {
        this.CarnetEmpleado = CarnetEmpleado;
    }

    public String getCarnetEmpleado() {
        return CarnetEmpleado;
    }
    
    public void setNombreEmpleado(String NombreEmpleado) {
            this.nombreEmpleado = NombreEmpleado;
        }

        public String getNombreEmpleado() {
            return nombreEmpleado;
        }
        
        public void setApellidoEmpleado(String ApellidoEmpleado) {
            this.apellidoEmpleado = ApellidoEmpleado;
        }

        public String getApellidoEmpleado() {
            return apellidoEmpleado;
        }
}
