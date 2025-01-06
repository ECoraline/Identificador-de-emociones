package pruebas;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class CohereEmotionHandling {
    public static void main(String[] args) {
        // Crear la interfaz gráfica
        CohereGUI gui = new CohereGUI();

        // Agregar un listener al botón "Procesar"
        gui.addProcessButtonListener(e -> {
            // Obtener el texto ingresado por el usuario
            String userInput = gui.getInputText();
            if (userInput == null || userInput.isEmpty()) {
                gui.showResult("Por favor, ingresa un texto para procesar.");
                return;
            }

            // Llamar a la API de Cohere con el texto ingresado
            String apiKey = "Kxad2wFuEQDeRvuEOSgI8uX6FT7TOYN8A7mcrCVn";
            String endpoint = "https://api.cohere.ai/v1/generate";

            String jsonInput = String.format("""
                {
                    "model": "command-xlarge-nightly",
                    "prompt": "Identifica las emociones en este texto: '%s' Y da una recomendacion para manejar esas emociones (responde sin signos de puntuacion ni acentos).",
                    "max_tokens": 100
                }
                """, userInput);

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

                // Parsear la respuesta JSON con JSONObject
                JSONObject jsonResponse = new JSONObject(response.toString());
                String generatedText = jsonResponse.getJSONArray("generations")
                        .getJSONObject(0)
                        .getString("text");

                // Mostrar el resultado en la interfaz
                gui.showResult("Respuesta generada:\n" + generatedText);

            } catch (Exception ex) {
                ex.printStackTrace();
                gui.showResult("Ocurrió un error al procesar el texto.");
            }
        });
    }
}

