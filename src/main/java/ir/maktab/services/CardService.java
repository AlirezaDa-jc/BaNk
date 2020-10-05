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
        while(repository.cardIdExists(actual)) {
            long i = Math.abs(Float.valueOf(new Random().nextFloat() *
                    (MAX_NUMBER - MIN_NUMBER)).longValue());
            System.out.println(i);
            actual = String.valueOf(i);
        }
        return actual;
    }

    public static Card findByCardId(String cardId) {
        return repository.findByCardId(cardId);
    }

    public static String checkCVV2(String cvv2) {
        while(repository.cvv2Exists(cvv2)) {
            cvv2 = String.valueOf(Math.abs(new Random().nextInt()));
            while(!(Integer.parseInt(cvv2) < 9999 && Integer.parseInt(cvv2) > 1000))
                cvv2 = String.valueOf(Math.abs(new Random().nextInt()));
        }
        return cvv2;
    }



}
