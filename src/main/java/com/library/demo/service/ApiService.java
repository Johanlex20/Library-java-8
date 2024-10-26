package com.library.demo.service;
import com.library.demo.config.ConsumoApiGutendex;
import com.library.demo.model.dtos.DataBookDto;
import com.library.demo.model.dtos.DataApiOriginal;
import com.library.demo.service.iService.iApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService implements iApiService {
    private final ConsumoApiGutendex consumoApi;
    private final ConvertirDatos convertirDatos;

    @Autowired
    public ApiService(ConsumoApiGutendex consumoApi, ConvertirDatos convertirDatos){
        this.consumoApi = consumoApi;
        this.convertirDatos = convertirDatos;
    }


    public DataBookDto obtnerDatosLibro(String  title){
        String json = consumoApi.obtenerDatos( title);

        DataApiOriginal datos = convertirDatos.convertirDatos(json, DataApiOriginal.class);

        if (datos.getResponseBooks() != null && !datos.getResponseBooks().isEmpty()){
            return datos.getResponseBooks().get(0);
        }else {
            System.out.println("No se encontraron libros para el título");
            return null;
        }
    }


}
