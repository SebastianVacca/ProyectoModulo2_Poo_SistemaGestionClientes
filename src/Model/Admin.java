package Model;

public class Admin extends User{

    private User[] usuarios;

    
    public Admin(String id, String name, String userName, String password, Rol rol, User[] usuarios) {
        super(id, name, userName, password, rol);
        this.usuarios = usuarios;
    }

    public User[] getUsuarios() {
        return usuarios;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public Actions[] getActions() {
        return super.getActions();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public Rol getRol() {
        return super.getRol();
    }
    
    @Override
    public String getUserName() {
        return super.getUserName();
    }

    @Override
    public void showAction() {
        super.showAction();
    }

    // public void setStatusUsers(int status){
    //     if (super.getStatus() == 2) {
            
    //     }
    // }
    
}
