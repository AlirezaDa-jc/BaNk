package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Card;
import ir.maktab.repository.CardRepository;
import ir.maktab.repository.Impl.CardRepositoryImpl;

import java.util.Random;

public class CardService {
    private static Card card = new Card();
    private static CardRepository repository = new CardRepositoryImpl();
    private static Scan sc = MainApp.getSc();
    static final long MAX_NUMBER = 9999999999999999L;
    static final long MIN_NUMBER = 1000000000000000L;

    public static Card getCard() {
        return card;
    }

    public static void setCard(Card card) {
        CardService.card = card;
    }

    public static void add(Card card1) {
        repository.insert(card1);
    }

    private static boolean check(Card newCard) {
        Card card = repository.findById(newCard.getId());
        return card != null;
    }

    public static String checkID(String actual) {
        while(!repository.checkDuplicateTitle(actual)) actual = String.valueOf(Math.abs(Float.valueOf(new Random().nextFloat() *
                (MAX_NUMBER - MIN_NUMBER)).longValue()));
        return actual;
    }

//
//    public static void displayAll() {
//        repository.displayAll();
//    }


}
