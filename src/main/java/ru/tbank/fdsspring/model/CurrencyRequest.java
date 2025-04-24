package ru.tbank.fdsspring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CurrencyRequest {
    private String name;
    private String baseCurrency;
    private String priceChangeRange;
    private String description;
}
