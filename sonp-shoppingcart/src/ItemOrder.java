/**
 * ItemOrder stores information about a purchase order for a particular item.
 * @author Son
 * @version 1.0
 *
 */
public class ItemOrder
{
  //Instance fields
  
  /**
   * The item of this order.
   */
  private final Item my_item;
  
  /**
   * The quantity of this order.
   */
  private final int my_quantity;
  
  //Constructors
  
  /**
   * Constructs an ItemOrder with specified item and quantity.
   * @param the_item The item which is purchased
   * @param the_quantity The quantity which is purchased
   */
  public ItemOrder(final Item the_item, final int the_quantity)
  {
    my_item = the_item;
    my_quantity = the_quantity;
  }
  
  //Instance methods

  /**
   * @return the cost for this item order
   */
  public double getPrice()
  {
    return my_item.priceFor(my_quantity);
  }

  /**
   * @return a reference to the item of this order 
   */
  public Item getItem()
  {
    return my_item;
  }
}
