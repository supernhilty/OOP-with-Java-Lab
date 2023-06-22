/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.view;

import petmanagement.model.Dog;
import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class DogView extends PetView{

    private DogView() {
    }
    
    @Override
    public String getInputID() {
        return Util.inputString("Please enter studen's id with the pattern(" + Dog.ID_FORMAT + ")", false).trim();
    }
    
}
