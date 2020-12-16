package JavaBeans;

import java.util.Date;

public class Movimiento {
    
    int idMov;//Autoincrementable no insertar en los constructores
    int idInv;//el identificador de los productos de inventario
    String carnetEmpleado;
    String tipo;
    int cantMov;
    double valUnidad;
    double valTotal;
    Date fecMov;
    
    Date fecha;
    String articulo;
    
    public Movimiento() {
    }


    public Movimiento(int idInv, String carnetEmpleado, String tipo, int cantMov, double valUnidad, Date fecMov) {
        this.idInv = idInv;
        this.carnetEmpleado = carnetEmpleado;
        this.tipo = tipo;
        this.cantMov = cantMov;
        this.valUnidad = valUnidad;
        this.fecMov = fecMov;
    }


    public Movimiento(String carnetEmpleado, String tipo, int cantMov, double valUnidad, double valTotal, Date fecMov) {
        this.carnetEmpleado = carnetEmpleado;
        this.tipo = tipo;
        this.cantMov = cantMov;
        this.valUnidad = valUnidad;
        this.valTotal = valTotal;
        this.fecMov = fecMov;
    }


    public Movimiento(Date fecha, String articulo) {
        this.fecha = fecha;
        this.articulo = articulo;
    }


    public void setIdMov(int idMov) {
        this.idMov = idMov;
    }

    public int getIdMov() {
        return idMov;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setIdInv(int idInv) {
        this.idInv = idInv;
    }

    public int getIdInv() {
        return idInv;
    }

    public void setCarnetEmpleado(String carnetEmpleado) {
        this.carnetEmpleado = carnetEmpleado;
    }

    public String getCarnetEmpleado() {
        return carnetEmpleado;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setCantMov(int cantMov) {
        this.cantMov = cantMov;
    }

    public int getCantMov() {
        return cantMov;
    }

    public void setValUnidad(double valUnidad) {
        this.valUnidad = valUnidad;
    }

    public double getValUnidad() {
        return valUnidad;
    }

    public void setValTotal(double valTotal) {
        this.valTotal = valTotal;
    }

    public double getValTotal() {
        return valTotal;
    }

    public void setFecMov(Date fecMov) {
        this.fecMov = fecMov;
    }

    public Date getFecMov() {
        return fecMov;
    }
}
