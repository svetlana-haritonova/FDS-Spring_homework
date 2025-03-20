package ru.tbank.fdsspring.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Currency {
    private String id;
    private String name;
    private String baseCurrency;
    private String priceChangeRange;
    private String description;
}
