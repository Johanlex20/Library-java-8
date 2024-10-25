package com.library.demo;

import com.library.demo.config.ConsumoApiGutendex;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("Probando Api");

		ConsumoApiGutendex consumoApi = new ConsumoApiGutendex();
		String json = consumoApi.obtenerDatos("Dracula");
		System.out.println(json);

	}
}
