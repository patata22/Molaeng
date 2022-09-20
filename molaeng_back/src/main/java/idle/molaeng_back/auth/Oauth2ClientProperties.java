package idle.molaeng_back.auth;


import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "spring.security.oauth2.client")
public class Oauth2ClientProperties {

    private final Map<String, Provider> provider = new HashMap<>();
    private final Map<String, Registration> registration = new HashMap<>();
    public Map<String, Provider> getProvider() {
        return this.provider;
    }
    public Map<String, Registration> getRegistration (){
        return this.registration;
    }

    @PostConstruct
    public void validate() {
        this.getRegistration().values().forEach(this::validateRegistration);
    }

    private void validateRegistration(Registration registration){};

    public static class Registration{
        private String provider;
        private String clientId;
        private String clientSecret;
    }

}
