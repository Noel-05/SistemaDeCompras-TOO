package JavaBeans;

public class Articulos {
    private String codArticulo;
    private String nombreArticulo;
    private String unidadMedida;

    private String codCarnet;

    public Articulos(String codArticulo, String nombreArticulo, String unidadMedida) {
        this.codArticulo = codArticulo;
        this.nombreArticulo = nombreArticulo;
        this.unidadMedida = unidadMedida;
    }

    public Articulos(String codCarnet) {
        this.codCarnet = codCarnet;
    }

    public void setCodCarnet(String codCarnet) {
        this.codCarnet = codCarnet;
    }

    public String getCodCarnet() {
        return codCarnet;
    }
    
    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getCodArticulo() {
        return codArticulo;
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

    public String toString(){
        return "Articulos(codArticulo=" + codArticulo + ", nombreArticulo=" + nombreArticulo + ", unidadMedida=" + unidadMedida + ")" ;
    }
    
}
