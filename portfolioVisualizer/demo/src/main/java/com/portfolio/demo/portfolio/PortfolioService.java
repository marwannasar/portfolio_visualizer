package com.portfolio.demo.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public List<holdings> getStockInfo(){
        return portfolioRepository.findAll();
    }

    public List<holdings> getSpecificStockInfo(String ticker) {
        holdings theStock = portfolioRepository.findById(ticker).orElse(null);
        //System.out.println(List.of(theStock));
        return List.of(theStock);
    }

    public void addNewStock(holdings stock) {
        Optional<holdings> stockByTicker = portfolioRepository.findStockByTicker(stock.getTicker());

        if (stockByTicker.isPresent()){
            //code for adding up (update)
            //@Query("UPDATE holdings SET num_shares = 1.0, avg_price = 100.0 WHERE ticker = 'MSFT';")
            holdings duplicateStock = portfolioRepository.findById(stock.getTicker()).orElse(null);
            duplicateStock.setNumShares(duplicateStock.getNumShares() + stock.getNumShares());
            duplicateStock.setAvgPrice(duplicateStock.getAvgPrice()*((duplicateStock.getNumShares() - stock.getNumShares())/ duplicateStock.getNumShares()) + (stock.getAvgPrice()*(stock.getNumShares()/ duplicateStock.getNumShares())));
            portfolioRepository.save(duplicateStock);
        }
        else{
            portfolioRepository.save(stock);
        }
    }

    public void deleteStock(String ticker) {
        boolean exists = portfolioRepository.existsById(ticker);
        if (exists == false){
            throw new IllegalStateException("ticker " + ticker + " does not exist");
        }
        else{
            portfolioRepository.deleteById(ticker);
        }
    }

}
