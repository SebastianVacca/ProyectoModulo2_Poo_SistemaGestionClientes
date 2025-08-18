package Model;

public class User implements Show {
    private String id;
    private String name;
    private String userName;
    private String password;
    private Rol rol;
    private Actions[] actions;
    private Status status;
    private Integer countAttempts;

    public User(String id, String name, String userName, String password) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.rol = Rol.ADMIN;
        this.status = Status.ENABLED;
        this.actions = new Actions[50];
    }

    public User(String id, String name, String userName, String password, Rol rol) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.rol = rol;
        this.status = Status.ENABLED;
        this.actions = new Actions[50];
    }

    public String getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public Rol getRol() {
        return rol;
    }
    public String getUserName() {
        return userName;
    }
    public Actions[] getActions() {
        return actions;
    }
    public Status getStatus() {
        return status;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Método diseñado para insertar acciones en el Array actions[]
     * @param description
     */
    public void addAction(String description){
        for (int i = 0; i < actions.length; i++) {
            if (actions[i] == null) {
                actions[i] = new Actions(description);
                break;
            }
        }
    }
    /**
     * funcion para validar si la contraseña que se ingresa es igual 
     * a la que ya está almacenada
     * @param currentPass
     * @return booleano
     */
    public Boolean validateCurrentPass(String currentPass){
        if (getPassword().equals(currentPass)) {
            return true;
        }
        return false;
    }
    /**
     * Funcion con retorno de Booleano que infroam si la condicion de min y max 
     * se cumple
     * @param newPass
     * @return booleano
     */
    public Boolean validateConditionUpdatePass(String newPass){
        if (newPass.length() < 6 || newPass.length() >10) {
            return true;
        }
        return false;
    }
    /**
     * Funcion que valida si la confirmacion de la nueva contraeña es igual a 
     * la contraseña nueva anteriormente guardada
     * @param newPass
     * @param confirmNewPass
     * @return booleano
     */
    public Boolean validPassword(String newPass, String confirmNewPass){
        if (confirmNewPass.equals(newPass)) {
            this.password = newPass;
            return true;
        } 
        return false;
    }

    public Integer getCountAttempts() {
        return countAttempts;
    }

    public void setCountAttempts() {
    countAttempts++;
    }
    
    public void restartCountAttempts(){
        countAttempts = 0;
    }
    
    @Override
    public void showAction() {
        for (int i = 0; i < actions.length; i++) {
            Actions action = actions[i];
            if (action != null) {
                System.out.println(action);
            }
        }
        
    }


}
