package ir.maktab.FactoryMethod;

//import ir.maktab.Menu.EmployerMenu;
import ir.maktab.Menu.CustomerMenu;
import ir.maktab.Scan;
import ir.maktab.services.RoleService;
import ir.maktab.services.CustomerService;

public class MenuFactory {
    Scan sc = Scan.getInstance();

    public Menu getMenu() {

        String type = sc.getString("Enter Role: ");
        type = type.toUpperCase();
        switch (type) {
            case "CUSTOMER":
                System.out.println(type);
                RoleService.checkRole(type);
                if (CustomerService.userLogin()) {
                    return new CustomerMenu();
                }
                break;
//            case "ADMIN":
//                RoleService.checkRole(type);
//                if (AdminService.adminLogin()) {
//                    return new EmployerMenu();
//                }
//                break;
            default:
                System.out.println("Invalid Role");
                return null;
        }
        return null;
    }
}
