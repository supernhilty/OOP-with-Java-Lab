package ordermanagement.services;
import java.util.ArrayList;
import java.util.List;
import ordermanagement.main.UserRole;
import ordermanagement.model.User;
import ordermanagement.utils.Util;

/**
 * 
 * @author leyen
 */
public class UserManagement {

    private static final UserManagement instance = new UserManagement();

    private User currentUser;

    public static UserManagement getInstance() {
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<User> getUserList() {
//        ////////////////////////////////////
//        // stub
        List<User> userList = new ArrayList();
        userList.add(new User("admin", "123", UserRole.ADMIN));
        userList.add(new User("user1", "123", UserRole.USER));
        userList.add(new User("user2", "123", UserRole.USER));
//        ////////////////////////////////////


        return userList;
    }

    private UserManagement() {
        currentUser = null;
    }

    public boolean login() {
        System.out.println("Login ...");
        String name = Util.inputString("User name");
        String pass = Util.inputString("Password");
        this.currentUser = validate(name, pass);
        return this.currentUser != null;
    }

    private User validate(String name, String pass) {
        if (name != null && pass != null) {
            List<User> userList = UserManagement.getInstance().getUserList();
            if (userList != null) {
                for (User user : userList) {
                    if (name.equals(user.getId()) && pass.equals(user.getPass())) {
                        return user;
                    }
                }
            }
        }
        return null;
    }
}
