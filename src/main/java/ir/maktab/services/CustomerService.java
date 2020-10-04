package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Account;
import ir.maktab.entities.Address;
import ir.maktab.entities.Customer;
import ir.maktab.entities.Role;
import ir.maktab.repository.CustomerRepository;
import ir.maktab.repository.Impl.CustomerRepositoryImpl;


public class CustomerService {
    private static Customer customer = new Customer();
    private static CustomerRepository repository = new CustomerRepositoryImpl();
    private static Scan sc = MainApp.getSc();


    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        CustomerService.customer = customer;
    }

    public static void userSignIn() {
        String name = sc.getString("Name: ");
        customer.setName(name);
        String nationalCode = sc.getString("National Code: ");
        customer.setNationalCode(nationalCode);
        String birthday = sc.getString("Date of Birthday: (Format : YYYY-MM-DD)");
        while (!birthday.matches("[0-9]{4}-[0-3][0-9]-[0-3][0-9]")) {
            birthday = sc.getString("Date of Birthday: (Format : YYYY-MM-DD)");
        }
        customer.setBirthday(birthday);
        String choice = sc.getString("You Want To Enter a Password? Y/N");
        if(choice.toLowerCase().charAt(0) == 'y') {
            String password = sc.getString("Password: ");
            String confirmPassword = sc.getString("Confirmed Password: ");
            if (password.equals(confirmPassword)) {
                customer.setPassword(password);
            } else {
                System.out.println("Invalid Password");
                return;
            }
        }
        Role role = RoleService.getRole();
        customer.setRole(role);
        role.addCustomer(customer);
        Address address = new Address();
        address.setAddress(sc.getString("Address: "));
        AddressService.insert(address);
        customer.setAddress(address);
        repository.insert(customer);
        AccountService.insert();
        Account account = AccountService.getAccount();
        customer.addAccount(account);

    }

    public static boolean signInOrLogin() {
        String choice = sc.getString("Sign In Or Login Or Exit: ");
        switch (choice.toUpperCase()) {
            case "SIGN IN":
                userSignIn();
                return true;
            case "LOGIN":
                if (login()) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    private static boolean login() {
        String nationalCode = sc.getString("NationalCode: ");
        String password = sc.getString("Password: (If You Don't Have Any Password Press Enter!))");
        return repository.userLogin(nationalCode, password);
    }

    public static void updatePassword() {
        String oldPassword = sc.getString("Enter Your Old Password: ");
        if (!oldPassword.equals(customer.getPassword())) {
            System.out.println("Wrong Password!");
            return;
        }
        String newPassword = sc.getString("Enter Your New Password: ");
        String confirmedPassword = sc.getString("Enter Your New Password Again: ");
        if (newPassword.equals(confirmedPassword)) {
            customer.setPassword(newPassword);
            repository.update(customer);
            System.out.println("Updated Successfully");
        }
        System.out.println("Unmatched Passwords!");

    }


    public static boolean userLogin() {
        return signInOrLogin();
    }

    public static void update() {
        String update = sc.getString("What You Want To Update? (Name/Birthday/Password)");
        switch (update.toUpperCase()) {
            case "NAME":
                updateName();
                break;
            case "BIRTHDAY":
                updateBirthday();
                break;
            case "PASSWORD":
                updatePassword();
                break;
            default:
                System.out.println("Invalid Input!");
        }
    }




    private static void updateBirthday() {
        System.out.println("Your Birthday is : " + customer.getBirthday());
        String choice = sc.getString("Are You Sure You Want To Change Your Birthday: Y/N");
        if(choice.toLowerCase().charAt(0) == 'n') return;
        String birthday = sc.getString("Birthday: ");
        while (!birthday.matches("[0-9]{4}-[0-3][0-9]-[0-3][0-9]")) {
            birthday = sc.getString("Date of Birthday: (Format : YYYY-MM-DD)");
        }
        customer.setBirthday(birthday);
        repository.update(customer);
        System.out.println("Updated Successfully");
    }

    private static void updateName() {
        System.out.println("Your Name Is : " + customer.getName());
        String choice = sc.getString("Are You Sure You Want To Change Your Name: Y/N");
        if(choice.toLowerCase().charAt(0) == 'n') return;
        String name = sc.getString("name: ");
        customer.setName(name);
        repository.update(customer);
        System.out.println("Updated Successfully");
    }

//    public static Customer findUser(String name) {
//        return repository.findByTitle(name);
//    }
//
//    public static void update(Customer newCustomer) {
//        repository.delete(newCustomer);
//    }
//
//    public static void delete() {
//        repository.displayAll();
//        int id = Integer.parseInt(sc.getString("ID Of User"));
//        repository.deleteById(id);
//    }
//
//    public static void display() {
//        repository.displayAll();
//    }
}
