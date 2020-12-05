package JavaBeans;

public class Empleado {
    String carnetEmpleado;
    String codigoPuesto;
    String nombreEmpleado;
    String apellidoEmpleado;
    String genero;
    String depto;
    String municipio;
    String estadoCivil;

    public Empleado(String carnetEmpleado) {
        this.carnetEmpleado = carnetEmpleado;
    }

    public Empleado(String carnetEmpleado, String codigoPuesto, String nombreEmpleado, String apellidoEmpleado,
                    String genero, String depto, String municipio, String estadoCivil) {
        this.carnetEmpleado = carnetEmpleado;
        this.codigoPuesto = codigoPuesto;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.genero = genero;
        this.depto = depto;
        this.municipio = municipio;
        this.estadoCivil = estadoCivil;
    }
    

    public void setCarnetEmpleado(String carnetEmpleado) {
        this.carnetEmpleado = carnetEmpleado;
    }

    public String getCarnetEmpleado() {
        return carnetEmpleado;
    }

    public void setCodigoPuesto(String codigoPuesto) {
        this.codigoPuesto = codigoPuesto;
    }

    public String getCodigoPuesto() {
        return codigoPuesto;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public String getDepto() {
        return depto;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }
}
