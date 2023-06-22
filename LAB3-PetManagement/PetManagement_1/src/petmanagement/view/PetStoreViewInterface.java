package petmanagement.view;

import java.util.List;
import petmanagement.model.PetStoreModelInterface;


/**
 * UniversityViewInterface.
 * @author hasu
 * @param <ModelClass>
 */
public interface PetStoreViewInterface<ModelClass extends PetStoreModelInterface> {

    public void showModel(ModelClass model);

    public void showModel(List<ModelClass> modelList);
}
