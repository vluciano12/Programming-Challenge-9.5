import java.util.*;

  /**
     The ShoppingCart class has an ArrayList of Items.
     You will write a new class DiscountedItem that extends Item.
       */

  public class Tester
  {
    public static void main(String[] args) {
      ShoppingCart cart = new ShoppingCart();
      cart.add(new Item("bread", 3.25));
      cart.add(new Item("milk", 2.50));

      // Uncomment these to test
      cart.add(new DiscountedItem("ice cream", 4.50, 1.50));
      cart.add(new DiscountedItem("apples", 1.35, 0.25));

      cart.printOrder();
    }
  }

  // DiscountedItem inherits from Item
  class DiscountedItem extends Item
  {
      // add an instance variable for the discount
      private double discount;
      // Add constructors that call the super constructor
      public DiscountedItem(String n, double p, double d){
        super(n, p);
        discount = d;
      }
      // Add get/set methods for discount
      public double getDiscount(){
        return discount; // return discount here instead of 0
      }

      // Add a toString() method that returns a call to the super toString
      // and then the discount in parentheses using the super.valueToString() method
      public String toString(){
        return super.toString() + " discount: " + super.valueToString(discount);
      }
  }


  class ShoppingCart
  {
      private ArrayList<Item> order;
      private double total;
      private double internalDiscount;

      public ShoppingCart()
      {
          order = new ArrayList<Item>();
          total = 0.0;
          internalDiscount = 0.0;
      }

      public void add(Item i) {
          order.add(i);
          total += i.getPrice();
          if (i instanceof DiscountedItem)
             internalDiscount += ((DiscountedItem) i).getDiscount();
      }

     /** printOrder() will call toString() to print */
      public void printOrder() {
          System.out.println(this);
      }

      public String toString() {
          return discountToString();
      }

      public String discountToString() {
          return orderToString() + "\nSub-total: " + valueToString(total) + "\nDiscount: " + valueToString(internalDiscount) + "\nTotal: " +      valueToString(total - internalDiscount);
      }

      private String valueToString(double value) {
          value = Math.rint(value * 100) / 100.0;
          String result = "" + Math.abs(value);
          if(result.indexOf(".") == result.length() - 2) {
              result += "0";
          }
          result = "$" + result;
          return result;
      }

      public String orderToString() {
          String build = "\nOrder Items:\n";
          for(int i = 0; i < order.size(); i++) {
              build += "   " + order.get(i);
              if(i != order.size() - 1) {
                  build += "\n";
              }
          }
          return build;
      }
    }


    class Item {
      private String name;
      private double price;

      public Item()
      {
        this.name = "";
        this.price = 0.0;
      }

      public Item(String name, double price) {
              this.name = name;
              this.price = price;
      }

      public double getPrice() {
              return price;
      }

      public String valueToString(double value) {
              String result = "" + Math.abs(value);
              if(result.indexOf(".") == result.length() - 2) {
                  result += "0";
              }
              result = "$" + result;
              return result;
      }

      public String toString() {
              return name + " " + valueToString(price);
      }
     }
     