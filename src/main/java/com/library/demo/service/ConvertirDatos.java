package com.library.demo.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.demo.service.iConvertirDatos.iConvertirDatos;
import org.springframework.stereotype.Service;

@Service
public class ConvertirDatos implements iConvertirDatos {

private final ObjectMapper objectMapper;

    public ConvertirDatos() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> T convertirDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
