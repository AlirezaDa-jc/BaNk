package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Card;

public interface CardRepository extends BaseRepository<Card,Integer> {
    Card findByCardId(String actual);

    boolean cardIdExists(String actual);

    boolean cvv2Exists(String cvv2);
}
