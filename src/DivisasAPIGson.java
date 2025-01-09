import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class DivisasAPIGson {

    public static void obtenerDivisas() throws IOException {
        String urlString = "https://api.exchangerate-api.com/v6/latest";

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

            if (jsonObject.has("rates")) {
                JsonObject ratesObject = jsonObject.getAsJsonObject("rates");

                System.out.println("Divisas soportadas por la API:");
                for (Map.Entry<String, JsonElement> entry : ratesObject.entrySet()) {
                    String currencyCode = entry.getKey();
                    System.out.println(currencyCode);
                }
            } else if (jsonObject.has("error")) {
                String error = jsonObject.get("error").getAsString();
                System.err.println("Error de la API: " + error);
            }
            else {
                System.err.println("Formato JSON inesperado: 'rates' no encontrado.");
                System.err.println(response); // Imprime la respuesta completa para depuración
            }

        } catch (IOException e) {
            System.err.println("Error al leer la respuesta de la API: " + e.getMessage());
        } finally {
            connection.disconnect(); // Desconectar la conexión
        }
    }

    public static void main(String[] args) throws IOException {
        obtenerDivisas();
    }
}