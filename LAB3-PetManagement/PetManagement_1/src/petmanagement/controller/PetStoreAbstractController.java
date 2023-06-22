package petmanagement.controller;

import java.util.List;
import petmanagement.service.PetStoreServiceInterface;
import petmanagement.model.PetStoreModelInterface;
import petmanagement.view.PetStoreViewInterface;


/**
 * Abstract class PetStoreAbstractController.
 *
 * @author hasu
 * @param <ServiceClass>
 * @param <ViewClass>
 * @param <ModelClass>
 */
public abstract class PetStoreAbstractController<
        ServiceClass extends PetStoreServiceInterface<ModelClass>, ViewClass extends PetStoreViewInterface<ModelClass>, ModelClass extends PetStoreModelInterface>
        implements PetStoreControllerInterface<ModelClass> {

    protected ServiceClass service;
    protected ViewClass view;

    @Override
    public void loadModels() {
        this.service.loadModels();
    }

    @Override
    public void refresh() {
        this.service.refresh();
    }

    @Override
    public void showAll() {
        this.view.showModel(this.service.getModelList());
    }

    @Override
    public void showModel(ModelClass model) {
        this.view.showModel(model);
    }

    @Override
    public ModelClass addNewModel() {
        ModelClass model = createNewModel();
        if (this.service.add(model)) {
            return model;
        }
        return null;
    }

    @Override
    public ModelClass filterById(String id) {
        return this.service.filterById(id);
    }

    @Override
    public List<ModelClass> filterByName(String name) {
        return this.service.filterByName(name);
    }
}
