/*
 * Autumn 2009 TCSS 305 Final Exam
 * Extra Credit - JUnit Testing
 */

package shopping;

/**
 * ItemOrder encapsulates an order of an item (for storage in a shopping
 * cart). 
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @version December 2009
 * @see Item
 * @see ShoppingCart
 */

public class ItemOrder
{
  // Instance Fields
  
  /**
   * The Item contained in this ItemOrder.
   */
  private final Item my_item;
  
  
  /**
   * The quantity represented by this ItemOrder.
   */
  private final int my_quantity;
  
  // Constructor
  
  /**
   * Constructs a new ItemOrder with the specified Item and quantity.
   * 
   * @param the_item The Item.
   * @param the_quantity The quantity.
   */  
  public ItemOrder(final Item the_item, final int the_quantity)
  {
    my_item = the_item;
    my_quantity = the_quantity;
  }

  
  // Instance Methods
  
  /**
   * Returns the price of this ItemOrder.
   * 
   * @return the price of this ItemOrder.
   */
  public double getPrice()
  {
    return my_item.priceFor(my_quantity);
  }
  
  /**
   * Returns the Item contained in this ItemOrder.
   * 
   * @return the Item contained in this ItemOrder.
   */
  public Item getItem()
  {
    return my_item;
  }
}

// end of class ItemOrder
