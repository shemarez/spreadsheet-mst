/*
 * Autumn 2009 TCSS 305 Midterm Exam #1
 * Question 3 - Ticket Class Refactoring
 */

package simpleairlines;

import java.util.Date;

/**
 * An economy class ticket on Simple Airlines.
 * 
 * @author Daniel M. Zimmerman
 * @author Son Pham
 * @version Autumn 2009
 */
public class EconomyTicket extends Ticket
{
  // Static Fields
  
  /**
   * The normal price of an economy class ticket.
   */
  public static final double NORMAL_PRICE = 125.00; 

  /**
   * The "late purchase" price of an economy class ticket.
   */
  public static final double LATE_PRICE = 250.00;
  
  // Constructor
  
  /**
   * Constructs an economy class ticket on Simple Airlines for the specified
   * flight at the specified date and time, purchased at the specified date and time.
   * 
   * @param the_flight_number The flight number.
   * @param the_flight_date The flight date and time.
   * @param the_purchase_date The purchase date and time.
   * @exception IllegalArgumentException if either date and time is null.
   */
  public EconomyTicket(final int the_flight_number, 
                       final Date the_flight_date,
                       final Date the_purchase_date)
    throws IllegalArgumentException
  {
    super(the_flight_number, the_flight_date, the_purchase_date, NORMAL_PRICE, LATE_PRICE);
  }
}
