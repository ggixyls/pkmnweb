package gevorgyan.pkmn.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем незнакомые поля
public class CardResponse {
    private List<CardData> data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем незнакомые поля
    public static class CardData {
        private String id;
        private String name;

        @JsonProperty("images")
        private Images images;

        @Data
        public static class Images {
            private String small;
            private String large;
        }
    }
}