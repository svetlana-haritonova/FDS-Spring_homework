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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Валюта успешно добавлена");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCurrency(@PathVariable String id) {
        for (Currency currency : currencyService.getCurrencies()) {
            if (currency.getId().equals(id)) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Валюта найдена");
            }
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Валюта не найдена");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCurrency(@PathVariable String id, @RequestBody CurrencyRequest currencyRequest) {
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

