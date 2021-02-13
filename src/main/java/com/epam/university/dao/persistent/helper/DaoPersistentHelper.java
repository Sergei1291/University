package com.epam.university.dao.persistent.helper;

import java.util.Map;

public interface DaoPersistentHelper {

    /**
     * This method is used to create query PrepareStatement for save object.
     * Fields and values of fields of object saved in Map fields. TableName
     * param defines in which table of data warehouse will be saved object.
     *
     * @param tableName
     * @param fields
     * @return
     */
    String createQuerySave(String tableName, Map<String, Object> fields);

    /**
     * This method is used to create query PrepareStatement for update data about
     * object. Fields and values of fields of object saved in Map fields. TableName
     * param defines in which table of data warehouse contains updating object.
     *
     * @param tableName
     * @param fields
     * @return
     */
    String createQueryUpdate(String tableName, Map<String, Object> fields);

    /**
     * This method is used to create array of object. Each of object keep value
     * which will be set up in PrepareStatement query. Param idIdentifiable will
     * be able include in array objects.
     * This method is used in a couple with methods of this class: createQuerySave
     * or createQueryUpdate.
     *
     * @param mapNameFiledValue
     * @param idIdentifiable
     * @return
     */
    Object[] createParams(Map<String, Object> mapNameFiledValue, int idIdentifiable);

}