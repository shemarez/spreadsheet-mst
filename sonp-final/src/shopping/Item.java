/*
 * Autumn 2009 TCSS 305 Final Exam
 * Extra Credit - JUnit Testing
 */

package shopping;

import java.text.NumberFormat;

/**
 * Item encapsulates an item available for purchase, its price, and
 * its bulk discount if any.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @version December 2009
 */

public class Item
{
  // Instance Fields 
  
  /**
   * The name of this item.
   */
  
  private final String my_name;
  
  /**
   * The perItemPrice of this item.
   */
  private final double my_per_item_price;
  
  /**
   * The quantity of this item at which a bulk discount applies. If this is 0,
   * there is no bulk discount.
   */
  private final int my_bulk_quantity;
  
  /**
   * The per-item price for a bulk quantity of the item.
   */
  private final double my_bulk_price;
  
  /**
   * A NumberFormat used in our toString() method to print prices gracefully. 
   */
  private final NumberFormat my_formatter;
  
  
  // Constructors
  
  /**
   * Constructs a new Item with the specified name and per-item price and no
   * bulk discount.
   * 
   * @param the_name The name.
   * @param the_per_item_price The per-item price.
   */
  public Item(final String the_name, 
              final double the_per_item_price)
  {
    this(the_name, the_per_item_price, 0, 0);
  }

  /**
   * Constructs a new Item with the specified name, per-item price, bulk
   * quantity, and bulk price.
   * 
   * @param the_name The name.
   * @param the_per_item_price The per-item price.
   * @param the_bulk_quantity The bulk quantity. If this is 0, the item has no 
   * bulk discount.
   * @param the_bulk_price The bulk price.
   */
  public Item(final String the_name, final double the_per_item_price,
              final int the_bulk_quantity, final double the_bulk_price)
  {
    my_name = the_name;
    my_per_item_price = the_per_item_price;
    my_bulk_quantity = the_bulk_quantity;
    my_bulk_price = the_bulk_price;
    my_formatter = NumberFormat.getCurrencyInstance();
  }

  // Instance Methods
  
  /**
   * Returns the total price for the specified quantity of items.
   * 
   * @param the_quantity The quantity.
   * @return the total price for the specified quantity of items.
   */
  public double priceFor(final int the_quantity)
  {
    double result;
    
    if ((my_bulk_quantity > 0) && (the_quantity > my_bulk_quantity))
    {
      final int bulk_q = the_quantity / my_bulk_quantity;
      final int extra_q = the_quantity % my_bulk_quantity;
      result = bulk_q * my_bulk_price + extra_q * my_per_item_price;
    }
    else
    {
      result = the_quantity * my_per_item_price;
    }
    
    return result;
  }

  
  /**
   * Generates a String representation of this Item of the form
   * "name, per-item price (bulk quantity for bulk price)"; the
   * parenthetical does not appear if there is no bulk discount.
   * 
   * @return a String representation of this Item.
   */
  public String toString()
  {
    final StringBuilder result = new StringBuilder();
    result.append(my_name);
    result.append(", ");
    result.append(my_formatter.format(my_per_item_price));
    
    if (my_bulk_quantity > 0)
    {
      result.append(" (");
      result.append(my_bulk_quantity);
      result.append(" for ");
      result.append(my_formatter.format(this.my_bulk_price));
      result.append(")");
    }
    
    return result.toString();
  }
  
  /**
   * {@inheritDoc}
   */
  public boolean equals(final Object the_other) 
  {
    boolean result = the_other != null && the_other.getClass() == getClass();
    if (result) 
    {
      final Item other_item = (Item) the_other;
      result = other_item.my_name.equals(my_name);
      result = result && other_item.my_per_item_price == my_per_item_price;
      result = result && other_item.my_bulk_quantity == my_bulk_quantity;
      result = result && other_item.my_bulk_price == my_bulk_price;
    }
    return result;
  }
  
  /**
   * {@inheritDoc}
   */
  public int hashCode()
  {
    return my_name.hashCode();
  }
}

// end of class Item 
