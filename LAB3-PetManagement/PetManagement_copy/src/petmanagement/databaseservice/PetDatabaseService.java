/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.databaseservice;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import petmanagement.entity.PetEntity;
import petmanagement.main.Category;
import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class PetDatabaseService extends PetStoreAbstractDatabaseService<PetEntity> {

    private static final int PET_ENTITY_ATTRIBUTE_COUNT = 6;

    private PetDatabaseService() {
        prepareDataFile(getEntityFilePath(PetEntity.class));
    }

    @Override
    public List<PetEntity> loadEntitys(Class<PetEntity> clazz) {
        return loadEntitysFromFile(getEntityFilePath(clazz));
    }

    @Override
    public boolean saveEntitys(List<PetEntity> entityLis) {
        if (entityLis != null && !entityLis.isEmpty()) {
            return saveEntitysToFile(getEntityFilePath(entityLis.get(0).getClass()), entityLis);
        }
        return false;
    }

    @Override
    protected String toString(PetEntity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append(entity.getId());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(entity.getName());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(entity.getDescription());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(Util.toString(entity.getImportDate()));
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(entity.getPrice());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(entity.getCategory());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);

        return sb.toString();
    }

    @Override
    protected PetEntity toEntity(String entityString) {
        PetEntity petEntity = null;
        if (entityString != null) {
            String[] attributes = entityString.split(PetStoreAbstractDatabaseService.SEPARATOR, -1);
            if (attributes.length >= PetDatabaseService.PET_ENTITY_ATTRIBUTE_COUNT) {
                petEntity = new PetEntity();
                petEntity.setId(attributes[0]);
                petEntity.setName(attributes[1]);

                petEntity.setDescription(attributes[2]);
                try {
                    petEntity.setImportDate(Util.toDate(attributes[3]));
                } catch (ParseException ex) {
                    Logger.getLogger(PetDatabaseService.class.getName()).log(Level.SEVERE, null, ex);
                }
                petEntity.setPrice(Double.parseDouble(attributes[4]));
                petEntity.setCategory(Category.getCategory(attributes[5]));

            }
        }
        return petEntity;
    }
}
