import java.util.Scanner;

import Model.User;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String id;
        String name;
        String userName;
        String password;
        
        System.out.printf("""
        ========= Bienvenido al sistema de gestión de Clientes ========= 
        
        Tenga en cuenta que en la primer ejecución del sistema,  este no
        tendrá ningún dato almacenado, y que cuando finalice su ejeución 
        se borrará toda la data almacenada durante du ejecución.

        El primer registro que se haga,  el sistema lo tomará como ADMIN 
        del sistema, luego de este registro el todos los usuarios que se
        registren  serán  tomados c omo usuarios básicos y sólo el ADMIN 
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
                System.out.print("""
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
                        User usuario = new User(id, name, userName, password);
                        
                        break;
                    } 
                    System.out.println("las claves no coinciden, intente de nuevo");
            } while (!confirmPass.equals(password));

        } else{
            System.out.println("========= Puedes cerrar la consola, te espramos pronto =========");
        }


        sc.close();
    }
}
