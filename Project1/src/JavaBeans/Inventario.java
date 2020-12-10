package JavaBeans;

public class Inventario {   

    String idInv;
    String codArticulo;
    int minArt;
    int maxArt;
    int stock;
    
    String nomArticulo;
    String unidadMedida;
    String mensaje;
    


    public Inventario() {
    }


    public Inventario(String codArticulo, int minArt, int maxArt, int stock, String nomArticulo, String unidadMedida,
                      String mensaje) {
        this.codArticulo = codArticulo;
        this.minArt = minArt;
        this.maxArt = maxArt;
        this.stock = stock;
        this.nomArticulo = nomArticulo;
        this.unidadMedida = unidadMedida;
        this.mensaje = mensaje;
    }


    public Inventario(String idInv, String codArticulo, String nomArticulo) {
        this.idInv = idInv;
        this.codArticulo = codArticulo;
        this.nomArticulo = nomArticulo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setIdInv(String idInv) {
        this.idInv = idInv;
    }

    public String getIdInv() {
        return idInv;
    }

    public void setNomArticulo(String nomArticulo) {
        this.nomArticulo = nomArticulo;
    }

    public String getNomArticulo() {
        return nomArticulo;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getCodArticulo() {
        return codArticulo;
    }

    public void setMinArt(int minArt) {
        this.minArt = minArt;
    }

    public int getMinArt() {
        return minArt;
    }

    public void setMaxArt(int maxArt) {
        this.maxArt = maxArt;
    }

    public int getMaxArt() {
        return maxArt;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }
}
