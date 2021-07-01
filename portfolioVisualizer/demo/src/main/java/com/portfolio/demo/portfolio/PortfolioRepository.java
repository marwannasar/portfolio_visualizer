package com.portfolio.demo.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<holdings, String> {

    //@Query("SELECT s FROM holdings s WHERE s.ticker = ?1")
    Optional<holdings> findStockByTicker(String ticker);
}
