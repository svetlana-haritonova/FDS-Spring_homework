package ru.tbank.fdsspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.fdsspring.model.Currency;
import ru.tbank.fdsspring.model.CurrencyRequest;
import ru.tbank.fdsspring.repository.CurrencyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Transactional(readOnly = true)
    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    @Transactional
    public void addCurrency(CurrencyRequest currencyRequest) {
        Currency currency = new Currency();
        currency.setName(currencyRequest.getName());
        currency.setBaseCurrency(currencyRequest.getBaseCurrency());
        currency.setPriceChangeRange(currencyRequest.getPriceChangeRange());
        currency.setDescription(currencyRequest.getDescription());
        currencyRepository.save(currency);
    }

    @Transactional
    public Currency updateCurrency(Long id, CurrencyRequest currencyRequest) {
        Currency updateCurrency = currencyRepository.findById(id).orElseThrow(() -> new RuntimeException("Currency not found"));
        updateCurrency.setName(currencyRequest.getName());
        updateCurrency.setBaseCurrency(currencyRequest.getBaseCurrency());
        updateCurrency.setPriceChangeRange(currencyRequest.getPriceChangeRange());
        updateCurrency.setDescription(currencyRequest.getDescription());
        return currencyRepository.save(updateCurrency);
    }

    @Transactional(readOnly = true)
    public Currency getCurrencyById(Long id) {
        return currencyRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }
}