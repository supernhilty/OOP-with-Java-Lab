package petmanagement.service;

import java.util.List;
import petmanagement.databaseservice.UserDatabaseService;
import petmanagement.entity.UserEntity;
import petmanagement.main.ModelException;
import petmanagement.main.UserRole;
import petmanagement.model.UserModel;
import petmanagement.utils.Singleton;


/**
 *
 * @author hasu
 */
public class UserService extends PetStoreAbstractService<UserModel, UserEntity> {

    private UserModel currentUser;

    public UserModel getCurrentUser() {
        return currentUser;
    }

    private UserService() {
        this.dataManagementService = Singleton.getInstance(UserDatabaseService.class);
    }

    public boolean login(String id, String pass) {
        this.currentUser = validateUser(id, pass);
        return this.currentUser != null;
    }

    public boolean checkCurrentUserRole(UserRole role) {
        return this.currentUser.checkRole(role);
    }

    public UserRole getCurrentUserRole() {
        return this.currentUser.getRole();
    }
//    @Override
//    public void refresh() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }


    @Override
    public int loadModels() {
        return loadEntityFromDatabase(UserEntity.class);
    }

    @Override
    public boolean saveModels() {
        return saveEntitytoDatabase(UserEntity.class);
    }


    @Override
    protected UserEntity toEntity(UserModel model) {
        UserEntity entity = null;
        if (model != null) {
            entity = new UserEntity();
            entity.setId(model.getId());
            entity.setName(model.getName());
            entity.setDescription(model.getDescription());
            entity.setPassword(model.getPassword());
            entity.setRole(model.getRole().intValue());
        }
        return entity;
    }

    @Override
    protected UserModel toModel(UserEntity entity) {
        UserModel model = null;
        if (entity != null) {
            try {
                model = new UserModel();
                model.setId(entity.getId());
                model.setName(entity.getName());
                model.setDescription(entity.getDescription());
                model.setPassword(entity.getPassword());
                model.setRole(UserRole.valueOf(entity.getRole()));
            } catch (ModelException ex) {
                System.out.println(">>>>> Error: " + ex.getMessage());
//                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return model;
    }

    private UserModel validateUser(String id, String pasword) {
        List<UserEntity> entityList = this.dataManagementService.loadEntitys(UserEntity.class);
        for (UserEntity entity : entityList) {
            if (id.equals(entity.getId()) && pasword.equals(entity.getPassword())) {
                return toModel(entity);
            }
        }
        return null;
    }

}
