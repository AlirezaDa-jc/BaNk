package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Customer;
import ir.maktab.repository.CustomerRepository;
import ir.maktab.services.CustomerService;

import javax.persistence.TypedQuery;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer, Integer> implements CustomerRepository {
    @Override
    protected Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    public boolean userLogin(String nationalCode, String password) {
        em.getTransaction().begin();
        TypedQuery<Customer> query = em.createQuery(
                "SELECT u FROM Customer u where u.nationalCode=:code",
                Customer.class);
        query.setParameter("code", nationalCode);
        if (query.getResultList().size() > 0) {
            Customer u = query.getSingleResult();
            String oldPass = u.getPassword();
            if (oldPass == null) {
                System.out.println("You didn't Set a Password!");
                System.out.println("To Ensure Safety Set a Password Later!");
            } else if (!oldPass.equals(password)) {
                System.out.println("Invalid Password " + u.getPassword());
                return false;
            }
            CustomerService.setCustomer(u);
            System.out.println("Welcome BaCk: " + u.getName());
            em.getTransaction().commit();
            return true;
        } else {
            em.getTransaction().rollback();
            return false;
        }
    }
}
