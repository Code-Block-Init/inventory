import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Product implements Comparable {
    String itemName;     // class variable that stores the item name
    double itemNumber;     // class variable that stores the item number
    long stockQuantity; // class variable that stores the quantity in stock
    double price;     // class variable that stores the item price

    public Product() {
        itemName = "";
        itemNumber = 0.0;
        stockQuantity = 0L;
        price = 0.0;

    }
    public Product(String name, int number, long stockQuantity, double price) {
        this.itemName = name;
        this.itemNumber = number;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }
    public void setItemName(String name) {
        this.itemName = itemName;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemNumber(double number) {
        this.itemNumber = itemNumber;
    }
    public double getItemNumber() {
        return itemNumber;
    }
    public void setStockQuantity(long quantity) {
        stockQuantity = quantity;
    }
    public long getStockQuantity() {
        return stockQuantity;
    }
    public void setItemPrice(double price) {
        this.price = price;
    }
    public double getItemPrice() {
        return price;
    }
    public double calculateInventoryValue() {
        return getItemPrice() * getStockQuantity();
    }
    public int compareTo (Object o) {
        Product p = (Product)o;
        return itemName.compareTo(p.getItemName());
    }
    public String toString() {
        return "Title :"+getItemName() + "\nStock Number"+itemNumber+"\nPrice"+price+"\nQuantity"+stockQuantity + "\nValue :"+calculateInventoryValue();
    }

}

class DVDTitle extends Product implements Comparable {
    private String rating;
    public DVDTitle() {
        super(); //Call the constructor in Product
        rating = ""; //Add the additonal attribute
    }
    public DVDTitle(String itemName, int itemNumber, long stockQuantity, double price, String rating) {
        super(itemName, itemNumber, stockQuantity, price); //Call the constructor in Product
        this.rating = rating; //Add the additonal attribute
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getRating() {
        return rating;
    }
    public double calculateInventoryValue() {
        return getItemPrice() * getStockQuantity() + getItemPrice()*getStockQuantity()*0.05; //Had you forgotten to include the restocking fee?
    }
    //What happens when we want to change the restocking fee?
    public double calculateRestockFee() {
        return getItemPrice() * 0.05;
    }
    public int compareTo (Object o) {
        Product p = (Product)o;
        return getItemName().compareTo(p.getItemName());
    }
    public String toString() {
        return "Name :"+getItemName() + "\nNumber"+getItemNumber()+"\nPrice"+getItemPrice()+"\nQuantity"+getStockQuantity() +"\nRating :"+getRating()+"\nValue"+calculateInventoryValue();
    }
}