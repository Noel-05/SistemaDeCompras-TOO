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
