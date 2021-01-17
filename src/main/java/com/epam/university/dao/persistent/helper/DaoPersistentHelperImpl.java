package com.epam.university.dao.persistent.helper;

import java.util.Collection;
import java.util.Map;

public class DaoPersistentHelperImpl implements DaoPersistentHelper {

    @Override
    public String createQuerySave(String tableName, Map<String, Object> mapNameFiledValue) {
        StringBuilder builder = new StringBuilder();
        builder.append("insert into ");
        builder.append(tableName);
        builder.append(" (");
        for (String columnName : mapNameFiledValue.keySet()) {
            builder.append(columnName);
            builder.append(",");
        }
        removeLastSymbol(builder);
        builder.append(") values (");
        for (int i = 0; i < mapNameFiledValue.size(); i++) {
            builder.append("?");
            builder.append(",");
        }
        removeLastSymbol(builder);
        builder.append(");");
        return new String(builder);
    }

    @Override
    public String createQueryUpdate(String tableName, Map<String, Object> mapNameFiledValue) {
        StringBuilder builder = new StringBuilder();
        builder.append("update ");
        builder.append(tableName);
        builder.append(" set ");
        for (String columnName : mapNameFiledValue.keySet()) {
            builder.append(columnName);
            builder.append(" = ?,");
        }
        removeLastSymbol(builder);
        builder.append(" where id = ?;");
        return new String(builder);
    }

    @Override
    public Object[] createParams(Map<String, Object> mapNameFiledValue, int identifiableId) {
        int numberParams = mapNameFiledValue.size();
        if (identifiableId != 0) {
            numberParams++;
        }
        Object[] params = new Object[numberParams];
        Collection<Object> values = mapNameFiledValue.values();
        values.toArray(params);
        if (identifiableId != 0) {
            params[numberParams - 1] = identifiableId;
        }
        return params;
    }

    private void removeLastSymbol(StringBuilder builder) {
        int lengthBuilder = builder.length();
        builder.deleteCharAt(lengthBuilder - 1);
    }

}