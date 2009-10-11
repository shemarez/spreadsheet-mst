import java.text.NumberFormat;

/**
 * Item stores information about an individual item.
 * @author Son Pham
 * @version 1.0
 *
 */
public class Item 
{
  //Instance fields
  
  /**
   * The name of the item
   */
  private final String my_name;
  
  /**
   * The price of a single item
   */
  private final double my_price;
  
  /**
   * The price for a bulk of item
   */
  private final double my_bulk_price;
  
  /**
   * The quantity of a bulk of item
   */
  private final int my_bulk_quantity;
  
  //Constructors
  
  /**
   * Constructs an item with specified name and price. Unspecified bulk quantity and price will be
   * set to 0.
   * @param the_name The name of the item
   * @param the_price The price of a single item
   */
  public Item(final String the_name, final double the_price) 
  {
    this(the_name, the_price, 0, 0.0);
  }

  /**
   * Constructs an item with specified name, price, bulk_quanity and bulk_price.
   * @param the_name The name of the item
   * @param the_price The price of a single item
   * @param the_bulk_quantity The bulk quantity of the item
   * @param the_bulk_price The price for the bulk quantity of the item
   */
  public Item(final String the_name, final double the_price, 
              final int the_bulk_quantity, final double the_bulk_price) 
  {
    my_name = the_name;
    my_price = the_price;
    my_bulk_quantity = the_bulk_quantity;
    my_bulk_price = the_bulk_price;
  }
  
  //Instance methods

  /**
   * Calculate the price for an input of quantity.
   * @param the_quantity The quantity need to be calculated
   * @return The price for the input quantity
   */
  public double priceFor(final int the_quantity) 
  {
    final int the_bulk_quantity = the_quantity / my_bulk_quantity;
    final int the_item_quantity = the_quantity % my_bulk_quantity;
    
    return (double) (the_bulk_quantity * my_bulk_price + the_item_quantity * my_price);
  }

  // methods overridden from java.lang.Object

  /**
   * @return The string representation of the item. It will output the name, price, bulk_quantity
   * and bulk_price of the item.
   */
  public String toString() 
  {
    NumberFormat the_nf = NumberFormat.getCurrencyInstance();
    String my_format_price = the_nf.format(my_price);
    String my_format_bulk_price = the_nf.format(my_bulk_price);
    
    if (my_bulk_quantity == 0) 
    {
      return my_name + ", " + my_format_price;
    }
    else 
    {
      return my_name + ", " + my_format_price + "(" + my_bulk_quantity + " for " + my_format_bulk_price + ")";
    }
  }
  
  /**
   * Compares two items to see if they are equal or not. 
   * 
   * @param the_other The other item
   * @return true if two item have the same name, price, bulk_quantity, and bulk_price.
   * Return false otherwise.
   */
  public boolean equals(final Object the_other)
  {
    if (the_other != null && the_other.getClass() == getClass())
    {
      final Item other_item = (Item) the_other;
      boolean result = (my_name == other_item.my_name) && (my_price == other_item.my_price)
                       && (my_bulk_quantity == other_item.my_bulk_quantity) 
                       && (my_bulk_price == other_item.my_bulk_price);
      return result;
    }
    else 
    {
      return false;
    }
  }
  
  /**
   * @return a hash code for this item
   */
  public int hashCode()
  {
    return toString().hashCode();
  }
}
