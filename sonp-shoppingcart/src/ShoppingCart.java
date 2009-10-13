import java.util.Map;
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
  //Static fields
  
  /**
   * The percentage of the order after discount.
   */
  private static final double  DISCOUNT_RATE = 0.9;
  
  //Instance fields
  
  /**
   * The hash map contains pairs of Item and the price for the purchase of that item.
   */
  private final Map<Item, Double> my_hashmap;
  
  /**
   * The boolean value of discount for the shopping cart. True means this shopping cart 
   * will get a discount. False means this shopping cart won't get a discount.
   */
  private boolean my_discount;
  
  //Constructor
  
  /**
   * Create an empty shopping cart.
   */
  public ShoppingCart()
  {
    my_hashmap = new HashMap<Item, Double>();
  }

  //Instance methods
  
  /**
   * Adds an item order to the cart, replacing any previous order for an equivalent 
   * item with the new order.
   * @param the_order The order needs to be added to the shopping cart
   */
  public void add(final ItemOrder the_order)
  {
    final Item the_order_item = the_order.getItem();
    final double the_order_price = the_order.getPrice();
    
    my_hashmap.put(the_order_item, the_order_price); 
  }

  /**
   * Set whether or not this order gets a discount.
   * @param the_discount The true value of discount. True means this order will get a discount.
   * False means this order will not get a discount.
   */
  public void setDiscount(final boolean the_discount)
  {
    my_discount = the_discount;
  }
  
  /**
   * Calculate and return the total cost for the shopping cart.
   * @return the total cost for the shopping cart
   */
  public double getTotal()
  {
    double the_undiscount_price = 0.0;
    final Iterator<Item> the_iterator = my_hashmap.keySet().iterator();
    
    while (the_iterator.hasNext())
    {
      final Item the_current_item = the_iterator.next();
      final double the_current_order_price = my_hashmap.get(the_current_item);
      
      the_undiscount_price += the_current_order_price;
    }
    
    if (my_discount)
    {
      return DISCOUNT_RATE * the_undiscount_price;
    }
    else 
    {
      return the_undiscount_price;
    }
  }
}
