package shopping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * A test class for the shopping package.
 * @author Son Pham
 * @version 1.0
 */
public class ShoppingTest
{
  /**
   * The precision when compare 2 double.
   */
  private static final double PRECISION = 0.000009;
  
  /**
   * The duplicate name Doll.
   */
  private static final String DUPLICATE_NAME = "Doll";
  
  /**
   * Test Item 1.
   */
  private static final Item ITEM_1 = new Item("CD", 9.50);
  
  /**
   * Test Item 2.
   */
  private static final Item ITEM_2 = new Item(DUPLICATE_NAME, 5.45, 20, 95);
  
  /**
   * Test Item 3.
   */
  private static final Item ITEM_3 = new Item(DUPLICATE_NAME, 5.45, 20, 95);
  
  /**
   * Test Item 4.
   */
  private static final Item ITEM_4 = new Item(DUPLICATE_NAME, 5.45, 19, 90);
  
  /**
   * Test ItemOrder 0.
   */
  private static final ItemOrder ORDER_0 = new ItemOrder(ITEM_4, 3);
  
  /**
   * Test ItemOrder 1.
   */
  private static final ItemOrder ORDER_1 = new ItemOrder(ITEM_1, 3);
  
  /**
   * Test ItemOrder 2.
   */
  private static final ItemOrder ORDER_2 = new ItemOrder(ITEM_2, 19);
  
  /**
   * Test ItemOrder 3.
   */
  private static final ItemOrder ORDER_3 = new ItemOrder(ITEM_3, 21);
  
  /**
   * Test ItemOrder 4.
   */
  private static final ItemOrder ORDER_4 = new ItemOrder(ITEM_2, 5);
  
  /**
   * Price of 3 CDs.
   */
  private static final double PRICE_3_CDS = 28.5;
  
  /**
   * Price of 19 Dolls.
   */
  private static final double PRICE_19_DOLLS = 103.55;
  
  /**
   * Price of 21 CDs.
   */
  private static final double PRICE_21_CDS = 100.45;
  
  /**
   * Price of order 1 without discount.
   */
  private static final double PRICE_ORDER_1_W0_DISCOUNT = 28.5;
  
  /**
   * Price of order 1 with discount.
   */
  private static final double PRICE_ORDER_1_W_DISCOUNT = 25.65;
  
  /**
   * Price of order 1 and 2 without discount.
   */
  private static final double PRICE_ORDER_1_AND_2_W0_DISCOUNT = 132.05;
  
  /**
   * Price of order 1, 2 and 4 without discount.
   */
  private static final double PRICE_ORDER_1_2_AND_4_W0_DISCOUNT = 55.75;
  
  /**
   * Test the ItemOrder class which also test Item class indirectly.
   */
  @Test
  public void testItemOrder()
  {
    assertEquals("Price for 3 CDs", PRICE_3_CDS, ORDER_1.getPrice(), PRECISION);
    assertEquals("Price for 19 Dolls", PRICE_19_DOLLS, ORDER_2.getPrice(), PRECISION);
    assertEquals("Price for 21 CDs", PRICE_21_CDS, ORDER_3.getPrice(), PRECISION);
    assertEquals("Test 2 equal items", ORDER_2.getItem(), ORDER_3.getItem());
    assertEquals("Test hashCode method of Item class", ORDER_2.getItem().hashCode(),
                 ORDER_3.getItem().hashCode());
    assertFalse("Test 2 not equal (but very close to equal) items", 
                ORDER_0.getItem().equals(ORDER_2.getItem()));
  }
  
  /**
   * Test the ShoppingCart class.
   */
  @Test
  public void testShoppingCart()
  {
    final ShoppingCart the_cart = new ShoppingCart();
    the_cart.add(ORDER_1);
    assertEquals("Total of my_order_1 w/o discount", PRICE_ORDER_1_W0_DISCOUNT, 
                 the_cart.getTotal(), PRECISION);
    the_cart.setDiscount(true);
    assertEquals("Total of my_order_1 w discount", PRICE_ORDER_1_W_DISCOUNT, 
                 the_cart.getTotal(), PRECISION);
    the_cart.setDiscount(false);
    the_cart.add(ORDER_2);
    assertEquals("Total of my_order_1 & my_order_2 w/o discount", 
                 PRICE_ORDER_1_AND_2_W0_DISCOUNT, 
                 the_cart.getTotal(), PRECISION);
    the_cart.add(ORDER_4);
    assertEquals("Test to see if my_order_4 replaces my_order_2", 
                 PRICE_ORDER_1_2_AND_4_W0_DISCOUNT, 
                 the_cart.getTotal(), PRECISION);
  }
}
