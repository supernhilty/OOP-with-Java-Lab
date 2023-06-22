package petmanagement.view;

import java.util.List;
import petmanagement.model.PetStoreModelInterface;


/**
 * UniversityAbstrac.
 *
 * @param <ModelClass>
 */
public abstract class PetStoreAbstractView<ModelClass extends PetStoreModelInterface>
        implements PetStoreViewInterface<ModelClass> {

    public void showModel(ModelClass model) {
        System.out.println(toString(model));
    }

    public void showModel(List<ModelClass> modelList) {
        if (modelList != null) {
            System.out.println("Model list:");
            int idx = 0;
            for (ModelClass model : modelList) {
                System.out.print(++idx + ".");
                showModel(model);
            }
        }
    }

    public abstract String toString(ModelClass model);
}
