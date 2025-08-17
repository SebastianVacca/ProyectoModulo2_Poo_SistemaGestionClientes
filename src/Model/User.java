package Model;

public class User implements Show {
    private String id;
    private String name;
    private String userName;
    private String password;
    private Rol rol;
    private Actions[] actions;
    private Integer status;

    public User(String id, String name, String userName, String password) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.rol = Rol.ADMIN;
        this.actions = new Actions[50];
    }

    public User(String id, String name, String userName, String password, Rol rol) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.rol = rol;
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
    public Integer getStatus() {
        return status;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public void addAction(String description){
        for (int i = 0; i < actions.length; i++) {
            if (actions[i] == null) {
                actions[i] = new Actions(description);
                break;
            }
        }
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
