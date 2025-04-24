package ru.tbank.fdsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tbank.fdsspring.entity.Currency;

import java.util.stream.Stream;


@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query(value = """
        SELECT * FROM currency;
    """, nativeQuery = true)
    Stream<Currency> findAllCurrencies();

}
