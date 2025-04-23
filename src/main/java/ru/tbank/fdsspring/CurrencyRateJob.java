package ru.tbank.fdsspring;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tbank.fdsspring.model.Currency;
import ru.tbank.fdsspring.service.CurrencyService;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CurrencyRateJob {

    private final CbrClient cbrClient;
    private final CurrencyService currencyService;

    private Stream<CurrencyRateDto> fetchRates() {
        return cbrClient.fetchRates().stream();
    }

    private double percentChange(CurrencyRateDto currencyRateDto) {
        return (currencyRateDto.getValue() - currencyRateDto.getPrevious()) / currencyRateDto.getPrevious() * 100.0;
    }


}
