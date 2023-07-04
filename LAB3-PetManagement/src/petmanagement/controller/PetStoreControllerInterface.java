package petmanagement.controller;

import java.util.List;
import petmanagement.model.PetStoreModelInterface;

/**
 * Interface UniversityControllerInterface.
 *
 * @author hasu
 * @param <ModelClass>
 */
public interface PetStoreControllerInterface<ModelClass extends PetStoreModelInterface> {

    public void loadModels();

    //public void refresh();

    public void showAll();

    public void showModel(ModelClass model);

    public ModelClass createNewModel();

    public ModelClass addNewModel();

    public ModelClass filterById(String id);

    //public List<ModelClass> filterByName(String name);
}
