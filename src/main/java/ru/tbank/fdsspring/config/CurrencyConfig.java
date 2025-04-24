package ru.tbank.fdsspring.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@AllArgsConstructor
@ConfigurationProperties(prefix = "currency-tracker")
public class CurrencyConfig {
    private String cbApiUrl;
}
