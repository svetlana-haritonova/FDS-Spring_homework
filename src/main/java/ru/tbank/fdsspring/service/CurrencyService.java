package ru.tbank.fdsspring.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.tbank.fdsspring.model.Currency;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class CurrencyService {
    private final List<Currency> currencies = new ArrayList<>();

    public void addCurrency(Currency currency) {
        currencies.add(currency);
        return;
    }

    public boolean deleteCurrency(String id) {
        return currencies.removeIf(currency -> currency.getId().equals(id));
    }
}