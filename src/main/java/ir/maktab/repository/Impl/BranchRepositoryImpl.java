package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Branch;
import ir.maktab.repository.BranchRepository;

public class BranchRepositoryImpl extends BaseRepositoryImpl<Branch,Integer> implements BranchRepository {
    @Override
    protected Class<Branch> getEntityClass() {
        return Branch.class;
    }
}
