import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class divisaAPI {

    public static String obtenerConversion(String monedaInicial, String monedaFinal, String cantidad ) throws IOException, InterruptedException {
        String divisa =
                "https://v6.exchangerate-api.com/v6/0c0a72198c7e589902301ba5/pair/"
                        + monedaInicial + "/" + monedaFinal + "/" + cantidad + "/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(divisa))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}


