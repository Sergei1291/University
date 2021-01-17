package com.epam.university.dao.persistent.extractor;

import com.epam.university.model.identifiable.Identifiable;

import java.util.Map;

public interface FieldExtractor<T extends Identifiable> {

    Map<String, Object> extract(T identifiable);

}