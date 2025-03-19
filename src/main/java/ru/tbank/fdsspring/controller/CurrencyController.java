package ru.tbank.fdsspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.fdsspring.model.Currency;
import ru.tbank.fdsspring.model.CurrencyRequest;
import ru.tbank.fdsspring.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private  final CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List> getCurrencies() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(currencyService.getCurrencies());
    }

    @PostMapping
    public ResponseEntity<String> addCurrency(@RequestBody CurrencyRequest currencyRequest) {
        Currency currency = new Currency();
        if (currencyRequest.getName().isEmpty() ||
                currencyRequest.getBaseCurrency().isEmpty() ||
                currencyRequest.getPriceChangeRange().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Неккоректные данные в запросе");
        }
        currency.setName(currencyRequest.getName());
        currency.setBaseCurrency(currencyRequest.getBaseCurrency());
        currency.setPriceChangeRange(currencyRequest.getPriceChangeRange());
        currency.setDescription(currencyRequest.getDescription());
        currencyService.addCurrency(currency);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Валюта успешно добавлена");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCurrency(@PathVariable String id) {
        for (Currency currency : currencyService.getCurrencies()) {
            if (currency.getId().equals(id)) {
                return ResponseEntity.ok(currency.toString());
            }
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Валюта не найдена");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCurrency(@PathVariable String id, @RequestBody CurrencyRequest currencyRequest) {
        for (Currency currency : currencyService.getCurrencies()) {
            if (currency.getId().equals(id)) {
                currency.setName(currencyRequest.getName());
                currency.setBaseCurrency(currencyRequest.getBaseCurrency());
                currency.setPriceChangeRange(currencyRequest.getPriceChangeRange());
                currency.setDescription(currencyRequest.getDescription());
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Данные валюты успешно обновлены");
            }
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Валюта не найдена");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCurrency(@PathVariable String id) {
        if (currencyService.deleteCurrency(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Валюта успешно удалена");
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Валюта не найдена");
        }
    }
}

