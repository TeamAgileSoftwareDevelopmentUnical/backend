package asd.amazon.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PayPalConfig {
    @Value("${paypal.client_id}")
    private String paypalClientId;

    @Value("${paypal.secret}")
    private String paypalSecret;

    @Value("${paypal.mode}")
    private String paypalTransactionMode;

    @Bean
    public Map<String,String> paypalSdkConfig() {
        Map<String,String> configMap = new HashMap<>();
        configMap.put("mode", paypalTransactionMode);
        return configMap;
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        return new APIContext(paypalClientId, paypalSecret, paypalTransactionMode, paypalSdkConfig());
    }
}
