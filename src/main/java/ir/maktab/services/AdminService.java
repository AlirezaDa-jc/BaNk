//package ir.maktab.services;
//
//import ir.maktab.MainApp;
//
//import ir.maktab.entities.Employer;
//import ir.maktab.Scan;
////import ir.maktab.entities.Role;
//import ir.maktab.entities.Customer;
//import ir.maktab.entities.Role;
//
//import java.util.Set;
//
//public class AdminService {
//
//    private static Employer employer;
//    private static AdminRepository repository = new AdminRepositoryImpl();
//    private static Scan sc = MainApp.getSc();
//
//    public static Employer getEmployer() {
//        return employer;
//    }
//
//    public static void setEmployer(Employer employer) {
//        AdminService.employer = employer;
//    }
//
//    public static boolean adminLogin() {
//        String userName = sc.getString("Username: ");
//        String password = sc.getString("Password: ");
//
//        if(repository.adminLogin(userName, password) != null){
//            employer = repository.adminLogin(userName, password);
////            admin.setName(userName);
////            admin.setPassword(password);
////            Role role = RoleService.getRole();
////            admin.setRole(role);
////            role.addAdmin(admin);
//            return true;
//        }
//        return false;
//    }
//
//    public static void insert() {
//        String userName = sc.getString("Username: ");
//        String password = sc.getString("Password: ");
//        employer.setName(userName);
//        employer.setPassword(password);
//        Role role = RoleService.getRole();
//        employer.setRole(role);
//        repository.insert(employer);
//        role.addAdmin(employer);
//    }
//
//    public static void editRole() {
//        Set<Customer> customerSet = RoleService.displayUsers();
//        String selectedUserName = sc.getString("Which User You Want To Change Its Role: (Enter Name) : ");
//        Customer selectedCustomer = null;
//        for(Customer customer : customerSet){
//            if(selectedUserName.equals(customer.getName())){
//                selectedCustomer = customer;
//            }
//        }
//        RoleService.display();
//        assert selectedCustomer != null;
//
//        String selectedRole = sc.getString("Which Role You Want To Give This User: ");
//        switch (selectedRole.toUpperCase()){
//            case "ADMIN":
//                selectedCustomer.setRole(RoleService.getRole());
//                CustomerService.update(selectedCustomer);
//                AdminService.adminAdd(selectedCustomer);
//                break;
//            case "USER":
//                selectedCustomer.setRole(selectedCustomer.getRole());
//                break;
//        }
//        System.out.println(selectedCustomer.getName() +": " + selectedCustomer.getRole().getRoleTitle());
//    }
//
//    private static void adminAdd(Customer newCustomer) {
//
//            Employer a = new Employer();
//            a.setName(newCustomer.getName());
//            a.setPassword(newCustomer.getPassword());
//            a.setRole(newCustomer.getRole());
//            repository.insert(a);
//
//    }
//
//}
