package sop_rmi;

import java.io.Serializable;

public class Libro implements Serializable {
    private int codigo;
    private String nombre;
    private String areaConocimiento;
    private String autor;
    private String editorial;

    public Libro(){
    }
    public Libro(int codigo, String nombre, String areaConocimiento, String autor, String editorial) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.areaConocimiento = areaConocimiento;
        this.autor = autor;
        this.editorial = editorial;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAreaConocimiento() {
        return this.areaConocimiento;
    }

    public void setAreaConocimiento(String areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

}