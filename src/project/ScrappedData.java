package project;

import model.StockData;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrappedData {
    Document doc = null;

    //constructor for scrapping the stock data from google webpage and storing it inside the StockData model
    public ScrappedData(String abbr, StockData sd) {
        try {
            doc = Jsoup.connect("https://www.google.com/search?q="+abbr+"+stocks&oq="+abbr+"+stocks&aqs=chrome..69i57j0l5.5039j1j7&sourceid=chrome&ie=UTF-8").timeout(10*1000).get();
            Element div = doc.getElementById("knowledge-finance-wholepage__entity-summary");
            Elements spans = div.getElementsByTag("span");
            for(Element span: spans){
                if(span.hasClass("NprOob")){
                    String schange = span.text().replaceAll(",", "");
                    sd.setStockprice(Double.valueOf(schange));
                    System.out.println(sd.getStockprice());
                }
                if(span.hasClass("knFDje")){
                    sd.setCurrencytype(span.text());
                    System.out.println(sd.getCurrencytype());
                }
                if(span.hasClass("WlRRw")){
                    String schange = span.text().replaceAll("−", "-");
                    String schange1 = schange.replaceAll("[()%]", "");
                    String[] split = schange1.split(" ");
                    sd.setChange(Double.valueOf(split[0]));
                    sd.setPercentchange(Double.valueOf(split[1]));
                    System.out.println(sd.getChange());
                    System.out.println(sd.getPercentchange());
                }
                if(span.hasClass("TgMHGc"))
                    sd.setUpdatedon(span.text());
                    System.out.println(sd.getUpdatedon());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }    
}
