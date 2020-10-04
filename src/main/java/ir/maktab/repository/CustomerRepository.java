package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Customer;

public interface CustomerRepository extends BaseRepository<Customer,Integer> {
    boolean userLogin(String nationalCode, String password);
}
