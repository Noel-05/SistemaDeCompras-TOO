package JavaBeans;

public class CostoPromedio {

    int idCostoPromedio;//Autoincrementable no insertar en los constructores
    int idMovimiento;//el identificador de los productos de inventario
    int idInventario;
    int cantidadCP;
    double valorUnidadCP;
    double valorTotalCP;

    String codArticulo;                        
    String nomArticulo;                        
    String unidadArticulo;  

    public CostoPromedio() {
    }


    public CostoPromedio(int idInventario, int cantidadCP, double valorUnidadCP, double valorTotalCP,
                         String codArticulo, String nomArticulo, String unidadArticulo) {
        this.idInventario = idInventario;
        this.cantidadCP = cantidadCP;
        this.valorUnidadCP = valorUnidadCP;
        this.valorTotalCP = valorTotalCP;
        this.codArticulo = codArticulo;
        this.nomArticulo = nomArticulo;
        this.unidadArticulo = unidadArticulo;
    }


    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getCodArticulo() {
        return codArticulo;
    }

    public void setNomArticulo(String nomArticulo) {
        this.nomArticulo = nomArticulo;
    }

    public String getNomArticulo() {
        return nomArticulo;
    }

    public void setUnidadArticulo(String unidadArticulo) {
        this.unidadArticulo = unidadArticulo;
    }

    public String getUnidadArticulo() {
        return unidadArticulo;
    }

    public void setIdCostoPromedio(int idCostoPromedio) {
        this.idCostoPromedio = idCostoPromedio;
    }

    public int getIdCostoPromedio() {
        return idCostoPromedio;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setCantidadCP(int cantidadCP) {
        this.cantidadCP = cantidadCP;
    }

    public int getCantidadCP() {
        return cantidadCP;
    }

    public void setValorUnidadCP(double valorUnidadCP) {
        this.valorUnidadCP = valorUnidadCP;
    }

    public double getValorUnidadCP() {
        return valorUnidadCP;
    }

    public void setValorTotalCP(double valorTotalCP) {
        this.valorTotalCP = valorTotalCP;
    }

    public double getValorTotalCP() {
        return valorTotalCP;
    }
}
