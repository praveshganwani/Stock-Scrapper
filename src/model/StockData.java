package model;

//StockData model class with getters and setters for storing and fetching the stock details
public class StockData {

    private double stockprice;
    private double change;
    private double percentchange;
    private String currencytype;
    private String updatedon;

    public double getStockprice() {
        return stockprice;
    }

    public void setStockprice(double stockprice) {
        this.stockprice = stockprice; 
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getPercentchange() {
        return percentchange;
    }

    public void setPercentchange(double percentchange) {
        this.percentchange = percentchange;
    }

    public String getCurrencytype() {
        return currencytype;
    }

    public void setCurrencytype(String currencytype) {
        this.currencytype = currencytype;
    }

    public String getUpdatedon() {
        return updatedon;
    }

    public void setUpdatedon(String updatedon) {
        this.updatedon = updatedon;
    }
}
