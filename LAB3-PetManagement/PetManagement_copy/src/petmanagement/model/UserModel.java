package petmanagement.model;

import petmanagement.main.ModelException;
import petmanagement.main.UserRole;



/**
 * User model.
 *
 * @author hasu
 */
public class UserModel implements PetStoreModelInterface{
    private String id;
    private String name;
    private String description;

    private String password;
    private UserRole role;
    public String getId() {
        return id;
    }

    public final void setId(String id) throws ModelException {
        if (!validateId(id)) {
            throw new ModelException(id + " does not match the pattern.");
        }
        this.id = id.trim().toUpperCase();
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) throws ModelException {
        if (name == null || name.isBlank()) {
            throw new ModelException("name is blank.");
        }
        this.name = name.trim();
    }

    public String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        if (description != null && !description.isBlank()) {
            this.description = description;
        } else {
            this.description = "";
        }
    }

    public UserModel(String id, String name, String description, String password, UserRole role) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.password = password;
        this.role = role;
    }

    

    public boolean isValid() {
        return validateId(this.id) && !this.name.isBlank();
    }



    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        if (password != null && !password.isBlank()) {
            this.password = password;
        } else {
            this.password = "";
        }
    }

    public UserRole getRole() {
        return role;
    }

    public final void setRole(UserRole role) {
        this.role = role;
    }

    public UserModel() {
        this.password = "";
        this.role = UserRole.INVALID;
    }

    public UserModel(String id, String password, UserRole role) throws ModelException {
        setId(id);
        setPassword(password);
        setRole(role);
    }

    public int compareTo(UserRole role) {
        return this.role.intValue() - role.intValue();
    }

    public boolean checkRole(UserRole role) {
        return compareTo(role) <= 0;
    }


    protected boolean validateId(String id) {
        return id != null && !id.isBlank();
    }

}
