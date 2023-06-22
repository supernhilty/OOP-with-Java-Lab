package petmanagement.service;

import java.util.List;
import petmanagement.model.PetStoreModelInterface;


/**
 * PetStoreServiceInterface.
 *
 * @author hasu
 * @param <ModelClass>
 */
public interface PetStoreServiceInterface<ModelClass extends PetStoreModelInterface> {

    public abstract int loadModels();

    public abstract boolean saveModels();

    public abstract void refresh();

    public boolean add(ModelClass object);

    public ModelClass update(ModelClass object);

    public ModelClass delete(ModelClass object);

    public List<ModelClass> getModelList();

    public ModelClass filterById(String id);

    public List<ModelClass> filterByName(String name);

}
