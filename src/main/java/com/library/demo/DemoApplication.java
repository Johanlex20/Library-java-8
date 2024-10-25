package com.library.demo;
import com.library.demo.config.ConsumoApiGutendex;
import com.library.demo.dao.DataAuthorDAO;
import com.library.demo.model.DataApiOriginal;
import com.library.demo.service.ConvertirDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("Probando Api");

		ConsumoApiGutendex consumoApi = new ConsumoApiGutendex();
		ConvertirDatos convertirDatos = new ConvertirDatos();

		String json = consumoApi.obtenerDatos("Hamlet");
		//System.out.println(json);

		DataApiOriginal datos = convertirDatos.convertirDatos(json, DataApiOriginal.class);

		System.out.println("Datos convertidos:");
		datos.getResponseBooks().stream().findFirst().ifPresent(primerLibro -> {
			System.out.printf("ID: %d%n Título: %s%n Autores: %s%n Idiomas: %s%n Géneros: %s%n Descargas: %d%n Imagen: %s%n",
					primerLibro.getLibroId(),
					primerLibro.getTitulo(),
					primerLibro.getAutor().stream().map(DataAuthorDAO::getNombre).collect(Collectors.toList()),
					primerLibro.getIdioma(),
					primerLibro.getGenero().stream()
							.map(g -> g.split("--"))
							.filter(parts -> parts.length > 1)
							.map(parts -> parts[1].trim())
							.findFirst()
							.orElse("No hay género disponible"),
					primerLibro.getCantidadDescargas(),
					primerLibro.getFormats());
		});





	}
}
