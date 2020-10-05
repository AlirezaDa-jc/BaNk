package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Account;
import ir.maktab.entities.Address;
import ir.maktab.entities.Customer;
import ir.maktab.entities.Role;
import ir.maktab.repository.CustomerRepository;
import ir.maktab.repository.Impl.CustomerRepositoryImpl;

import java.util.List;


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
        if (choice.toLowerCase().charAt(0) == 'y') {
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

        int countLoginTry = 0;
        String nationalCode = sc.getString("NationalCode: ");
        customer = repository.findByNC(nationalCode);
        if(customer.isSuspended()){
            System.out.println("Customer Suspended!");
            System.out.println("Report To Admins!");
            return false;
        }
        while (true) {
            if(countLoginTry > 2){
                System.out.println("Account Suspended!");
                customer.setSuspended(true);
                repository.update(customer);
                return false;
            }
            if(customer.getPassword() == null){
                return true;
            }
            if (checkPassword(customer)) {
                return true;
            }else{
                countLoginTry++;
            }
        }
    }

    private static boolean checkPassword(Customer customer) {
        String password = sc.getString("Password: ");
        return customer.getPassword().equals(password);
    }

    public static void updatePassword() {
        if(customer.getPassword()!=null) {
            String oldPassword = sc.getString("Enter Your Old Password: ");
            if (!oldPassword.equals(customer.getPassword())) {
                System.out.println("Wrong Password!");
                return;
            }
        }
        String newPassword = sc.getString("Enter Your New Password: ");
        String confirmedPassword = sc.getString("Enter Your New Password Again: ");
        if (newPassword.equals(confirmedPassword)) {
            customer.setPassword(newPassword);
            System.out.println(customer);
            repository.update(customer);
            System.out.println("Updated Successfully");
            return;
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
        if (choice.toLowerCase().charAt(0) == 'n') return;
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
        if (choice.toLowerCase().charAt(0) == 'n') return;
        String name = sc.getString("name: ");
        customer.setName(name);
        repository.update(customer);
        System.out.println("Updated Successfully");
    }
    public static void updateSuspension() {
        displayAllSuspendedAccounts();
        int id = Integer.parseInt(sc.getString("ID Of Customer"));
        Customer customer = repository.findById(id);
        char choice = sc.getString("Allow: Y/N : ").toLowerCase().charAt(0);
        if(choice == 'n') {
            System.out.println("Update Unsuccessful");
            return;
        }
        customer.setSuspended(false);
        repository.update(customer);
    }

    private static void displayAllSuspendedAccounts() {
        List<Customer> all = repository.findAll();
        all.stream()
                .filter(Customer::isSuspended)
                .forEach(System.out::println);
    }

}
