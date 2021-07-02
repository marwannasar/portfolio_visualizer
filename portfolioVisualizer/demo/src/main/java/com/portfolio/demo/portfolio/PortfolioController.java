package com.portfolio.demo.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService stockInfo) {
        this.portfolioService = stockInfo;
    }

    @RequestMapping("/stocks")
    public List<holdings> getStockInfo(){
        return portfolioService.getStockInfo();
    }

    @RequestMapping("/stocks/{stockTicker}")
    public List<holdings> getSpecificStockInfo(@PathVariable ("stockTicker") String ticker){
        return portfolioService.getSpecificStockInfo(ticker);
    }

    @PostMapping(path="/stocks")
    public String addStock(@RequestBody holdings stock){
        portfolioService.addNewStock(stock);
        return "update complete (stock added)";
    }


    @DeleteMapping(path = "/stocks/{stockTicker}")
    public void deleteStock(@PathVariable ("stockTicker") String ticker){
        portfolioService.deleteStock(ticker);
    }
}

