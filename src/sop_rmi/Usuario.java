package sop_rmi;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String nombreCompleto;
    private String ocupacion;
    private Credencial objCred;

    public Usuario() {
    }

    public Usuario(int id, String nombreCompleto, String ocupacion) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.ocupacion = ocupacion;
        // this.objCred = objCred;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getOcupacion() {
        return this.ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Credencial getObjCred() {
        return this.objCred;
    }

    public void setObjCred(Credencial objCred) {
        this.objCred = objCred;
    }


}