package ir.maktab;


import ir.maktab.FactoryMethod.Menu;
import ir.maktab.FactoryMethod.MenuFactory;

public class MainApp {

    private static Scan sc = Scan.getInstance();

    public static Scan getSc() {
        return sc;
    }

    public static void main(String[] args) {
        check();
    }


    private static void check() {
        MenuFactory menuFactory = new MenuFactory();
        while (true) {
            try {
                Menu menu = menuFactory.getMenu();
                menu.menuHandler();
            }catch (NullPointerException ex){
                System.out.println("Invalid Input!");
            }
        }
    }

//    private static void checkRole() {
//        if(RoleService.checkRole()) {
//            Role role1 = RoleService.getRole();
//            switch (role1.getRoleTitle()) {
//                case "USER":
//                    UserService.userLogin();
//                    new UserMenu().menuHandler();
//                    break;
//                case "ADMIN":
//                    if(AdminService.adminLogin()) {
//                        new AdminMenu().menuHandler();
//                    }
//                    break;
//            }
//        }
//
//    }


}
