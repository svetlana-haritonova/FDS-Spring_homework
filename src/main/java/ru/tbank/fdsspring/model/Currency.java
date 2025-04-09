package ru.tbank.fdsspring.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String baseCurrency = "RUB";
    private String priceChangeRange;
    private String description;

    public Currency(String name, String baseCurrency, String priceChangeRange, String description) {
        this.name = name;
        this.baseCurrency = baseCurrency;
        this.priceChangeRange = priceChangeRange;
        this.description = description;
    }
}
