package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Account;
import ir.maktab.entities.Card;
import ir.maktab.entities.Record;
import ir.maktab.repository.Impl.RecordRepositoryImpl;
import ir.maktab.repository.RecordRepository;

public class RecordService {
    private static Scan sc = MainApp.getSc();
    private static RecordRepository repository = new RecordRepositoryImpl();
    private static Record record = new Record();

    public static boolean insert(Card card, int balance, Account account) {
        Record record = new Record();
        String date = sc.getString("Today's Date: ");
        record.setCreateDate(date);
        record.setCustomer(CustomerService.getCustomer());
        record.setRecord(balance + " Transfer From "+account.getCard().getCardId() +"("+account.getCustomer().getNationalCode() +")To "
                + card.getAccount().getCustomer().getNationalCode() + "\nAccount ID "
                + card.getCardId() + "\nBranch : " +card.getAccount().getBranch());
        char choice = sc.getString("Sure?Y/N: ").toLowerCase().charAt(0);
        if(choice == 'n') {
            record.setDone(false);
            System.out.println("Transfer Got Canceled!");
            repository.insert(record);
            return false;
        }
        record.setDone(true);
        repository.insert(record);
        return true;
    }
}
