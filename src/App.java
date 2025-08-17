import java.util.Scanner;

import Model.Rol;
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
                                                                                          // defecto por ser le
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
                                            2. Actualizar Usuario
                                            3. Eliminar Usuario
                                            4. Desbloquear Usuario
                                            5. Cambiar Rol Usuario
                                            6. Cambiar clave
                                            7. Historial de acciones
                                            8. Salir
                                            """);
                                            var submenu = sc.nextInt();
                                            sc.nextLine();
                                            switch (submenu) {
                                                case 1:
                                                    sistemaManage.registerUser(sc);
                                                    break;
                                                    
                                                case 2:
                                                    
                                                    break;

                                                case 3:
                                                    
                                                    break;

                                                case 4:
                                                    
                                                    break;

                                                case 5:
                                                    
                                                    break;

                                                case 6:
                                                    
                                                    break;

                                                case 7:
                                                    usuarioLogin.showAction();

                                                    break;

                                                case 8:
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
