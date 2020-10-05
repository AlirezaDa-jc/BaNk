package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Account;
import ir.maktab.entities.Card;
import ir.maktab.entities.Record;
import ir.maktab.repository.Impl.RecordRepositoryImpl;
import ir.maktab.repository.RecordRepository;

import java.util.List;

public class RecordService {
    private static Scan sc = MainApp.getSc();
    private static RecordRepository repository = new RecordRepositoryImpl();
    private static Record record = new Record();

    public static boolean insert(Card card, int balance, Account account) {
        Record record = new Record();
        String date = sc.getString("Today's Date: ");
        while (!date.matches("[0-9]{4}-[0-3][0-9]-[0-3][0-9]")) {
            date = sc.getString("Today's Date: : (Format : YYYY-MM-DD)");
        }
        record.setCreateDate(date);
        record.setCustomer(CustomerService.getCustomer());
        record.setRecord(balance + " Transfer From " + account.getCard().getCardId() + "(" + account.getCustomer().getNationalCode() + ")To "
                + card.getAccount().getCustomer().getNationalCode() + "\nAccount ID "
                + card.getCardId() + "\nBranch : " + card.getAccount().getBranch());
        char choice = sc.getString("Sure?Y/N: ").toLowerCase().charAt(0);
        if (choice == 'n') {
            record.setDone(false);
            System.out.println("Transfer Got Canceled!");
            repository.insert(record);
            return false;
        }
        record.setDone(true);
        repository.insert(record);
        return true;
    }

    public static void displayFiltered() {
        List<Record> all = repository.findAll();
        String desiredDate = sc.getString("Desired Date: ");
        while (!desiredDate.matches("[0-9]{4}-[0-3][0-9]-[0-3][0-9]")) {
            desiredDate = sc.getString("Desired Date: : (Format : YYYY-MM-DD)");
        }
        String finalDesiredDate = desiredDate;
        all.stream()
                .filter((c) -> {
                    if (Integer.parseInt(c.getCreateDate().substring(0, 4)) > Integer.parseInt(finalDesiredDate.substring(0, 4))) {
                        return true;
                    }
                    if (Integer.parseInt(c.getCreateDate().substring(0, 4)) == Integer.parseInt(finalDesiredDate.substring(0, 4)))
                        if (Integer.parseInt(c.getCreateDate().substring(5, 7)) > Integer.parseInt(finalDesiredDate.substring(5, 7))) {
                            return true;
                        }
                    if (Integer.parseInt(c.getCreateDate().substring(5, 7)) == Integer.parseInt(finalDesiredDate.substring(5, 7))) {
                        return Integer.parseInt(c.getCreateDate().substring(8, 10)) > Integer.parseInt(finalDesiredDate.substring(8, 10));
                    }
                    return false;
                })
                .forEach(System.out::println);
    }
}
