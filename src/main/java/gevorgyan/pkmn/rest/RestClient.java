package gevorgyan.pkmn.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {
    private final RestTemplate restTemplate;

    @Autowired
    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CardResponse getCardByName(String cardName) {
        try {
            String url = "https://api.pokemontcg.io/v2/cards?q=name:" + cardName;
            System.out.println("Вызов внешнего API: " + url);

            String jsonResponse = restTemplate.getForObject(url, String.class);
            System.out.println("Ответ API: " + jsonResponse);

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonResponse, CardResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при вызове API: " + e.getMessage());
        }
    }
}