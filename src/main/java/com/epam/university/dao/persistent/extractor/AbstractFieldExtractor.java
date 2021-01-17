package com.epam.university.dao.persistent.extractor;

import com.epam.university.model.identifiable.Identifiable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractFieldExtractor<T extends Identifiable> implements FieldExtractor<T> {

    protected abstract void initializeMap(Map<String, Object> mapNameFiledValue, T identifiable);

    @Override
    public Map<String, Object> extract(T identifiable) {
        Map<String, Object> mapNameFiledValue = new HashMap<>();
        initializeMap(mapNameFiledValue, identifiable);
        return mapNameFiledValue;
    }

}