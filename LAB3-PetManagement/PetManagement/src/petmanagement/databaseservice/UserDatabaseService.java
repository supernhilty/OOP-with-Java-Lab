package petmanagement.databaseservice;

import java.util.List;
import petmanagement.entity.UserEntity;


/**
 * UserDatabaseService.
 *
 * @author hasu
 */
public class UserDatabaseService extends PetStoreAbstractDatabaseService<UserEntity> {

    private static final int USER_ENTITY_ATTRIBUTE_COUNT = 5;

    private UserDatabaseService() {
        prepareDataFile(getEntityFilePath(UserEntity.class));
    }

    @Override
    public List<UserEntity> loadEntitys(Class<UserEntity> clazz) {
        return loadEntitysFromFile(getEntityFilePath(clazz));
    }

    @Override
    public boolean saveEntitys(List<UserEntity> entityLis) {
        if (entityLis != null && !entityLis.isEmpty()) {
            return saveEntitysToFile(getEntityFilePath(entityLis.get(0).getClass()), entityLis);
        }
        return false;
    }

    @Override
    protected String toString(UserEntity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append(entity.getId());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(entity.getName());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(entity.getPassword());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(entity.getRole());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(entity.getDescription());
        return sb.toString();
    }

    @Override
    protected UserEntity toEntity(String entityString) {
        UserEntity userEntity = null;
        if (entityString != null) {
            String[] attributes = entityString.split(PetStoreAbstractDatabaseService.SEPARATOR, -1);
            if (attributes.length >= UserDatabaseService.USER_ENTITY_ATTRIBUTE_COUNT) {
                userEntity = new UserEntity();
                userEntity.setId(attributes[0]);
                userEntity.setName(attributes[1]);
                userEntity.setPassword(attributes[2]);
                try {
                    userEntity.setRole(Integer.parseInt(attributes[3].trim()));
                } catch (NumberFormatException ex) {
                    userEntity.setRole(-1);
                    System.out.println(">>>>> Error: " + ex.getMessage());
//                    Logger.getLogger(UserDatabaseService.class.getName()).log(Level.SEVERE, null, ex);
                }
                userEntity.setDescription(attributes[4]);
            }
        }
        return userEntity;
    }
}
