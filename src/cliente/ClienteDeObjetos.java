package cliente;

import cliente.utilidades.UtilidadesRegistroC;
import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import sop_rmi.GestUsuarioInt;
import sop_rmi.Credencial;
import sop_rmi.Libro;
import sop_rmi.Usuario;
import sop_rmi.Prestamo;
import java.util.ArrayList;

public class ClienteDeObjetos {

    private static GestUsuarioInt objRemoto;

    public static void main(String[] args) {
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
        numPuertoRMIRegistry = cliente.utilidades.UtilidadesConsola.leerEntero();

        objRemoto = (GestUsuarioInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry,
                "gestionPersonal");
        MenuPrincipal();
    }

    private static void MenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("==Menu Inicio==");
            System.out.println("1. Abrir sesión");
            System.out.println("2. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    Opcion1();
                    break;
                case 2:
                    System.out.println("Salir...");                    
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 2);
    }

    private static void Opcion1() {
        try {
            System.out.println("Ingrese el usuario: ");
            String usuario = UtilidadesConsola.leerCadena();
            System.out.println("Ingrese la contraseña: ");
            String clave = UtilidadesConsola.leerCadena();
            //PersonalDTO objPersonal = new PersonalDTO(tipo_id, id, nombreCompleto, ocupacion, usuario, clave);
            Credencial objCredencial = new Credencial(usuario, clave);
            int resultadoSesion = objRemoto.abrirSesion(objCredencial);
            if(resultadoSesion==1){
                int opcion1=0;
                do
                {
                    System.out.println("==== Menú Admin ====");
                    System.out.println("* 1-Registrar usuario *");
                    System.out.println("* 2-Registrar libro *");
                    System.out.println("* 3-Consultar usuario *");
                    System.out.println("* 4-Consultar prestamo *");
                    System.out.println("* 5-Devolucion libro *");
                    System.out.println("* 6-Salir *");
                    System.out.println("======================\n");
                    System.out.println("Elija la opcion: ");
                    opcion1 = UtilidadesConsola.leerEntero();

                    switch (opcion1)
                    {
                        case 1:
                            Usuario objU = new Usuario();
                            Credencial objC = new Credencial();
                            System.out.println("REGISTRAR USUARIOS");
                            System.out.println("Ingrese los nombres:");
                            objU.setNombreCompleto(UtilidadesConsola.leerCadena());

                            System.out.println("Ingrese la ocupación:");
                            objU.setOcupacion(UtilidadesConsola.leerCadena());

                            System.out.println("Ingrese la identificación:");
                            objU.setId(UtilidadesConsola.leerEntero());
                            objU.setObjCred(objC);
                            System.out.println("Ingrese el usuario: ");
                            objU.getObjCred().setUsuario(UtilidadesConsola.leerCadena());

                            System.out.println("Ingrese la clave: ");
                            objU.getObjCred().setClave(UtilidadesConsola.leerCadena());

                            boolean verificarRegistro = objRemoto.registrarUsuario(objU);
                            if(verificarRegistro){
                                System.out.println("Usuario registrado con éxito");
                            }else{
                                System.out.println("Usuario no registrado");
                            }
                        break;

                        // opcion para registrar los libros
                        case 2:
                            Libro objLib = new Libro();
                            System.out.println("REGISTRAR LIBROS");
                            System.out.println("Ingrese el nombre del libro: ");
                            objLib.setNombre(UtilidadesConsola.leerCadena());

                            System.out.println("Ingrese el autor:");
                            objLib.setAutor(UtilidadesConsola.leerCadena());

                            System.out.println("Ingrese el area de conocimiento: ");
                            objLib.setAreaConocimiento(UtilidadesConsola.leerCadena());

                            System.out.println("Ingrese la editorial ");
                            objLib.setEditorial(UtilidadesConsola.leerCadena());

                            System.out.println("Ingrese el código: ");
                            objLib.setCodigo(UtilidadesConsola.leerEntero());

                            boolean verificarRegistroLib = objRemoto.registrarLibro(objLib);
                            if(verificarRegistroLib){
                                System.out.println("Libro registrado con éxito");
                            }else{
                                System.out.println("Libro no registrado");
                            }
                        break;

                        // Opcion para consultar usuarios
                        case 3:
                            System.out.println("CONSULTAR USUARIOS");
                            System.out.println("Digite la identificación del usuario: ");
                            int id = UtilidadesConsola.leerEntero();
                            Usuario objUsu = objRemoto.consultarUsuario(id);

                            if ((objUsu != null) && (objUsu.getId() == id))
                            {
                                System.out.println("Nombre: "+ objUsu.getNombreCompleto());
                                System.out.println("Identificación: "+objUsu.getId ());
                                System.out.println("Usuario:"+objUsu.getObjCred ().getUsuario());
                                System.out.println("Ocupación: "+objUsu.getOcupacion ());
                                System.out.println("\n");
                            }
                            else
                            {
                                System.out.println("Usuario NO encontrado \n");
                            }
                        break;
                        //Opcion para consultar un prestamo
                        case 4:
                            System.out.println("CONSULTAR PRESTAMO");
                            System.out.println("Digite el codigo del prestamo: ");
                            int codigo = UtilidadesConsola.leerEntero();
                            Prestamo prestamoConsult = objRemoto.consultarPrestamo(codigo);

                            if (prestamoConsult.getCodigo() == codigo){
                                System.out.println("Codigo del prestamo:  "+ prestamoConsult.getCodigo());
                                System.out.println("Nombre usuario: "+ prestamoConsult.getUsuarioAsociado().getNombreCompleto());
                                System.out.println("Identificacion usuario:"+ prestamoConsult.getUsuarioAsociado().getId());
                                System.out.println("Nombre del libro:"+prestamoConsult.getLibroaPrestar().getNombre());
                                System.out.println("Codigo del libro:"+ prestamoConsult.getLibroaPrestar().getCodigo());
                                System.out.println("Fecha de prestamo: "+ prestamoConsult.getFechaPrestamo());
                                System.out.println("Fecha de devolucion: "+ prestamoConsult.getFechaDevolucion());
                                System.out.println("\n");
                            }
                            else{
                                System.out.println("Prestamo NO encontrado \n");
                            }
                        break;
                        case 5:
                            System.out.println("DEVOLUCION LIBRO");
                            Prestamo objP;
                            System.out.println("Digite el codigo del prestamo: ");
                            int codPrestamo = UtilidadesConsola.leerEntero();
                            objP = objRemoto.consultarPrestamo(codPrestamo);

                            if (objP != null && objP.getCodigo() == codPrestamo)
                            {
                                Libro objL = objP.getLibroaPrestar();
                                int resultado = objRemoto.calcularMulta(objL);

                                if(resultado != 0){
                                    System.out.println("RECIBO DE PAGO");
                                    System.out.println("Nombre del usuario: "+objP.getUsuarioAsociado().getNombreCompleto());
                                    System.out.println("Nombre del libro: "+ objP.getLibroaPrestar().getNombre());
                                    System.out.println("El valor de la multa es: "+resultado);
                                }else{
                                    System.out.println("El usuario no tiene multas, puede hacerse la devolución del libro sin costo\n");
                                }

                            }else{
                                System.out.println("El libro no se encuentra registrado");
                            }
                        break;
                        // opcion para salir de la aplicacion
                        case 6:
                            System.out.println("Exit....\n");
                            break;
                    }
                } while (opcion1 != 6);
            }else if (resultadoSesion == 2){
                int opcion2 = 0;
                do
                {
                    System.out.println("==== Menú Usuario ====");
                    System.out.println("* 1-Consultar libros *");
                    System.out.println("* 2-Solicitar el préstamo de un libro *");
                    System.out.println("* 3-Salir *");
                    System.out.println("======================\n");
                    System.out.println("Elija la opcion: ");
                    opcion2 = UtilidadesConsola.leerEntero();

                    switch (opcion2)
                    {
                        case 1:
                            // Opcion para consultar libros
                            System.out.println("CONSULTAR LIBROS");
                            System.out.println("Ingrese la opcion dependiendo de la consulta que desea realizar");
                            System.out.println("opcion1 = editorial, opcion2 = área de conocimiento, opcion3 = autor");
                            int opcion = UtilidadesConsola.leerEntero();
                            String busqueda = "";
                            if(opcion == 1){
                                System.out.println("Ingrese el nombre de la editorial: ");
                                busqueda = UtilidadesConsola.leerCadena();
                            }else if(opcion == 2){
                                System.out.println("Ingrese el nombre del area de conocimiento: ");
                                busqueda = UtilidadesConsola.leerCadena();
                            }else if(opcion == 3){
                                System.out.println("Ingrese el nombre del autor: ");
                                busqueda = UtilidadesConsola.leerCadena();
                            }

                            ArrayList<Libro> lstLibrosB = objRemoto.buscarLibros(opcion, busqueda);

                            if (lstLibrosB != null ){
                                for(int i=0; i<lstLibrosB.size(); i++){
                                    System.out.println("INFORMACION DEL LIBRO: ");
                                    System.out.println("Nombre del libro: "+lstLibrosB.get(i).getNombre());
                                    System.out.println("Código del libro: "+lstLibrosB.get(i).getCodigo());
                                    System.out.println("Autor: "+lstLibrosB.get(i).getAutor ());
                                    System.out.println("Area de conocimiento: "+lstLibrosB.get(i).getAreaConocimiento());
                                    System.out.println("Editorial: "+lstLibrosB.get(i).getEditorial());
                                    System.out.println("\n");
                                }

                            }
                            else{
                                System.out.println("Libros no encontrados a partir del tipo de busqueda realizada \n");
                            }
                            break;

                        case 2:
                            System.out.println("SOLICITAR PRESTAMO ");
                            int idLibro = 0;
                            Libro objLibPrestado = null;
                            System.out.println("Digite la identificación del usuario: ");
                            int idUsu = UtilidadesConsola.leerEntero();
                            Usuario objUsu = objRemoto.consultarUsuario(idUsu);

                            if (objUsu.getId() == idUsu){
                                System.out.println("Digite el id del libro: ");
                                idLibro = UtilidadesConsola.leerEntero();
                                objLibPrestado = objRemoto.buscarLibro(idLibro);

                                ArrayList<Libro> lstLibros = objRemoto.librosPrestados(idUsu);
                                if(lstLibros != null){
                                    for (int i = 0; i < lstLibros.size(); i++) {
                                        objRemoto.calcularMulta(lstLibros.get(i));
                                    }
                                }

                                if(objRemoto.validarMultaUsuario(idUsu)){
                                    if ((objLibPrestado != null) && (objLibPrestado.getCodigo() == idLibro)){
                                        Prestamo objP = new Prestamo();
                                        objP.setLibroaPrestar(objLibPrestado);
                                        objP.setUsuarioAsociado(objUsu);
                                        System.out.println("Ingrese el código del prestamo: ");
                                        objP.setCodigo(UtilidadesConsola.leerEntero());
                                        System.out.println("Ingrese la fecha del prestamo: ");
                                        objP.setFechaPrestamo(UtilidadesConsola.leerCadena());
                                        System.out.println("Ingrese la fecha de devolucion del libro: ");
                                        objP.setFechaDevolucion(UtilidadesConsola.leerCadena());
                                        objP.setValorMulta(0);
                                        System.out.println("\n");
                                        boolean resultadoPrestamo = objRemoto.realizarPrestamo(objP);

                                        if (resultadoPrestamo == true)
                                        {
                                            System.out.println("Prestamo registrado Exitosamente \n");
                                        }
                                        else
                                        {
                                            System.out.println("Prestamo NO registrado\n");
                                        }
                                    }else{
                                        System.out.println("Libro NO encontrado \n");
                                    }
                                }else{
                                    System.out.println("El usuario no puede solicitar prestamo ya que tiene una multa mayor a 20000 \n");
                                }
                            }else{
                                System.out.println("Usuario NO encontrado \n");
                            }
                            break;

                        case 3:
                            System.out.println("Exit....\n");
                            break;
                    }
                } while (opcion2 != 3);
            }else
            {
                System.out.println("No se ingresaron correctamente los datos. Verificar la información\n");
            }

        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...\n");
        }
    }

}
