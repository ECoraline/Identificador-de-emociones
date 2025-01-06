package pruebas;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.json.JSONObject;

public class ConsumoAPI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Tu API Key de Cohere
        String apiKey = "Kxad2wFuEQDeRvuEOSgI8uX6FT7TOYN8A7mcrCVn";

        // Endpoint de la API de Cohere para generación de texto
        String endpoint = "https://api.cohere.ai/v1/generate";

        //interaccion con el usuario
        System.out.println("Dime como te sientes para ayudarte a identificar tus emociones: :");
        String texto = sc.nextLine();

        // Cuerpo de la solicitud JSON
        String jsonInput = """
                {
                    "model": "command-xlarge-nightly",
                    "prompt": "Identifica las emociones en este texto: '%s' Y da una recomendación para manejar esas emociones (responde sin acentos).",
                    "max_tokens": 150
                }
            """.formatted(texto);

        try {
            // Configurar la conexión
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Enviar el cuerpo de la solicitud
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Leer la respuesta
            int status = connection.getResponseCode();
            InputStream inputStream = (status >= 200 && status < 300)
                    ? connection.getInputStream()
                    : connection.getErrorStream();

            StringBuilder response = new StringBuilder();
            try (InputStream is = inputStream) {
                int ch;
                while ((ch = is.read()) != -1) {
                    response.append((char) ch);
                }
            }
            // Mostrar la respuesta
            //System.out.println("Respuesta de la API:");
            System.out.println(response);

            // parseando la respuesta de la api
            // Parsear la respuesta JSON con JSONObject
            JSONObject jsonResponse = new JSONObject(response.toString());
            // Extraer solo la respuesta generada
            String generatedText = jsonResponse.getJSONArray("generations")
                    .getJSONObject(0)
                    .getString("text");

            // Mostrar solo la respuesta generada
            System.out.println("Respuesta generada: ");
            System.out.println(generatedText);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
