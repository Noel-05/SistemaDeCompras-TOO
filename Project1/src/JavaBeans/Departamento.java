package JavaBeans;

import java.util.Date;

public class Departamento {
    private String codigoDepartamento;
    private String nombreDepartamento;
    private String descripcionDepartamento;

    private String carnetEmpleado;
    private Date fecha;

    public Departamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }
    
    public Departamento(String codigoDepartamento, String nombreDepartamento, String descripcionDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.descripcionDepartamento = descripcionDepartamento;
    }

    public Departamento(String carnetEmpleado, Date fecha) {
        this.carnetEmpleado = carnetEmpleado;
        this.fecha = fecha;
    }

    public void setCarnetEmpleado(String carnetEmpleado) {
        this.carnetEmpleado = carnetEmpleado;
    }

    public String getCarnetEmpleado() {
        return carnetEmpleado;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
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

    public void setDescripcionDepartamento(String descripcionDepartamento) {
        this.descripcionDepartamento = descripcionDepartamento;
    }

    public String getDescripcionDepartamento() {
        return descripcionDepartamento;
    }
    
}
