package com.library.demo.service.iConvertirDatos;

public interface iConvertirDatos {
    <T> T convertirDatos(String json, Class<T> clase);
}
