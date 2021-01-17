package com.epam.university.dao.persistent.api;

import com.epam.university.dao.DaoException;
import com.epam.university.dao.api.AbstractDao;
import com.epam.university.dao.mapper.RowMapper;
import com.epam.university.dao.persistent.extractor.FieldExtractor;
import com.epam.university.dao.persistent.helper.DaoPersistentHelper;
import com.epam.university.model.identifiable.Identifiable;

import java.sql.Connection;
import java.util.Map;

public abstract class AbstractPersistentDao<T extends Identifiable> extends AbstractDao<T>
        implements PersistentDao<T> {

    private final static String ID = "id";

    private final FieldExtractor<T> fieldExtractor;
    private final DaoPersistentHelper daoPersistentHelper;

    protected AbstractPersistentDao(Connection connection, RowMapper<T> rowMapper, String tableName,
                                    FieldExtractor<T> fieldExtractor, DaoPersistentHelper daoPersistentHelper) {
        super(connection, rowMapper, tableName);
        this.fieldExtractor = fieldExtractor;
        this.daoPersistentHelper = daoPersistentHelper;
    }

    @Override
    public void save(T identifiable) throws DaoException {
        int id = identifiable.getId();
        Map<String, Object> mapNameFiledValue = fieldExtractor.extract(identifiable);
        mapNameFiledValue.remove(ID);
        String query = createQuery(mapNameFiledValue, id);
        Object[] params = daoPersistentHelper.createParams(mapNameFiledValue, id);
        executeUpdate(query, params);
    }

    private String createQuery(Map<String, Object> mapNameFiledValue, int id) {
        String tableName = getTableName();
        if (id == 0) {
            return daoPersistentHelper.createQuerySave(tableName, mapNameFiledValue);
        } else {
            return daoPersistentHelper.createQueryUpdate(tableName, mapNameFiledValue);
        }
    }

}