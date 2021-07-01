package com.portfolio.demo.portfolio;

import javax.persistence.*;

@Entity
@Table
public class holdings {
    //@SequenceGenerator(name = "portfolio_sequence", sequenceName = "portfolio_sequence", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "portfolio_sequence")

    @Id
    private String ticker;
    private double avgPrice;
    private double numShares;

    @Transient
    private double totalInvested;

    public holdings() {
    }

    public holdings(String ticker, double avgPrice, double numShares) {
        this.ticker = ticker;
        this.avgPrice = avgPrice;
        this.numShares = numShares;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public double getTotalInvested(){
        return numShares * avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public double getNumShares() {
        return numShares;
    }

    public void setNumShares(double numShares) {
        this.numShares = numShares;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "ticker='" + ticker + '\'' +
                ", avgPrice=" + avgPrice +
                ", numShares=" + numShares +
                '}';
    }
}
