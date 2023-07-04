package ordermanagement.list;

import ordermanagement.model.User;

/**
 * 
 * @author leyen
 */
public class UserList extends ObjectList<User> {



    @Override
    protected User parseString(String stringObject) {
        User u = new User();
        u.parseString(stringObject);
        return u;
    }

    @Override
    public User filter(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
