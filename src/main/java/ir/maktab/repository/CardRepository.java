package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Card;

public interface CardRepository extends BaseRepository<Card,Integer> {
    boolean checkDuplicateTitle(String actual);
}
