package ru.tbank.fdsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tbank.fdsspring.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
