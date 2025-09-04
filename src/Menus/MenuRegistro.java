package Menus;

import java.util.Scanner;

import Model.Rol;
import Model.User;
import services.SystemManage;

public class MenuRegistro {

    String id = "";
    String name;
    String userName;
    String password = "";
    int menu;
        
    
    private Scanner sc = new Scanner(System.in);
    
    public void mostrarMenuRegistro(int opcion, SystemManage systemManage ){

        boolean salir = false;

        do {

            switch (opcion) {

                case 1:
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

                            if (systemManage.getUsers()[0] == null) {
                                User usuario = new User(id, name, userName, password);// Crea un usuario con rol
                                                                                      // administrador por
                                                                                      // defecto por ser el
                                                                                      // primer registro
                                systemManage.addUser(usuario);// Agrega el usuario Administrador como un
                                                              // usuario del sistema
                            } else {
                                User usuario = new User(id, name, userName, password, Rol.GENERIC);
                                systemManage.addUser(usuario);// Agrega el usuario como usuario básico al
                                                              // sistema
                            }
                            salir = true;
                        } else {
                            System.out.println("las claves no coinciden, intente de nuevo");
                        }
                        
                    } while (!confirmPass.equals(password));

                    break;

                case 2:
                    System.out.println("================== Regresando al menú anterior ==================\n");
                    salir = true;
                    break;
            
                default:
                System.out.println("Opcióne inválida");
                    break;
            }

        } while (!salir);

        
    }
}
