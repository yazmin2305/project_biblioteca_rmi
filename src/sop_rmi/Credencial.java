package sop_rmi;

import java.io.Serializable;

public class Credencial implements Serializable {
    private String usuario;
    private String clave;

    public Credencial(){}
    public Credencial(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}