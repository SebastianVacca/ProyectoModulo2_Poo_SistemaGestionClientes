package Menus;

import Model.Status;
import Model.User;

public class MenuEstadoUser {

    public void mostrarMenuEstadoUsuario(int optionMenuStatus, User resultSearch){
        boolean salir = false;

        do {
            switch (optionMenuStatus) {
                case 1:
                    resultSearch.setStatus(Status.ENABLED);
                    System.out.printf(
                            "Usuario %s en estado %s",
                            resultSearch.getName(),
                            resultSearch.getStatus());
                    break;

                case 2:
                    resultSearch.setStatus(Status.DISABLED);
                    System.out.printf(
                            "Usuario %s en estado %s",
                            resultSearch.getName(),
                            resultSearch.getStatus());
                    break;

                case 0:
                    System.out.println("Regresando al menú anterior.....");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (!salir);
       
    }
    
}
