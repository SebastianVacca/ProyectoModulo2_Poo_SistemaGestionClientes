package services;

import java.util.Scanner;

import Model.Rol;
import Model.Status;
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

   

    public void addUser(User user){
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                
                return;
            }
            
        }
        System.out.println("El sistema no soporta más usuarios");
    }

    public void removeUser(String id, User currentUser){
        if (currentUser != null && currentUser.getId().equals(id)) {
            System.err.println("No se puede elminar su propio usuario");
            return;
        }

        Boolean search = false;

        for (int i = 0; i < users.length; i++) {            

            User user = users[i];

            if (user != null && user.getId().equals(id)) {
                users[i].getName();
                users[i] = null;
                search = true;
                System.out.println("Usuario eliminado");
                break;
            } 
        }

        if (!search) {
            System.err.println("Usuario no enontrado");
        }
    }

    public User login(String id, String password){
        
       for (int i = 0; i < users.length; i++) {
            User user = users[i];

            //if (user.getStatus().equals(Status.DISABLED)) {
                if (user != null && user.getId().equals(id) &&
                        user.getPassword().equals(password)) {
                    user.restartCountAttempts();
                    return user;
                }
            //    user.setCountAttempts();
            //}     
            //System.out.println("Su usuario se encuentra bloqueado, por favor contacte a su administrador de sistema"); 
            //user.addAction("Bloqueo de usuario por exceder intentos de inicio de sesión");      
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

    public String UptadePasswordUsersAdmin(Scanner sc, String id){
        for (int i = 0; i < users.length; i++) {
            User user = users[i];

            if(user != null && user.getId().equals(id)){
                System.out.print("Digite su contraseña actual: ");
                String currentPassword = sc.nextLine();

                if (user.getPassword().equals(currentPassword)) {
                    String newPass;
                    String confirmPass = "";
                    do {
                        System.out.print("Digite su nueva clave: ");
                        newPass = sc.nextLine();

                        if (!validateConditionUpdatePass(newPass)) {
                            System.out.println("La clave debe tener mínimo 4 caracteres y maximo 10");
                            continue;
                        }

                        System.out.print("Confirme su clave: ");
                        confirmPass = sc.nextLine();

                        if (!validateMatchPass(confirmPass, newPass)) {
                           System.out.println("Las claves deben coincidir, por favor intente de nuevo");
                        }
                    
                    } while (!validateMatchPass(confirmPass, newPass));

                        user.setPassword(newPass);

                } else {

                    System.out.println("Contraseña incorrecta");
                    return null;
                }
            } 
            
        }
        System.out.println("Usuario no encontrado");

        return null;
    }

    public void updateUser(){
        System.out.println("===================== Actualizacion de datos de usuarios =====================");
        System.out.println("");
    }

    public User searchUser(String id){
        for (int i = 0; i < users.length; i++) {

            User user = users[i];

            if (users[i] != null && id.equals(user.getId())) {
                return user;
            } 
        }
        System.out.println("Usuario no encontrado");
        return null;
    }

    public Boolean validateConditionUpdatePass(String newPass){
        if (newPass.length() < 6 || newPass.length() >10) {
            return true;
        }
        return false;
    }
    
    public Boolean validateMatchPass(String confirmPass ,String newPass){
        if (confirmPass.equals(newPass)) {
            return true;
        }
        return false;
    }
}


