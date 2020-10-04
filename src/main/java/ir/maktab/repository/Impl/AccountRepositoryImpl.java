package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Account;
import ir.maktab.repository.AccountRepository;


public class AccountRepositoryImpl extends BaseRepositoryImpl<Account,Integer> implements AccountRepository{

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }
}
