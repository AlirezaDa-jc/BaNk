package ir.maktab.FactoryMethod;

import ir.maktab.Scan;
import ir.maktab.controller.CustomerMenu;
import ir.maktab.controller.EmployeeMenu;
import ir.maktab.services.CustomerService;
import ir.maktab.services.EmployeeService;
import ir.maktab.services.RoleService;

public class MenuFactory {
    Scan sc = Scan.getInstance();

    public Menu getMenu() {

        String type = sc.getString("Enter Role: ");
        type = type.toUpperCase();
        switch (type) {
            case "CUSTOMER":
                RoleService.checkRole(type);
                if (CustomerService.userLogin()) {
                    return new CustomerMenu();
                }
                break;
            case "EMPLOYEE":
                RoleService.checkRole(type);
//                BranchService.add();
                if (EmployeeService.login()) {
                    return new EmployeeMenu();
                }
                break;
            default:
                System.out.println("Invalid Role");
                System.out.println("Roles Are Customer And Employee");
                return null;
        }
        return null;
    }
}
