package JavaBeans;

public class Proveedor {

    private String codigoProv;
    private String nomEmpresa;
    
    public Proveedor() {
    }


    public Proveedor(String codigoProv, String nomEmpresa) {
        this.codigoProv = codigoProv;
        this.nomEmpresa = nomEmpresa;
    }


    public Proveedor(String codigoProv) {
        this.codigoProv = codigoProv;
    }


    public void setCodigoProv(String codigoProv) {
        this.codigoProv = codigoProv;
    }

    public String getCodigoProv() {
        return codigoProv;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }


}
