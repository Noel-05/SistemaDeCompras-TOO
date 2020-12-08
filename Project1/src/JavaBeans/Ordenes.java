package JavaBeans;

import java.util.Date;

public class Ordenes {

    private String codProveedor;
    private float precioTotal;

    private Date fecha;
    private double sumaPrecio;
    private int cantOrdenes;
    private String nomProveedor;

    public Ordenes() {
    }


    public Ordenes(Date fecha) {
        this.fecha = fecha;
    }

    public Ordenes(String codProveedor, float precioTotal) {
        this.codProveedor = codProveedor;
        this.precioTotal = precioTotal;
    }

    public Ordenes(String codProveedor, double sumaPrecio, int cantOrdenes, String nomProveedor) {
        this.codProveedor = codProveedor;
        this.sumaPrecio = sumaPrecio;
        this.cantOrdenes = cantOrdenes;
        this.nomProveedor = nomProveedor;
    }

    public void setNomProveedor(String nomProveedor) {
        this.nomProveedor = nomProveedor;
    }

    public String getNomProveedor() {
        return nomProveedor;
    }

    public void setCantOrdenes(int cantOrdenes) {
        this.cantOrdenes = cantOrdenes;
    }

    public int getCantOrdenes() {
        return cantOrdenes;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setCodProveedor(String codProveedor) {
        this.codProveedor = codProveedor;
    }

    public String getCodProveedor() {
        return codProveedor;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setSumaPrecio(float sumaPrecio) {
        this.sumaPrecio = sumaPrecio;
    }

    public double getSumaPrecio() {
        return sumaPrecio;
    }
}
