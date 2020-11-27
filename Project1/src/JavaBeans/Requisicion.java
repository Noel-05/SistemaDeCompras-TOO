package JavaBeans;

import java.util.Date;

public class Requisicion {
    private Date fechaPedidoReq;
    private Date fechaEntregaReq;
    private int autorizado;
    private int entregado;


    public Requisicion(Date fechaPedidoReq, Date fechaEntregaReq, int autorizado, int entregado) {
        this.fechaPedidoReq = fechaPedidoReq;
        this.fechaEntregaReq = fechaEntregaReq;
        this.autorizado = autorizado;
        this.entregado = entregado;
    }
    
    
    public Requisicion(Date fechaPedidoReq) {
        this.fechaPedidoReq = fechaPedidoReq;
    }


    public void setFechaPedidoReq(Date fechaPedidoReq) {
        this.fechaPedidoReq = fechaPedidoReq;
    }

    public Date getFechaPedidoReq() {
        return fechaPedidoReq;
    }

    public void setFechaEntregaReq(Date fechaEntregaReq) {
        this.fechaEntregaReq = fechaEntregaReq;
    }

    public Date getFechaEntregaReq() {
        return fechaEntregaReq;
    }

    public void setAutorizado(int autorizado) {
        this.autorizado = autorizado;
    }

    public int getAutorizado() {
        return autorizado;
    }

    public void setEntregado(int entregado) {
        this.entregado = entregado;
    }

    public int getEntregado() {
        return entregado;
    }
}
