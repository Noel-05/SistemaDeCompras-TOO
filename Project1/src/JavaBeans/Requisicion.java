package JavaBeans;

import java.util.Date;

public class Requisicion {
    private Date fechaPedidoReq;
    private Date fechaEntregaReq;
    private int autorizado;
    private int entregado;
    private int cantArt;
    private String codArticulo;
    private String CarnetEmpleado;
    
    private String nombreArticulo;
    private String codigoDepartamento;
    private String nombreDepartamento;


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


    public Requisicion(Date fechaPedidoReq, String codigoDepartamento) {
        this.fechaPedidoReq = fechaPedidoReq;
        this.codigoDepartamento = codigoDepartamento;
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
}
