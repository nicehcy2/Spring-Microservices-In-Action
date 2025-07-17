import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(ClientRegistrationRepository clientRegistrations,
                               OAuth2AuthorizedClientRepository authorizedClients) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrations, authorizedClients);

        oauth2.setDefaultOAuth2AuthorizedClient(true); // 기본 인증 클라이언트 사용
        return WebClient.builder()
                .apply(oauth2.oauth2Configuration())
                .build();
    }
}
