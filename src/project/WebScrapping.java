package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.StockData;

//running a thread to keep getting the stock data continously
public class WebScrapping extends Thread{
    
    Connection con;
    String abbr;
    StockData stckd = new StockData();
    
    @Override
    public void run() {
        //establishing the connection with the database for fetching the stock names from database
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/projectdb","projectdb","projectdb");
        } 
        catch (Exception e) {
            System.out.println(e);
        }
            while(true){
                for(int i=1; i<11; i++){
                try {
                    PreparedStatement ps = con.prepareStatement("select * from stocks where stockid=?");
                    ps.setString(1, ""+i);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        abbr = rs.getString("stockabbr");
                        System.out.println(abbr);
                        Thread.sleep(3000);
                        //call to ScrappedData for getting the stockdata
                        ScrappedData sd = new ScrappedData(abbr.toLowerCase(), stckd);
                    }
                    ps = con.prepareStatement("update stocks set stockprice=?, currencytype=?, change=?, percentchange=?, updatedon=? where stockid=?");
                    System.out.println(stckd.getStockprice());
                    System.out.println(stckd.getCurrencytype());
                    System.out.println(stckd.getChange());
                    System.out.println(stckd.getPercentchange());
                    System.out.println(stckd.getUpdatedon());
                    ps.setDouble(1, stckd.getStockprice());
                    ps.setString(2, stckd.getCurrencytype());
                    ps.setDouble(3, stckd.getChange());
                    ps.setDouble(4, stckd.getPercentchange());
                    ps.setString(5, stckd.getUpdatedon());
                    ps.setInt(6, i);
                    ps.executeUpdate();
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
    public static void main(String[] args) {
        WebScrapping ws = new WebScrapping();
        ws.start();
        JFrame jf = new JFrame();
        JOptionPane.showMessageDialog(jf, "Stock Scrapping has been started!");
    }
}
