package ru.tbank.fdsspring.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class CurrencyRequest {
    private String name;
    private String baseCurrency = "RUB";
    private String priceChangeRange;
    private String description;
}
