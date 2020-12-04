package JavaBeans;

public class Departamento {
    private String codigoDepartamento;
    private String nombreDepartamento;
    private String descripcionDepartamento;

    public Departamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }
    
    public Departamento(String codigoDepartamento, String nombreDepartamento, String descripcionDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.descripcionDepartamento = descripcionDepartamento;
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
