package Model;

public class SystemManage {
    private User[] users;
    private Integer position;

    public SystemManage(){
        users = new User[100];
        position = 0;
    }

    public User[] getUsers() {
        return users;
    }

    public Integer getPosition() {
        return position;
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


}


