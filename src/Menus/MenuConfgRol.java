package Menus;

import Model.Rol;
import Model.User;

public class MenuConfgRol {
    
    public void mostrarMenuConfRol(int newRol, User resultSearch) {
        boolean salir = false;

        do {
            switch (newRol) {
                case 1:
                    resultSearch.setRol(Rol.ADMIN);
                    break;

                case 2:
                    resultSearch.setRol(Rol.GENERIC);
                    break;

                case 0:
                    System.out.println("Se abortó la transacción, regresando al menú anterior\n");
                    break;

                default:
                    System.out.println("Opción inválida, escoja una de las opciones que se muestran en pantalla\n");
                    break;
            }
        } while (!salir);

        System.out.printf("Usuario %s configurado con rol %s%n",
                resultSearch.getName(), resultSearch.getRol());
    }

}
