import java.util.HashMap;
import java.util.Iterator;

/**
 * ShoppingCart stores information about customer's overall purchase.
 * @author Son
 * @version 1.0
 *
 */
public class ShoppingCart
{
  private HashMap<Item, Double> my_hashmap; 
  private boolean my_discount;
  
  public ShoppingCart()
  {
    my_hashmap = new HashMap<Item, Double>();
  }

  public void add(final ItemOrder the_order)
  {
    Item the_order_item = the_order.getItem();
    double the_order_price = the_order.getPrice();
    
    my_hashmap.put(the_order_item, the_order_price); 
  }

  public void setDiscount(final boolean the_discount)
  {
    my_discount = the_discount;
  }

  public double getTotal()
  {
    double the_undiscount_price = 0.0;
    Iterator<Item> the_iterator = my_hashmap.keySet().iterator();
    while (the_iterator.hasNext())
    {
      Item the_current_item = the_iterator.next();
      double the_current_order_price = my_hashmap.get(the_current_item);
      
      the_undiscount_price += the_current_order_price;
    }
    if (my_discount)
    {
      return 0.9 * the_undiscount_price;
    }
    else return the_undiscount_price;
  }
}
