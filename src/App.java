import java.util.Scanner;

import Menus.MenuConfgRol;
import Menus.MenuEstadoUser;
import Menus.MenuRegistro;
import Model.Rol;
// import Model.Status;
import services.SystemManage;
import Model.User;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        SystemManage systemManage = new SystemManage();
        MenuRegistro menuRegistro = new MenuRegistro();
        MenuEstadoUser menuEstadoUsuario = new MenuEstadoUser();
        MenuConfgRol menuConfgRol = new MenuConfgRol();

        String id = "";
        // String name;
        // String userName;
        String password = "";
        int menu;    
        
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

                    menuRegistro.mostrarMenuRegistro(opcion, systemManage);
                    
                    break;

                case 2:             

                    System.out.println("======================== Logging ========================");

                    User usuarioLogin = null;
                    do {
                        do {
                            System.out.print("Digite su Id --> ");
                            id = sc.nextLine();

                            if (id.isEmpty()) {
                                System.err.println("Es necesario el Id para poder continuar");
                            }

                        } while (id.isEmpty());

                        do {
                            System.out.print("Digite su clave --> ");
                            password = sc.nextLine();

                            if (password.isEmpty()) {
                                System.err.println("Es necesario la clave para poder continuar");
                            }

                        } while (password.isEmpty());

                        usuarioLogin = systemManage.login(id, password);

                        if (usuarioLogin == null) {
                            System.err.println("Credenciales inválidas\n");
                            break;
                        }

                    } while (usuarioLogin == null);                     
                    
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

                                            Digita una opción:

                                            """);
                                            var submenu = sc.nextInt();
                                            sc.nextLine();
                                            switch (submenu) {                                              

                                                case 1://agregar usuario
                                                    systemManage.registerUser(sc);
                                                    break;
                                                    
                                                case 2://Buscar usuario

                                                    String searchId;
                                                    User resultSearch;

                                                    System.out.println("""
                                                            ===================== Búsqueda de usuarios =====================
                                                            ================================================================

                                                            Digite el id:

                                                            """);
                                                            searchId = sc.nextLine();
                                                            resultSearch = systemManage.searchUser(searchId);

                                                            if (resultSearch != null) {
                                                                System.out.printf("""
                                                                        ====================== Resultado búsqueda ======================
                                                                        ================================================================
                                                                        Nombre: %s
                                                                        Nombre de usuario: %s
                                                                        Rol: %s                                                                        
                                                                        Estado: %s
                                                                        Acciones: %s

                                                                        Digite el id:

                                                                        """, resultSearch.getName(),
                                                                        resultSearch.getUserName(),
                                                                        resultSearch.getRol(),
                                                                        resultSearch.getStatus(),
                                                                        resultSearch.getActions() );
                                                            }
                                                    break;

                                                case 3://Actualizar usuario
                                                    System.out.println("""
                                                            ====================== Edición de usuarios =====================
                                                            ================================================================

                                                            Digite el id:

                                                            """);
                                                            searchId = sc.nextLine();
                                                            resultSearch = systemManage.searchUser(searchId);

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
                                                                    3. Rol 
                                                                    4. Estado 
                                                                    0 para salir

                                                                    Digita una opción:

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
                                                                                    0. Volver

                                                                                    Digita una opción:
                                                                                    """, resultSearch.getName(), resultSearch.getRol());

                                                                            var newRol = sc.nextInt();
                                                                            menuConfgRol.mostrarMenuConfRol(newRol, resultSearch);          
                                                                                                                                                                                                                        
                                                                            break;

                                                                        case 4:
                                                                             System.out.printf("""
                                                                                        ====================== Estado del usario ======================
                                                                                        ===============================================================

                                                                                        El usuario actualmente esta en estado %s en el sistema.

                                                                                        Por favor elija el estado del estado usuario e el sistema,
                                                                                        recuerde que del estado depende el acceso al sistema

                                                                                        1. Activo
                                                                                        2. Inactivo
                                                                                        0. Volver

                                                                                        Digita una opción:

                                                                                        """, resultSearch.getStatus());
                                                                                var optionMenuStatus = sc.nextInt();
                                                                                sc.nextLine();

                                                                                menuEstadoUsuario.mostrarMenuEstadoUsuario(optionMenuStatus, resultSearch);
                                                                                                                                                         
                                                                            break;

                                                                        case 0:
                                                                            System.out.println("saliendo.....");
                                                                            break;
                                                                    
                                                                        default:
                                                                            System.out.println("Opción inválida");
                                                                            break;
                                                                    }
                                                    
                                                    break;

                                                case 4://Eliminar usuario
                                                    systemManage.removeUser(id, usuarioLogin);
                                                    break;

                                                case 5://Desbloquear usuario
                                                    System.out.println("""
                                                            ====================== Desbloquear Usuario =====================
                                                            ================================================================

                                                            Digite el id:
                                                            
                                                            """);
                                                            searchId = sc.nextLine();
                                                            resultSearch = systemManage.searchUser(searchId);

                                                            System.out.printf(
                                                                    """
                                                                            ====================== Estado del usario ======================
                                                                            ===============================================================

                                                                            El usuario %s actualmente esta en estado %s en el sistema.

                                                                            Por favor elija el estado del estado usuario e el sistema,
                                                                            recuerde que del estado depende el acceso al sistema

                                                                            1. Activo
                                                                            2. Inactivo
                                                                            0. Volver

                                                                            Digita una opción:

                                                                            """, resultSearch.getName(), 
                                                                                resultSearch.getStatus());

                                                            var optionMenuStatus = sc.nextInt();
                                                            sc.nextLine();

                                                            menuEstadoUsuario.mostrarMenuEstadoUsuario(optionMenuStatus, resultSearch);
                                                    break;

                                                case 6://Cambiar rol
                                                    System.out.println("""
                                                            ===================== Consulta rol usuarios ====================
                                                            ================================================================

                                                            Digite el id:
                                                            """);
                                                    searchId = sc.nextLine();
                                                    resultSearch = systemManage.searchUser(searchId);

                                                    System.out.printf(
                                                            """                                    
                                                                    El usuario %s actualmente tiene rol %s

                                                                    Por favor ingrese la opcion de rol con el que desea configurar al usuario
                                                                    actual

                                                                    1. Administrador ( privilegios absolutos sobre el sistema )
                                                                    2. Usuario ( Mínimos privilegios en el sistema )%n
                                                                    0. Volver

                                                                    Digita una opción:

                                                                    """,
                                                            resultSearch.getName(), resultSearch.getRol());

                                                    var newRol = sc.nextInt();

                                                    menuConfgRol.mostrarMenuConfRol(newRol, resultSearch);
                                                    
                                                    break;

                                                case 7://Cambiar clave
                                                    systemManage.UptadePasswordUsersAdmin(sc, id);
                                                    break;

                                                case 8://Historial acciones
                                                    usuarioLogin.showAction();

                                                    break;

                                                case 9://Salida
                                                    System.out.println(".....: Cerrando sesión :.....");
                                                    break;
                                            
                                                default:
                                                System.err.println(".....: Opción iválida :.....");
                                                    break;
                                            }
                                } else{
                                    System.out.print("""
                                            ============== Menú de opciones para el usuario ==============

                                            1. Actualizar datos
                                            2. Cambiar clave
                                            3. Historial de acciones
                                            4. Salir

                                            Digita una opción:

                                            """);
                                             var submenu = sc.nextInt();
                                            sc.nextLine();
                                            switch (submenu) {
                                                case 1://Actualizar datos
                                                    Boolean exitMenu = false;
                                                    while (!exitMenu) {
                                                        int optionMenuUserEdit;

                                                        System.out.printf(
                                                                """
                                                                        ===================== Edición de su perfil =====================
                                                                        ================================================================

                                                                        Nombre: %s
                                                                        Nombre de usuario: %s
                                                                        Estado de usuario: %s

                                                                        ¿Desea actualizar algún dato?

                                                                        1. Nombre
                                                                        2. Nombre de usuario
                                                                        0. Menú anterior

                                                                        Digita una opción:

                                                                        """,
                                                                usuarioLogin.getName(),
                                                                usuarioLogin.getUserName(),
                                                                usuarioLogin.getStatus());

                                                        optionMenuUserEdit = sc.nextInt();
                                                        sc.nextLine();

                                                        switch (optionMenuUserEdit) {

                                                            case 1:
                                                                System.out.print("Actualice su nombre: ");
                                                                var newNameUser = sc.nextLine();
                                                                usuarioLogin.setName(newNameUser);

                                                                System.out.println("Cambio almacenado");
                                                                break;

                                                            case 2:
                                                                System.out.print("Actualice su nombre de usuario: ");
                                                                var newUserName = sc.nextLine();
                                                                usuarioLogin.setName(newUserName);

                                                                System.out.println("Cambio almacenado");
                                                                break;

                                                            case 0:
                                                                exitMenu = true;
                                                                break;

                                                            default:
                                                                System.err.println("Opción inválida");
                                                                break;
                                                            }                                                                                                  
                                                    }
                                                    break;
                                                    
                                                    
                                                case 2://Cambiar clave
                                                    String currentPass;
                                                    String newPass;
                                                    String confirmPass;
                                                    
                                                    do {
                                                        System.out.print("Digite su clave actual: ");
                                                        currentPass = sc.nextLine();
                                                    } while (!usuarioLogin.validateCurrentPass(currentPass));   

                                                    do {

                                                        do {
                                                            System.out.print("Digite su nueva clave: ");
                                                            newPass = sc.nextLine();

                                                            if (!usuarioLogin.validateConditionUpdatePass(newPass)) {
                                                                System.err.println("La clave debe tener minimo 4 caracteres y maximo 10");
                                                            }

                                                        } while (!usuarioLogin.validateConditionUpdatePass(newPass));                                                     
                                
                                                        System.out.print("Confirme su nueva clave: ");
                                                        confirmPass = sc.nextLine();   
                                                        
                                                        if (!usuarioLogin.validateConditionUpdatePass(newPass)) {
                                                            System.err.println("Las claves no coinciden, intente de nuevo");
                                                        }

                                                    } while (!usuarioLogin.validPassword(newPass, confirmPass));
                                                    
                                                    usuarioLogin.setPassword(newPass);
                                                    System.out.println("Contraseña actualizada con éxito");
                                                   
                                                    break;

                                                case 3://Historial de acciones
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
