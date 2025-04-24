package ru.tbank.fdsspring;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tbank.fdsspring.model.Currency;
import ru.tbank.fdsspring.service.CurrencyService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CurrencyRateJob {

    private final CbrClient cbrClient;
    private final CurrencyService currencyService;

    @PostConstruct
    @Scheduled(cron = "0 0 * * * *")       // каждый час
    public void processRates() {
        List<CurrencyRateDto>  allRates   = cbrClient.fetchRates();
        Map<String, Double> changeRanges = collectChangeRanges();
        List<CurrencyRateDto>  rates   = getSuitableRates(allRates, changeRanges);
        rates.forEach(this::printNotify);
    }

    private Map<String, Double> collectChangeRanges() {
        return currencyService.getCurrencies().stream()
                .filter(c -> c.getPriceChangeRange() != null
                        && !c.getPriceChangeRange().isBlank())
                .collect(Collectors.toMap(
                        Currency::getBaseCurrency,
                        currency -> Double.parseDouble(
                                currency.getPriceChangeRange().replace("%", ""))
                ));
    }

    private List<CurrencyRateDto> getSuitableRates(List<CurrencyRateDto> rates,
                                                  Map<String, Double> changeRanges) {

        return rates.stream()
                .filter(rate -> changeRanges.containsKey(rate.getCode()))
                .filter(rate -> {
                    double actual = percentChange(rate);
                    double range  = changeRanges.get(rate.getCode());
                    if (range >= 0) {
                        return actual >= range;
                    } else {
                        return actual <= range;
                    }
                })
                .toList();
    }

    private void printNotify(CurrencyRateDto rate) {
        double percent = percentChange(rate);
        String direction;
        if (percent < 0) {
            direction = "упал";
        } else {
            direction = "вырос";
        }
        System.out.printf("%s %s на %.2f%%%n",
                rate.getName(),
                direction,
                Math.abs(percent));
    }

    private double percentChange(CurrencyRateDto rate) {
        return (rate.getValue() - rate.getPrevious()) / rate.getPrevious() * 100.0;
    }

}
