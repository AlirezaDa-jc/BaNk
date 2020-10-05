package ir.maktab.Menu;


import ir.maktab.FactoryMethod.Menu;
import ir.maktab.FactoryMethod.MenuImpl;
import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.services.AccountService;
import ir.maktab.services.CustomerService;
import ir.maktab.services.RecordService;

public class CustomerMenu extends MenuImpl implements Menu {
    private Scan sc;

    public CustomerMenu() {
        sc = MainApp.getSc();
    }

    @Override
    public void display() {
        System.out.println("See Your Account(s) Press 1");
        System.out.println("Add a New Account Press 2");
        System.out.println("Update Your Personal Info Press 3");
        System.out.println("To Delete Your Account Press 4");
        System.out.println("To Deposit Money To Your Account Press 5");
        System.out.println("To Transfer Money To another Card Press 6");
        System.out.println("To See Your Records Press 7");

        System.out.println("Log Out Press 9");
    }

    public void setOption(Scan sc) {
        while (true) {
            try {
                option = Integer.parseInt(sc.getString("Enter a Number: "));
                super.setOption(option);
                break;
            } catch (NumberFormatException e) {
                e.getMessage();
            }
        }
    }

    public int getOption() {
        return super.getOption();
    }

    @Override
    public void menuHandler() {
        boolean flag = true;
        while (flag) {
            try {
                display();
                setOption(sc);
                int option = getOption();
                switch (option) {
                    case 1:
                        AccountService.displayAllUsersAccount();
                        break;
                    case 2:
                        AccountService.insert();
                        break;
                    case 3:
                        CustomerService.update();
                        break;
                    case 4:
                        AccountService.deleteByUser();
                        break;
                    case 5:
                        AccountService.updateBalance();
                        break;
                    case 6:
                        AccountService.transfer();
                        break;
                    case 7:
                        RecordService.displayFiltered();
                        break;
                    case 9:
                        flag = false;
                }
            }catch (NullPointerException ex){
                System.out.println("Invalid Input!");
            }
        }
    }

    @Override
    public boolean checkChoice() {
        char choice = sc.getString("Do You Want To See Details Of an Article?: ").toUpperCase().charAt(0);
        return choice == 'Y';
    }
}
