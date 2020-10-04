package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Card;
import ir.maktab.repository.CardRepository;

import javax.persistence.TypedQuery;

public class CardRepositoryImpl extends BaseRepositoryImpl<Card,Integer> implements CardRepository {
    @Override
    protected Class<Card> getEntityClass() {
        return Card.class;
    }

    @Override
    public boolean checkDuplicateTitle(String actual) {
        em.getTransaction().begin();
        TypedQuery<Card> query = em.createQuery(
                "SELECT u FROM Card u where u.cardId=:title",
                Card.class);

        query.setParameter("title", actual);
        if (query.getResultList().size() > 0) {
            em.getTransaction().rollback();
            return false;
        }
        em.getTransaction().commit();
        return true;
    }
}
