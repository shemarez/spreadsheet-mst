/*
 * Autumn 2009 TCSS 305 Final Exam
 * Extra Credit - JUnit Testing
 */

package shopping;

import java.util.ArrayList;
import java.util.List;

/**
 * ShoppingCart encapsulates a set of items and quantities to be purchased,
 * and an optional discount on the entire set.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @version December 2009
 */

public class ShoppingCart
{
  // Static Fields
  
  /**
   * The discount to be applied, if applicable; the resulting price is the
   * full price multiplied by this factor (e.g., 0.75 would represent a 25% 
   * discount).
   */
  public static final double DISCOUNT_FACTOR = 0.9;
  
  
  // Instance Fields
  
  /**
   * The list of orders in the shopping cart.
   */
  private final List<ItemOrder> my_orders;
  
  
  /**
   * A flag that indicates whether or not the discount should be applied.
   */
  private boolean my_discount;
  
  
  // Constructor
  
  /**
   * Constructs a new, empty shopping cart.
   */
  public ShoppingCart()
  {
    my_orders = new ArrayList<ItemOrder>();
    my_discount = false;
  }
  
  // Instance Fields
  
  /**
   * Adds an order to the cart; if the order is for an item that already
   * exists in the cart, the old order is replaced by the new one. 
   * 
   * @param the_order The order to add.
   */
  public void add(final ItemOrder the_order)
  {
    for (int i = 0; i < my_orders.size(); i++)
    {
      if (my_orders.get(i).getItem().equals(the_order.getItem()))
      {
        //Wrong code.
        //my_orders.add(i, the_order);
        
        //A fix version of the wrong code.
        my_orders.set(i, the_order);
        return;
      }
    }
    
    // we didn't replace a list item so let's add it to the end
    
    my_orders.add(the_order);
  }

  
  /**
   * Sets the discount flag to the specified value.
   * 
   * @param the_discount The new value of the discount flag.
   */
  public void setDiscount(final boolean the_discount)
  {
    my_discount = the_discount;
  }

  
  /**
   * Returns the total price of the items in the shopping cart, including
   * the discount if applicable.
   * 
   * @return the total price of the items in the shopping cart.
   */
  public double getTotal()
  {
    double sum = 0.0;
    
    for (ItemOrder o : my_orders)
    {
      sum = sum + o.getPrice();
    }
    
    if (my_discount)
    {
      sum = sum * DISCOUNT_FACTOR;
    }
    
    return sum;
  }
}

// end of class ShoppingCart
