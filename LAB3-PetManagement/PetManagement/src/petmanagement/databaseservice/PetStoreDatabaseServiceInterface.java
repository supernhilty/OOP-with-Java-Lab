package petmanagement.databaseservice;

import java.util.List;
import petmanagement.entity.PetStoreEntityInterface;

/**
 * Interface PetStoreDatabaseServiceInterface.
 *
 * @author hasu
 * @param <EntityClass>
 */
public interface PetStoreDatabaseServiceInterface<EntityClass extends PetStoreEntityInterface> {

    public List<EntityClass> loadEntitys(Class<EntityClass> clazz);

    public boolean saveEntitys(List<EntityClass> entityLis);

}
