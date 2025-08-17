package services;

import java.util.Scanner;

import Model.Rol;
import Model.User;

public class SystemManage {
    private User[] users;
    // private Integer position;

    public SystemManage(){
        users = new User[100];
        // position = 0;
    }

    public User[] getUsers() {
        return users;
    }

    // public Integer getPosition() {
    //     return position;
    // }

    public void addUser(User user){
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                
                return;
            }
            
        }
        System.out.println("El sistema no soporta más usuarios");
    }

    public User login(String id, String password){
       for (int i = 0; i < users.length; i++) {
            User user = users[i];

            if (user != null && user.getId().equals(id) && user.getPassword().equals(password))  {
                return user;
            }
       }
       return null;
    }

    public void registerUser(Scanner sc){

        String id;
        String name;
        String userName;
        String password;
        // int menu;

        SystemManage sistemaManage = new SystemManage();

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

    }


}


