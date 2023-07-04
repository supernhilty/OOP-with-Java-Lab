/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import petmanagement.databaseservice.PetDatabaseService;
import petmanagement.model.OrderModel;
import petmanagement.model.OrderLine;
import petmanagement.model.PetModel;
import petmanagement.service.PetService;
import petmanagement.utils.Singleton;
import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class OrderView extends PetStoreAbstractView<OrderModel> {

    public OrderView() {
    }

    public String getInputID() {
        return Util.inputString("Please enter Order ID with pattern " + OrderModel.ORDER_ID_FORMAT, true).trim();
    }

    public String getInputCustomer() {
        return Util.inputString("Please enter customer name", true).trim();
    }

    public Date getInputOrderDate() {
        return Util.inputDate("Please enter Order Date");
    }

    public String getInputPetID() {
        
        return Util.inputString("Input Pet ID", true);
    }

    public List<OrderLine> getInputPetOrder() {
        List<OrderLine> list = new ArrayList<>();
        int orderQuantity = 0;
        String petID;
        do {
            petID = getInputPetID();
            if (!petID.isBlank() && Singleton.getInstance(PetService.class).filterById(petID)!=null) {
                orderQuantity = Util.inputInteger("Input quantity", 0, Integer.MAX_VALUE);
                list.add(new OrderLine(Singleton.getInstance(PetService.class).filterById(petID), orderQuantity));
            }else{
                System.out.println("Pet ID is blank or has not already existed!");
            }
        } while (!petID.isBlank() || list.isEmpty());
        return list;
    }

    @Override
    public String toString(OrderModel model) {
        StringBuilder sb = new StringBuilder();
        if (model != null) {
            sb.append("\n\t\tId: ");
            sb.append(model.getId());
            sb.append("\n\t\tOrder Date: ");
            sb.append(Util.toString(model.getOrderDate()));
            sb.append("\n\t\tCustomer: ");
            sb.append(model.getCustomer());
            sb.append("\n\t\tPet Count: ");
            sb.append(model.getPetCount());
            sb.append("\n\t\tTotal: ");
            sb.append(model.getTotal());
        }
        return sb.toString();
    }

}
