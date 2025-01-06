package Principal;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ConsultaAPI {

    public String Consultar(String textoUsuario) {
        String apiKey = "YOUR_API_KEY";

        // Endpoint de la API de Cohere para generación de texto
        String endpoint = "https://api.cohere.ai/v1/generate";


        // Cuerpo de la solicitud JSON
        String jsonInput = """
                    {
                        "model": "command-r-plus-08-2024",
                        "prompt": "Identifica las emociones primarias en este texto: '%s' Y da una recomendación para manejar esas emociones.(respuesta en español y maximo 300 ccaracteres).",
                        "max_tokens": 301
                    }
                """.formatted(textoUsuario);

        try {
            //Configurar la conexión
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
            String response;
            try (InputStream is = inputStream) {
                response = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }

            // Parsear la respuesta JSON con JSONObject
            JSONObject jsonResponse = new JSONObject(response.toString());
            // Extraer solo la respuesta generada
            String generatedText = jsonResponse.getJSONArray("generations")
                    .getJSONObject(0)
                    .getString("text");
            //return generatedText;
            return "\nRespuesta generada: \n" + generatedText.replace(".",".\n");

        } catch (Exception e) {
            return "Ocurrió un error al procesar el texto." + e.getMessage();
        }
    }
}
