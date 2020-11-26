package JavaBeans;

import java.util.Date;

public class SolicitudArt {
    private String carnetEmpleado;
    private String codArticulo;
    private int cantArticulo;
    private Date fechaSol;
    
    private String nombreArticulo;


    public SolicitudArt(String carnetEmpleado, String codArticulo, int cantArticulo, Date fechaSol) {
        this.carnetEmpleado = carnetEmpleado;
        this.codArticulo = codArticulo;
        this.cantArticulo = cantArticulo;
        this.fechaSol = fechaSol;
    }
    
    public SolicitudArt(String carnetEmpleado, String codArticulo, int cantArticulo, Date fechaSol, String nombreArticulo) {
        this.carnetEmpleado = carnetEmpleado;
        this.codArticulo = codArticulo;
        this.cantArticulo = cantArticulo;
        this.fechaSol = fechaSol;
        this.nombreArticulo = nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setCarnetEmpleado(String carnetEmpleado) {
        this.carnetEmpleado = carnetEmpleado;
    }

    public String getCarnetEmpleado() {
        return carnetEmpleado;
    }

    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getCodArticulo() {
        return codArticulo;
    }

    public void setCantArticulo(int cantArticulo) {
        this.cantArticulo = cantArticulo;
    }

    public int getCantArticulo() {
        return cantArticulo;
    }

    public void setFechaSol(Date fechaSol) {
        this.fechaSol = fechaSol;
    }

    public Date getFechaSol() {
        return fechaSol;
    }
    
    public String toString(){
        return "Solicitudes(carnetEmpleado=" + carnetEmpleado + ", codArticulo=" + codArticulo + ", cantArticulo=" + cantArticulo + ", fechaSol=" + fechaSol + ")" ;
    }

}
