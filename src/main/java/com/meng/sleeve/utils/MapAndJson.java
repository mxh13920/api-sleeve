package com.meng.sleeve.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meng.sleeve.exception.http.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.sql.rowset.serial.SerialException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class MapAndJson implements AttributeConverter<Map<String, Object>, String> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(Map<String, Object> stringObjectMap) {
        try {
            String s = mapper.writeValueAsString(stringObjectMap);
            return s;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceException(99999);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        try {
            return mapper.readValue(s,HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw  new ServiceException(99999);
        }
    }
}
