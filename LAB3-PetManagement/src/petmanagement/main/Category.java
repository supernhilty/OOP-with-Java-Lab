/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.main;

/**
 *
 * @author leyen
 */
public enum Category {
    Cat,
    Dog,
    Parrot;
    
    public static Category getCategory(String category){
       if(category.equalsIgnoreCase("cat")){
           return Cat;
       }
       if(category.equalsIgnoreCase("dog")){
           return Dog;
       }
       if(category.equalsIgnoreCase("parrot")){
           return Parrot;
       }
        return null;
    }
}
