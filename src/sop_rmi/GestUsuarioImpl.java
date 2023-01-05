package sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.filechooser.FileNameExtensionFilter;

public class GestUsuarioImpl extends UnicastRemoteObject implements GestUsuarioInt {
    private ArrayList<Usuario> lstUsuarios;
    private ArrayList<Libro> lstLibros;
    private ArrayList<Prestamo> lstPrestamos;

    public GestUsuarioImpl() throws RemoteException {
        super(); // Invoca al constructor de la clase base
        lstUsuarios = new ArrayList();
        lstLibros = new ArrayList<>();
        lstPrestamos = new ArrayList<>();
    }
    @Override
    public boolean registrarUsuario(Usuario objU) throws RemoteException {

        System.out.println("Entrando a registrar usuario");
        boolean bandera = false;

        if (lstUsuarios.size() < 4) {
            bandera = lstUsuarios.add(objU);
            System.out.println("Usuario registado con éxito");
            //System.out.println("Personal registrado:  id: " + objU.getId() + ", Nombre completo: " + objU.getNombreCompleto()
            //        + ", Ocupación: " + objU.getOcupacion() + ", Usuario: " + objU.getObjCred().getUsuario());
        } else {
            System.out.println("Cantidad maxima de registros alcanzados");
        }
        return bandera;
    }

    @Override
    public boolean registrarLibro(Libro objLib) throws RemoteException {
        System.out.println("Entrando a registrar libro");
        boolean bandera = false;

        if (lstLibros.size() < 4) {
            bandera = lstLibros.add(objLib);
            System.out.println("Libro registado con éxito");
        } else {
            System.out.println("Cantidad maxima de registros alcanzados");
        }
        return bandera;
    }

    @Override
    public int calcularMulta(Libro objLib) throws RemoteException {

        int result=0;
        Prestamo objP;
        if(lstPrestamos.size()>0){
            objP = consultaPrestamo(objLib);
            String fechaDevolucion = objP.getFechaDevolucion();
            String[] partesFechaDev = fechaDevolucion.split("/");

            Calendar fecha = new GregorianCalendar();

            int diaActual = fecha.get(Calendar.DAY_OF_MONTH);
            String mesActual = String.valueOf(fecha.get(Calendar.MONTH)+1);
            System.out.println("mes actual "+mesActual);

            int diaDevolucion = Integer.parseInt(partesFechaDev[0]);
            String mesDevolucion = partesFechaDev[1];
            System.out.println("mes devolucion "+mesDevolucion);
            int validarDia = 0;
            int resultado = 0;


            //Validando si el mes actual es igual al mes del prestamo
            if(mesDevolucion.equals(mesActual)){
                validarDia = diaActual - diaDevolucion;
                resultado = validacionMulta(validarDia);
                result = resultado;
                objP.setValorMulta(resultado);
            }else{

                if(mesDevolucion.equals("01") || mesDevolucion.equals("03") || mesDevolucion.equals("05") || mesDevolucion.equals("07") || mesDevolucion.equals("08") || mesDevolucion.equals("10") || mesDevolucion.equals("12")){
                    int dia = 31 - diaDevolucion;
                    System.out.println("Dia:"+ dia);
                    validarDia = diaActual + dia;
                    System.out.println("Dias multa: "+ validarDia);
                    resultado = validacionMulta(validarDia);
                    result = resultado;
                    objP.setValorMulta(resultado);

                }else if(mesDevolucion.equals("04") || mesDevolucion.equals("06") || mesDevolucion.equals("09") || mesDevolucion.equals("11")){
                    int dia = 30 - diaDevolucion;
                    System.out.println("Dia: "+ dia);
                    validarDia = diaActual + dia;
                    System.out.println("Dias multa:"+ validarDia);
                    resultado = validacionMulta(validarDia);
                    result = resultado;
                    objP.setValorMulta(resultado);

                }else if(mesDevolucion.equals("02")){
                    int dia = 28 - diaDevolucion;
                    System.out.println("Dia: "+ dia);
                    validarDia = diaActual + dia;
                    System.out.println("Dias multa:"+ validarDia);
                    resultado = validacionMulta(validarDia);
                    result = resultado;
                    objP.setValorMulta(resultado);
                }
            }
        }else{
            System.out.println("No existen prestamos registrados en el sistema\n");
        }
        return result;
    }

    @Override
    public int abrirSesion(Credencial objCredencial) throws RemoteException {
        int  result;

        if((objCredencial.getUsuario().equals("admin")) && (objCredencial.getClave().equals("1234"))){
            result = 1;
        }else{
            result = -1;
        }

        //Validación del usuario
        if(lstUsuarios.size()>0 && result!=1){
            for(int i=0; i<lstUsuarios.size(); i++){
                if((lstUsuarios.get(i).getObjCred().getUsuario().equals(objCredencial.getUsuario())) && (lstUsuarios.get(i).getObjCred().getClave().equals(objCredencial.getClave()))){
                    // printf("personal encontrado");
                    result = 2;
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public Usuario consultarUsuario(int id) throws RemoteException {
        System.out.println("Entrando a consultar usuario");
        Usuario objUsuario= null;

        for (int i = 0; i < this.lstUsuarios.size(); i++) {
            if (this.lstUsuarios.get(i).getId() == id) {
                objUsuario = this.lstUsuarios.get(i);
                System.out.println("Usuario encontrado!!");
                break;
            }else{
                System.out.println("No se encontró el Usuario");
            }
        }
        return objUsuario;
    }

    @Override
    public ArrayList<Libro> buscarLibros(int opcion, String busqueda) throws RemoteException {
        System.out.println("Entrando a buscar libros");
        ArrayList<Libro> lstlibrosB = new ArrayList<>();
        Libro objLibro = null;

        for (int i = 0; i < this.lstLibros.size(); i++) {
            if(opcion == 1){
                if (this.lstLibros.get(i).getEditorial().equals(busqueda)) {
                    lstlibrosB.add(lstLibros.get(i));
                    System.out.println("Libros encontrados por editorial!!");
                }else{
                    System.out.println("No se encontraron libros registrados con la editorial "+ busqueda);
                }
            }else if(opcion == 2){
                if (this.lstLibros.get(i).getAreaConocimiento().equals(busqueda)) {
                    lstlibrosB.add(lstLibros.get(i));
                    System.out.println("Libros encontrados por area de conocimiento!!");
                }else{
                    System.out.println("No se encontraron libros registrados con el área de conocimiento "+ busqueda);
                }

            }else if(opcion == 3){
                if (this.lstLibros.get(i).getAutor().equals(busqueda)) {
                    lstlibrosB.add(lstLibros.get(i));
                    System.out.println("Libros encontrados por autor!!");
                }else{
                    System.out.println("No se encontraron libros registrados con el autor "+ busqueda);
                }
            }else{
                System.out.println("Se digitó una opción de búsqueda incorrecta");
            }
        }
        return lstlibrosB;
    }

    @Override
    public Libro buscarLibro(int codigo) throws RemoteException {
        System.out.println("Entrando a buscar libro");
        Libro objLibro = null;

        for (int i = 0; i < this.lstLibros.size(); i++) {
            if (this.lstLibros.get(i).getCodigo() == codigo) {
                objLibro = this.lstLibros.get(i);
                System.out.println("Libro encontrado!!");
                break;
            }else{
                System.out.println("No se encontró el Libro");
            }
        }
        return objLibro;
    }

    @Override
    public Prestamo consultarPrestamo(int codigo) throws RemoteException {
        System.out.println("Entrando a consultar prestamo");
        Prestamo objPrestamo = null;

        for (int i = 0; i < this.lstPrestamos.size(); i++) {
            if (this.lstPrestamos.get(i).getCodigo() == codigo) {
                objPrestamo = this.lstPrestamos.get(i);
                System.out.println("Préstamo encontrado!!");
                break;
            }else{
                System.out.println("No se encontró el Préstamo");
            }
        }
        return objPrestamo;
    }

    @Override
    public boolean realizarPrestamo(Prestamo objP) throws RemoteException {

        boolean result = false;

        System.out.println("Invocando registrar Prestamo\n");
        if(lstUsuarios.size() < 4){
            lstPrestamos.add(objP);
            result = true;
            System.out.println("Registrando Prestamo\n");
        }
        else{
            System.out.println("Cantidad máxima de registros de prestamos alcanzados\n");
        }
        return result;
    }

    @Override
    public boolean validarMultaUsuario(int id) throws RemoteException {
        boolean bandera = true;
        if(lstPrestamos.size()>0){
            for (int i=0 ; i<lstPrestamos.size(); i++){
                if (lstPrestamos.get(i).getUsuarioAsociado().getId() == id){
                    int valorMulta = lstPrestamos.get(i).getValorMulta();
                    if(valorMulta > 20000){
                        bandera = false;
                    }
                }
            }
        }
        return bandera;
    }

    @Override
    public ArrayList<Libro> librosPrestados(int id) throws RemoteException {
        ArrayList<Libro> lstLibros = new ArrayList<>();
        if (lstPrestamos.size() > 0) {
            for (int i=0 ; i<lstPrestamos.size(); i++){
                if (lstPrestamos.get(i).getUsuarioAsociado().getId() == id){
                    lstLibros.add(lstPrestamos.get(i).getLibroaPrestar());

                }
            }
            return lstLibros;
        }
        return null;
    }

    public Prestamo consultaPrestamo(Libro objLib){
        Prestamo objP = new Prestamo();

        if(lstPrestamos.size()>0){
            for (int i=0 ; i<lstPrestamos.size(); i++){
                if (lstPrestamos.get(i).getLibroaPrestar().getCodigo() == objLib.getCodigo()){
                    objP = lstPrestamos.get(i);
                    System.out.println("Fecha devolucion"+objP.getFechaDevolucion());
                    break;
                }
            }
        }

        return objP;
    }

    int validacionMulta(int validarDia){
        int multa=0;
        if((validarDia>=1) && (validarDia<=3)){
            System.out.println("multa 1 a 3 dias");
            multa = 10000;
            System.out.println("El usuario debe pagar: "+multa);

        }else if((validarDia>=4) && (validarDia<=8)){
            int dias = validarDia - 3;
            multa = 10000+(1000*dias);
            System.out.println("El usuario debe pagar: "+multa);
        }else if(validarDia>8){
            int dias = validarDia - 3;
            multa = 10000+(2000*dias);
            System.out.println("El usuario debe pagar: "+multa);
        }else{
            System.out.println("El usuario no tiene multas\n");
        }
        return multa;
    }
}
