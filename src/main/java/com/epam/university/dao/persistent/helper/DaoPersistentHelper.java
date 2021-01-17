package com.epam.university.dao.persistent.helper;

import java.util.Map;

public interface DaoPersistentHelper {

    String createQuerySave(String tableName, Map<String, Object> fields);

    String createQueryUpdate(String tableName, Map<String, Object> fields);

    Object[] createParams(Map<String, Object> mapNameFiledValue, int idIdentifiable);

}