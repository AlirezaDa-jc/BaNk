package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.*;
import ir.maktab.repository.AccountRepository;
import ir.maktab.repository.Impl.AccountRepositoryImpl;

import java.util.InputMismatchException;
import java.util.List;

/*
Design Pattern Combinator
 */
public class AccountService {

    private static Account account = new Account();
    private static AccountRepository repository = new AccountRepositoryImpl();
    private static Scan sc = MainApp.getSc();

    public static Account getAccount() {
        return account;
    }

    public static void insert() {
        System.out.println("\nAccount Section!");
        String createDate = sc.getString("Create Date: (Format : YYYY-MM-DD): ");
        createDate = checkDate(createDate);
        account.setCreateDate(createDate);
        Branch branch = BranchService.use();
        account.setBranch(branch);
        Customer customer = CustomerService.getCustomer();
        account.setCustomer(customer);
        if (branch == null || customer == null) {
            System.out.println("Invalid Category Or User Usage");
            return;
        }
        System.out.println("\n Card Section!");
        Card card = new Card();
        String actual = "";
        actual = CardService.checkID(actual);
        card.setCardId(actual);
        String cvv2 = "";
        cvv2 = CardService.checkCVV2(cvv2);
        card.setCvv2(cvv2);
        String password = sc.getString("Password: ");
        card.setPassword(password);
        CardService.add(card);
        account.setCard(card);
        AccountService.insert(account);
        System.out.println("Account Successfully Created!");
    }

    private static String checkDate(String createDate) {
        while (!createDate.matches("[0-9]{4}-[0-3][0-9]-[0-3][0-9]")) {
            createDate = sc.getString("Create Date: (Format : YYYY-MM-DD): ");
        }
        return createDate;
    }


    public static void displayAll() {
        List<Account> all = repository.findAll();
        all.stream()
                .filter((a) -> !a.isDeleted())
                .forEach(System.out::println);
    }

    public static void displayAllUsersAccount() {
        List<Account> all = repository.findAll();
        all.stream()
//                .filter((a) -> a.getCustomer()==CustomerService.getCustomer()))
                .filter((a) -> a.getCustomer().getNationalCode().equals(CustomerService.getCustomer().getNationalCode()))
                .filter((a) -> !a.isDeleted())
                .forEach(System.out::println);
    }


    public static void insert(Account account) {
        repository.insert(account);
    }

    public static void deleteByUser() {
        displayAllUsersAccount();
        try {
            int id = Integer.parseInt(sc.getString("Which Want You Want To Delete: "));
            String choice = sc.getString("Sure? Y/N");
            if (choice.toLowerCase().charAt(0) == 'n') return;
            Account account = repository.findById(id);
            account.setDeleted(true);
            repository.update(account);
        } catch (InputMismatchException ex) {
            System.out.println("Invalid Input!");
        }
    }

    public static void updateBalance() {
        displayAllUsersAccount();
        int id = Integer.parseInt(sc.getString("Which Want You Want To Add Balance: "));
        Account account = repository.findById(id);
        System.out.println("Your Balance is : " + account.getBalance());
        int balance = Integer.parseInt(sc.getString("How Much You Want to Add: "));
        balance += account.getBalance();
        account.setBalance(balance);
        repository.update(account);
        System.out.println("Updated Successfully");
    }

    public static void transfer() {
        try {
            String cardId = sc.getString("Card Id: ");
            if(cardId.length() != 16) {
                System.out.println("Invalid Card");
                return;
            }
            Card card = CardService.findByCardId(cardId);
            int balance = Integer.parseInt(sc.getString("How Much Money You Want To Transfer: "));
            System.out.println(balance);
            displayAllUsersAccount();
            int id = Integer.parseInt(sc.getString("ID of Account You Want TO Transfer: "));
            Account account = repository.findById(id);
            String password = sc.getString("Card's Password: ");
            String cvv2 = sc.getString("Cvv2: ");
            if (!account.getCard().getPassword().equals(password) || !account.getCard().getCvv2().equals(cvv2)) {
                System.out.println("Invalid Password Or Cvv2");
                return;
            }
            Integer updatedBalance = account.getBalance();
            if (balance >= (updatedBalance - 500)) {
                System.out.println(balance);
                System.out.println(updatedBalance);
                System.out.println("Not Enough Money!");
                return;
            }
            if (!RecordService.insert(card, balance, account)) return;
            updatedBalance -= (balance + 500);
            account.setBalance(updatedBalance);
            repository.insert(account);
            Account secondAccount = card.getAccount();
            updatedBalance = secondAccount.getBalance();
            secondAccount.setBalance((updatedBalance + balance));
            repository.insert(secondAccount);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Input!");
        }
    }


    public static void deleteAccountAdmin() {
        displayAll();
        int id = Integer.parseInt(sc.getString("ID of Account You Want to Delete: "));
        repository.deleteById(id);
        System.out.println("Deleted Successfully");
    }


}
