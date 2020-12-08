package JavaBeans;

public class Inventario {   

    String codArticulo;
    int minArt;
    int maxArt;
    int stock;
    
    String nomArticulo;
    String unidadMedida;
    


    public Inventario() {
    }


    public Inventario(String codArticulo, int minArt, int maxArt, int stock, String nomArticulo, String unidadMedida) {
        this.codArticulo = codArticulo;
        this.minArt = minArt;
        this.maxArt = maxArt;
        this.stock = stock;
        this.nomArticulo = nomArticulo;
        this.unidadMedida = unidadMedida;
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
