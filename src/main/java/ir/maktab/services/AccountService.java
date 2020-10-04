package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Account;
import ir.maktab.entities.Branch;
import ir.maktab.entities.Card;
import ir.maktab.entities.Customer;
import ir.maktab.repository.AccountRepository;
import ir.maktab.repository.Impl.AccountRepositoryImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

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
            return ;
        }
        System.out.println("\n Card Section!");
        Card card = new Card();
        String actual = "";
        actual = CardService.checkID(actual);
        card.setCardId(actual);
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

//    public static void displayAUserArticle() {
//        repository.displayAUserArticle();
//    }

    private static String getText(String s) {
        System.out.print(s);
        StringBuilder text = new StringBuilder();
        while (true) {
            String temp = sc.getString();
            if (temp.equals("ESC")) {
                break;
            }
            text.append(temp).append("\n");
        }
        return text.toString();
    }


//    public static void deleteArticleUser() {
//        displayAUserArticle();
//        int id = Integer.parseInt(sc.getString("ID of Article You Want to Delete: "));
//        Customer customer = CustomerService.getCustomer();
//        Set<Account> accountSet = customer.getAccounts();
//        for (Account account : accountSet) {
//            if (account.getId() == id) {
//                repository.deleteById(id);
//                Set<Card> cards = account.getTags();
//                System.out.println("Deleted Successfully");
//                cards.forEach((t) -> t.removeArticle(true));
//            } else {
//                System.out.println("Invalid ID");
//            }
//            return;
//        }
//    }


//    private static boolean updateCategory(Account account) {
//        Branch branch = CategoryService.use();
//        if (branch == null) {
//            System.out.println("Invalid Usage Of Category");
//            return false;
//        }
//        account.setCategory(branch);
//        repository.update(account);
//        return true;
//    }


    public static void displayAll() {
        List<Account> all = repository.findAll();
        all.stream()
                .filter((a)-> !a.isDeleted())
                .forEach((t)-> System.out.println("ID: " + t.getId() +"\nBranch: " + t.getBranch().getTitle() + "\nCard: "
                        + t.getCard().getCardId() + "\nCustomer: " + t.getCustomer().getName() +
                        "\nBalance: "+t.getBalance() ));
    }


    public static void insert(Account account) {
        repository.insert(account);
    }

    public static void delete() {
        displayAll();
        try {
            int id = Integer.parseInt(sc.getString("Which Want You Want To Delete: "));
            String choice = sc.getString("Sure? Y/N");
            if(choice.toLowerCase().charAt(0) == 'n') return;
            Account account = repository.findById(id);
            account.setDeleted(true);
            repository.update(account);
        }catch (InputMismatchException ex){
            System.out.println("Invalid Input!");
        }
    }

    public static void updateBalance() {
        displayAll();
        int id = Integer.parseInt(sc.getString("Which Want You Want To Add Balance: "));
        Account account = repository.findById(id);
        System.out.println("Your Balance is : " + account.getBalance());
        int balance = Integer.parseInt(sc.getString("How Much You Want to Add: "));
        balance += account.getBalance();
        account.setBalance(balance);
        repository.update(account);
        System.out.println("Updated Successfully");
    }


//    public static void deleteArticleAdmin() {
//        int id = Integer.parseInt(sc.getString("ID of Article You Want to Delete: "));
//        Account a = repository.findById(id);
//        Set<Card> cards = a.getTags();
//        repository.deleteById(id);
//            System.out.println("Deleted Successfully");
//            cards.forEach((t) -> t.removeArticle(a));
//    }


}