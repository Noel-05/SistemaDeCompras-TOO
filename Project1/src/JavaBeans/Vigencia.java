package JavaBeans;

import java.util.Date;

public class Vigencia {
    private String codArticulo;
    private String codProveedor;
    private Date fechaDesde;
    private Date fechaHasta;
    private float descuento;
    private float precio;
    private int tiempoEspera;
    private int periodoGracia;
    
    private String nombreArticulo;
    private String unidadMedida;
    
    private String nombreEmpresa;
    private String depto;
    private String municipio;
    private String telefono;
    private String correo;
    private String responsble;
    
    private String perGracia;


    public Vigencia(String codArticulo, String codProveedor, Date fechaDesde, Date fechaHasta, float descuento,
                    float precio, int tiempoEspera, int periodoGracia, String nombreArticulo, String unidadMedida,
                    String nombreEmpresa, String depto, String municipio, String telefono, String correo,
                    String responsble, String perGracia) {
        this.codArticulo = codArticulo;
        this.codProveedor = codProveedor;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.descuento = descuento;
        this.precio = precio;
        this.tiempoEspera = tiempoEspera;
        this.periodoGracia = periodoGracia;
        this.nombreArticulo = nombreArticulo;
        this.unidadMedida = unidadMedida;
        this.nombreEmpresa = nombreEmpresa;
        this.depto = depto;
        this.municipio = municipio;
        this.telefono = telefono;
        this.correo = correo;
        this.responsble = responsble;
        this.perGracia = perGracia;
    }


    public Vigencia(String codArticulo) {
        this.codArticulo = codArticulo;
    }


    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getCodArticulo() {
        return codArticulo;
    }

    public void setCodProveedor(String codProveedor) {
        this.codProveedor = codProveedor;
    }

    public String getCodProveedor() {
        return codProveedor;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecio() {
        return precio;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setPeriodoGracia(int periodoGracia) {
        this.periodoGracia = periodoGracia;
    }

    public int getPeriodoGracia() {
        return periodoGracia;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setResponsble(String responsble) {
        this.responsble = responsble;
    }

    public String getResponsble() {
        return responsble;
    }

    public void setPerGracia(String perGracia) {
        this.perGracia = perGracia;
    }

    public String getPerGracia() {
        return perGracia;
    }

}
