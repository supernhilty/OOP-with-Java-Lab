package petmanagement.view;

import petmanagement.model.UserModel;
import petmanagement.utils.Util;



/**
 * UserView.
 *
 * @author hasu
 */
public class UserView extends PetStoreAbstractView<UserModel> {

    private UserView() {
    }

    public String getInputId() {
        return Util.inputString("Please enter user's id", false).trim();
    }

    public String getInputPassword() {
        return Util.inputString("Please enter user's password", false).trim();
    }

    @Override
    public String toString(UserModel model) {
        StringBuilder sb = new StringBuilder();
        if (model != null) {
            sb.append("\tUser {");
            sb.append("\n\t\tName: ");
            sb.append(model.getName());
            sb.append("\n\t\tRole: ");
            sb.append(model.getRole());
            sb.append("\n\t\tDescription: ");
            if (model.getDescription() != null) {
                sb.append(model.getDescription());
            }
            sb.append("\n\t}");
        }
        return sb.toString();
    }

}
