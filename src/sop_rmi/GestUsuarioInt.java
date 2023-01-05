package sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
//Hereda de la clase Remote, lo cual la convierte en interfaz remota

public interface GestUsuarioInt extends Remote {
    public boolean registrarUsuario(Usuario objU) throws RemoteException;

    public boolean registrarLibro(Libro objLib) throws RemoteException;

    public int calcularMulta(Libro objLib) throws RemoteException;

    public int abrirSesion(Credencial objCredencial) throws RemoteException;

    public Usuario consultarUsuario(int id) throws RemoteException;

    public ArrayList<Libro> buscarLibros(int opcion, String busqueda) throws RemoteException;
    public Libro buscarLibro(int codigo) throws RemoteException;

    public Prestamo consultarPrestamo(int codigo) throws RemoteException;

    public boolean realizarPrestamo(Prestamo objP) throws RemoteException;

    public boolean validarMultaUsuario(int id) throws RemoteException;
    public ArrayList<Libro> librosPrestados (int id) throws RemoteException;
}
