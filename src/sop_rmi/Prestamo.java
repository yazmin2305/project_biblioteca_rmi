package sop_rmi;

import java.io.Serializable;

public class Prestamo implements Serializable {
    private int codigo;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private int valorMulta;
    private Libro libroaPrestar;
    private Usuario usuarioAsociado;

    public Prestamo(){}
    public Prestamo(int codigo, String fechaPrestamo, String fechaDevolucion, int valorMulta) {
        this.codigo = codigo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.valorMulta = valorMulta;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFechaPrestamo() {
        return this.fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getValorMulta() {
        return this.valorMulta;
    }

    public void setValorMulta(int valorMulta) {
        this.valorMulta = valorMulta;
    }

    public Libro getLibroaPrestar() {
        return this.libroaPrestar;
    }

    public void setLibroaPrestar(Libro libroaPrestar) {
        this.libroaPrestar = libroaPrestar;
    }

    public Usuario getUsuarioAsociado() {
        return this.usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }


}