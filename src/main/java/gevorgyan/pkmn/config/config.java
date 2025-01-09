package gevorgyan.pkmn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class config {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}