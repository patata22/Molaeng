package idle.molaeng_back.auth;

import org.springframework.boot.autoconfigure.security.oauth2.client.ClientsConfiguredCondition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Oauth2ClientProperties.class)
@Conditional(ClientsConfiguredCondition.class)
public class OAuth2ClientRegistrationRepositoryConfiguration {
    private final Oauth2ClientProperties properties;


    OAuth2ClientRegistrationRepositoryConfiguration(Oauth2ClientProperties properties){
        this.properties = properties;
    }

    
}
