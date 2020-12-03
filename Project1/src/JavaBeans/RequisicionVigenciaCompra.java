package JavaBeans;

public class RequisicionVigenciaCompra {
    String codigoDepartamento;
    String codArticulo;
    String codProveedor;
    int cantArt;
    float precioTotal;
    
    String nombreProv;
    String nombreArt;
    float descuento;
    float precio;
    int periodoGracia;
    int entregaInmediata;
    

    public RequisicionVigenciaCompra(String codigoDepartamento, String codArticulo, String codProveedor, int cantArt,
                                     float precioTotal) {
        this.codigoDepartamento = codigoDepartamento;
        this.codArticulo = codArticulo;
        this.codProveedor = codProveedor;
        this.cantArt = cantArt;
        this.precioTotal = precioTotal;
    }


    public RequisicionVigenciaCompra(String codigoDepartamento, String codArticulo, String codProveedor, int cantArt) {
        this.codigoDepartamento = codigoDepartamento;
        this.codArticulo = codArticulo;
        this.codProveedor = codProveedor;
        this.cantArt = cantArt;
    }


    public RequisicionVigenciaCompra(String codigoDepartamento, String codArticulo, String codProveedor, int cantArt,
                                     String nombreProv, String nombreArt, float descuento, float precio,
                                     int periodoGracia, int entregaInmediata) {
        this.codigoDepartamento = codigoDepartamento;
        this.codArticulo = codArticulo;
        this.codProveedor = codProveedor;
        this.cantArt = cantArt;
        this.nombreProv = nombreProv;
        this.nombreArt = nombreArt;
        this.descuento = descuento;
        this.precio = precio;
        this.periodoGracia = periodoGracia;
        this.entregaInmediata = entregaInmediata;
    }

    public RequisicionVigenciaCompra(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }


    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
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

    public void setCantArt(int cantArt) {
        this.cantArt = cantArt;
    }

    public int getCantArt() {
        return cantArt;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreArt(String nombreArt) {
        this.nombreArt = nombreArt;
    }

    public String getNombreArt() {
        return nombreArt;
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

    public void setPeriodoGracia(int periodoGracia) {
        this.periodoGracia = periodoGracia;
    }

    public int getPeriodoGracia() {
        return periodoGracia;
    }

    public void setEntregaInmediata(int entregaInmediata) {
        this.entregaInmediata = entregaInmediata;
    }

    public int getEntregaInmediata() {
        return entregaInmediata;
    }

}
