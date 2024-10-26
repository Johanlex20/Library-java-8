package com.library.demo.service.iService;

public interface iConvertirDatos {
    <T> T convertirDatos(String json, Class<T> clase);
}
