package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Record;
import ir.maktab.repository.RecordRepository;

public class RecordRepositoryImpl extends BaseRepositoryImpl<Record,Integer> implements RecordRepository {
    @Override
    protected Class<Record> getEntityClass() {
        return Record.class;
    }
}
