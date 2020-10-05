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
    public Card findByCardId(String actual) {
        em.getTransaction().begin();
        TypedQuery<Card> query = em.createQuery(
                "SELECT u FROM Card u where u.cardId=:title",
                Card.class);

        query.setParameter("title", actual);
        if (query.getResultList().size() > 0) {
            Card card = query.getSingleResult();
            em.getTransaction().commit();
            return card;
        }
        em.getTransaction().rollback();
        return null;
    }

    @Override
    public boolean cardIdExists(String actual) {
        em.getTransaction().begin();
        TypedQuery<Card> query = em.createQuery(
                "SELECT u FROM Card u where u.cardId=:title",
                Card.class);
        query.setParameter("title", actual);
        if (query.getResultList().size() > 0) {
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().rollback();
        return false;
    }

    @Override
    public boolean cvv2Exists(String cvv2) {
        em.getTransaction().begin();
        TypedQuery<Card> query = em.createQuery(
                "SELECT u FROM Card u where u.cvv2=:title",
                Card.class);
        query.setParameter("title", cvv2);
        if (query.getResultList().size() > 0) {
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().rollback();
        return false;
    }
}

