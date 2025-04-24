package ru.tbank.fdsspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.fdsspring.entity.Currency;
import ru.tbank.fdsspring.model.CurrencyRequest;
import ru.tbank.fdsspring.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<Currency>> getCurrencies() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(currencyService.getCurrencies());
    }

    @PostMapping
    public ResponseEntity<String> addCurrency(@RequestBody CurrencyRequest currencyRequest) {
        currencyService.addCurrency(currencyRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Валюта успешно добавлена");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable Long id) {
        for (Currency currency : currencyService.getCurrencies()) {
            if (currency.getId().equals(id)) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(currency);
            }
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable Long id, @RequestBody CurrencyRequest currencyRequest) {
        Currency updatedCurrency = currencyService.updateCurrency(id, currencyRequest);
        if (updatedCurrency == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(updatedCurrency);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCurrency(@PathVariable Long id) {
        if (currencyService.getCurrencyById(id) != null) {
            currencyService.deleteCurrency(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Валюта успешно удалена");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Валюта не найдена");
        }
    }
}

