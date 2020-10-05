package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Address;
import ir.maktab.entities.Branch;
import ir.maktab.repository.BranchRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class BranchRepositoryImpl extends BaseRepositoryImpl<Branch,Integer> implements BranchRepository {
    @Override
    protected Class<Branch> getEntityClass() {
        return Branch.class;
    }

    @Override
    public Branch findByTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Branch> query = em.createQuery(
                "SELECT u FROM Branch u where u.title=:title",
                Branch.class);

        query.setParameter("title", title);
        List<Branch> resultList = query.getResultList();
        em.getTransaction().commit();
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
