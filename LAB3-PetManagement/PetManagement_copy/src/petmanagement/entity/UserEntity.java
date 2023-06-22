package petmanagement.entity;

/**
 * User entity.
 *
 * @author hasu
 */
public class UserEntity implements PetStoreEntityInterface{

    private String password;
    private int role;
    protected String id;
    protected String name;
    protected String description;

    public UserEntity(String password, int role, String id, String name, String description) {
        this.password = password;
        this.role = role;
        this.id = id;
        this.name = name;
        this.description = description;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public UserEntity() {
        this.password = "";
        this.role = -1;
    }

}
