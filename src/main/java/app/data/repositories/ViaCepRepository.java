package app.data.repositories;

import app.data.mappers.AddressViaCep;
import app.entities.Address;
import com.google.gson.Gson;
import lombok.SneakyThrows;

import javax.ejb.Stateless;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.*;

@Stateless
public class ViaCepRepository {
    private final static String BASE_URL = "https://viacep.com.br/ws/";
    private final HttpClient client = HttpClient.newHttpClient();

    @SneakyThrows
    public Address find(String zipcode) {
        String endpoint = BASE_URL + zipcode + "/json";
        HttpRequest request = newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return parseResponse(response.body());
    }

    @SneakyThrows
    private Address parseResponse(String json) {
        Gson gson = new Gson();
        AddressViaCep addressViaCep = gson.fromJson(json, AddressViaCep.class);
        return addressViaCep.parse();
    }
}
