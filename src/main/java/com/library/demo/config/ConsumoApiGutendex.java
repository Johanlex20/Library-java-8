package com.library.demo.config;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsumoApiGutendex {

    public String obtenerDatos(String title){

        StringBuilder response = new StringBuilder();
        String apiUrl = "https://gutendex.com/books/?search=" + title;

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
                connection.setRequestMethod("GET");

                int status = connection.getResponseCode();

                if (status == 200) {

                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                    }
                } else {
                    System.out.println("Error en la respuesta: " + status);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        return response.toString();
    }


}

