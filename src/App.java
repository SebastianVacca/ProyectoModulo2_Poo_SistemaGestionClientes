import java.util.Scanner;

import Model.Rol;
import Model.Status;
import services.SystemManage;
import Model.User;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String id;
        String name;
        String userName;
        String password;
        int menu;

        SystemManage sistemaManage = new SystemManage();
        
            do {

            System.out.printf("""
                    ========= Bienvenido al sistema de gestión de Clientes =========
                    ======================== Menú principal ========================
                    1. Registrarse
                    2. Iniciar Sesión
                    3. Salir
                    Digite el número de opción...
                    """);
            menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                
                case 1:
                    
                    System.out.printf("""
                            ========= Bienvenido al sistema de gestión de Clientes =========

                            Tenga en cuenta que en la primer ejecución del sistema,  este no
                            tendrá ningún dato almacenado, y qué cuando finalice su ejeución
                            se borrará toda la data almacenada durante du ejecución.

                            El primer registro que se haga,  el sistema lo tomará como ADMIN
                            del sistema, luego  de  este registro  todos los usuarios que se
                            registren  serán  tomados como usuarios  básicos y sólo el ADMIN
                            podrá otorgar privilegios de ADMIN a quién haga la solicitud.

                            ¿Desea Registrarse?
                            1. Si
                            2. No
                            Digite el número de opción...
                            """);

                    var opcion = sc.nextInt();

                    if (opcion == 1) {
                        String confirmPass;

                        sc.nextLine();
                        System.out.println("=============== Iniciando registro en el sistema ===============");
                        System.out.print("Ingrese su documento de identidad: ");
                        id = sc.nextLine();
                        System.out.print("Ingrese su nombre completo: ");
                        name = sc.nextLine();
                        System.out.print("Digite un nombre de usuario: ");
                        userName = sc.nextLine();
                        do {
                            System.out
                                    .print("""
                                            Digite una contraseña, recuerde debe ser mínimo de 4 digitos y máximo 10 dígitos,
                                            recuerdela bien.
                                            """);
                            do {
                                System.out.print("Digite su clave: ");
                                password = sc.nextLine();
                                if (password.length() >= 4 && password.length() <= 10) {
                                    break;
                                } else {
                                    System.out.println("La contraseña no cumple con las condiciones requeridas");
                                }
                            } while (password.length() < 4 || password.length() > 10);

                            System.out.print("Por favor digite nuevamente su clave: ");
                            confirmPass = sc.nextLine();

                            if (confirmPass.equals(password)) {
                                System.out.println("Registro completado con éxito");
                                if (sistemaManage.getUsers()[0] == null) {
                                    User usuario = new User(id, name, userName, password);// Crea un usuario con rol
                                                                                          // administrador por
                                                                                          // defecto por ser el
                                                                                          // primer registro
                                    sistemaManage.addUser(usuario);// Agrega el usuario Administrador como un
                                                                   // usuario del sistema
                                } else {
                                    User usuario = new User(id, name, userName, password, Rol.GENERIC);
                                    sistemaManage.addUser(usuario);// Agrega el usuario como usuario básico al
                                                                   // sistema
                                }
                                break;
                            }
                            System.out.println("las claves no coinciden, intente de nuevo");
                        } while (!confirmPass.equals(password));

                    } else {
                        System.out.println("========= Puedes cerrar la consola, te espramos pronto =========");
                    }
                    break;

                case 2:
                    
                    System.out.println("======================== Logging ========================");
                   
                    do {
                        System.out.print("Digite su Id --> ");
                        id = sc.nextLine();
                    } while (id == null);
                    do {
                        System.out.print("Digite su clave --> ");
                        password = sc.nextLine();
                    } while (password == null);

                    var usuarioLogin = sistemaManage.login(id, password);
                    if (usuarioLogin != null) {
                        System.out.printf("Bienvenido %s su rol en el sistema es de: %s%n",
                                usuarioLogin.getName(), usuarioLogin.getRol());
                                usuarioLogin.addAction("Inicio sesión");
                                
                                if (usuarioLogin.getRol().equals(Rol.ADMIN)) {
                                    System.out.print("""
                                            ============ Menú de opciones para administrador ============

                                            1. Agregar Usuario
                                            2. Buscar Usuario
                                            3. Actualizar Usuario
                                            4. Eliminar Usuario
                                            5. Desbloquear Usuario
                                            6. Cambiar Rol Usuario
                                            7. Cambiar claves
                                            8. Historial de acciones
                                            9. Salir
                                            """);
                                            var submenu = sc.nextInt();
                                            sc.nextLine();
                                            switch (submenu) {                                              

                                                case 1:
                                                    sistemaManage.registerUser(sc);
                                                    break;
                                                    
                                                case 2:

                                                    String searchId;
                                                    User resultSearch;

                                                    System.out.println("""
                                                            ===================== Búsqueda de usuarios =====================
                                                            ================================================================

                                                            Digite el id:
                                                            """);
                                                            searchId = sc.nextLine();
                                                            resultSearch = sistemaManage.searchUser(searchId);

                                                            if (resultSearch != null) {
                                                                System.out.printf("""
                                                                        ====================== Resultado búsqueda ======================
                                                                        ================================================================
                                                                        Nombre: %s
                                                                        Nombre de usuario: %s
                                                                        Rol: %s                                                                        
                                                                        Estado: %s
                                                                        Acciones: %s
                                                                        """, resultSearch.getName(),
                                                                        resultSearch.getUserName(),
                                                                        resultSearch.getRol(),
                                                                        resultSearch.getStatus(),
                                                                        resultSearch.getActions() );
                                                            }
                                                    break;

                                                case 3:
                                                    System.out.println("""
                                                            ===================== Edición de usuarios =====================
                                                            ================================================================

                                                            Digite el id:
                                                            """);
                                                            searchId = sc.nextLine();
                                                            resultSearch = sistemaManage.searchUser(searchId);

                                                            if (resultSearch != null) {
                                                                System.out.printf("""
                                                                        ====================== Resultado búsqueda ======================
                                                                        ================================================================
                                                                        Nombre: %s
                                                                        Nombre de usuario: %s
                                                                        Rol: %s                                                                        
                                                                        Estado: %s
                                                                        Acciones: %s%n
                                                                        """, resultSearch.getName(),
                                                                        resultSearch.getUserName(),
                                                                        resultSearch.getRol(),
                                                                        resultSearch.getStatus(),
                                                                        resultSearch.getActions() );
                                                            }

                                                            System.out.printf("""
                                                                    ¿Cuál dato desea editar?
                                                                    1. Nombre
                                                                    2. Nombre de usuario
                                                                    3. Rol %s
                                                                    4. Estado %s%n
                                                                    0 para salir
                                                                    """);
                                                                    var optionMenuEdit = sc.nextInt();
                                                                    sc.nextLine();

                                                                    switch (optionMenuEdit) {
                                                                        case 1:
                                                                            System.out.print("Digite el Nombre: ");
                                                                            String newName = sc.nextLine();
                                                                            resultSearch.setName(newName);
                                                                            break;

                                                                        case 2:
                                                                            System.out.print("Digite el Nombre de usuario: ");
                                                                            String newUserName = sc.nextLine();
                                                                            resultSearch.setUserName(newUserName);
                                                                            
                                                                            break;

                                                                        case 3:
                                                                            System.out.printf("""
                                                                                    ===================== Configuración de rol ======================
                                                                                    =================================================================
                                                                                    
                                                                                    El usuario %s actualmente tiene rol %s

                                                                                    Por favor la opcion de rol con el que desea configurar al usuario
                                                                                    actual

                                                                                    1. Administrador ( privilegios absolutos sobre el sistema )
                                                                                    2. Usuario ( Mínimos privilegios en el sistema )%n
                                                                                    """, resultSearch.getName(), resultSearch.getRol());

                                                                            var newRol = sc.nextInt();
                                                                            if (newRol == 1) {
                                                                                resultSearch.setRol(Rol.ADMIN);
                                                                            } else {
                                                                                resultSearch.setRol(Rol.GENERIC);
                                                                            }
                                                                            System.out.printf("Usuario %s configurado con rol %s%n",
                                                                            resultSearch.getName(), resultSearch.getRol());                                                                                                                                                        
                                                                            break;

                                                                        case 4:
                                                                            if (resultSearch.getCountAttempts() > 2) {
                                                                                System.out.println("""
                                                                                        ====================== Estado del usario ======================
                                                                                        ===============================================================

                                                                                        El usuario actualmente esta en estado %s en el sistema.

                                                                                        Por favor elija el estado del estado usuario e el sistema,
                                                                                        recuerde que del estado depende el acceso al sistema

                                                                                        1. Activo
                                                                                        2. Inactivo
                                                                                        """);
                                                                                var optionMenuStatus = sc.nextInt();
                                                                                sc.nextLine();

                                                                                if (optionMenuStatus == 1) {
                                                                                    resultSearch.setStatus(Status.ENABLED);
                                                                                    System.out.printf(
                                                                                            "Usuario %s en estado %s",
                                                                                            resultSearch.getName(),
                                                                                            resultSearch.getStatus());
                                                                                } else if (optionMenuStatus == 2) {
                                                                                    resultSearch.setStatus(Status.DISABLED);
                                                                                    System.out.printf(
                                                                                            "Usuario %s en estado %s",
                                                                                            resultSearch.getName(),
                                                                                            resultSearch.getStatus());
                                                                                } else {
                                                                                    System.out.println("Opción inválida");
                                                                                }                                                                                    
                                                            
                                                                            }                                                                            
                                                                            break;

                                                                        case 0:
                                                                            System.out.println("saliendo.....");
                                                                            break;
                                                                    
                                                                        default:
                                                                            System.out.println("Opción inválida");
                                                                            break;
                                                                    }
                                                    
                                                    break;

                                                case 4:
                                                    sistemaManage.removeUser(id);
                                                    break;

                                                case 5:
                                                    
                                                    
                                                    break;

                                                case 6:
                                                    
                                                    break;

                                                case 7:
                                                    sistemaManage.UptadePasswordUsersAdmin(sc, id);
                                                    break;

                                                case 8:
                                                    usuarioLogin.showAction();

                                                    break;

                                                case 9:
                                                    System.out.println(".....: Cerrando sesión :.....");
                                                    break;
                                            
                                                default:
                                                System.err.println(".....: Opción iválida :.....");
                                                    break;
                                            }
                                } else{
                                    System.out.print("""
                                            ============== Menú de opciones para el usuario ==============

                                            1. Actualizar Usuario
                                            2. Cambiar clave
                                            5. Historial de acciones
                                            4. Salir
                                            """);
                                             var submenu = sc.nextInt();
                                            sc.nextLine();
                                            switch (submenu) {
                                                case 1:
                                                    
                                                    break;
                                                    
                                                case 2:
                                                    
                                                    break;

                                                case 3:
                                                    usuarioLogin.showAction();
                                                    break;

                                                case 4:
                                                    System.out.println(".....: Cerrando sesión :.....");
                                                    break;
                                            
                                                default:
                                                System.err.println(".....: Opción iválida :.....");
                                                    break;
                                            }

                                }
                    } else {
                        System.err.println("Credenciales inválidas");
                    }
                    break;

                case 3:
                    System.out.println("......: Saliendo del sistema :......");
                    break;

                default:
                    System.err.println("......: Opción inválida :......");
                    break;
                    }
                } while (menu != 3);


        sc.close();

        
    }

}
