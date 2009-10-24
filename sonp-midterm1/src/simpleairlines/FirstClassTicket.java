/*
 * Autumn 2009 TCSS 305 Midterm Exam
 * Question 3 - Ticket Class Refactoring
 */

package simpleairlines;

import java.util.Date;

/**
 * A first class ticket on Simple Airlines.
 * 
 * @author Daniel M. Zimmerman
 * @author Son Pham
 * @version Autumn 2009
 */
public class FirstClassTicket extends Ticket
{
  // Static Field
  
  /**
   * The price of a first class ticket.
   */
  public static final double TICKET_PRICE = 400.00; 

  // Constructor
  
  /**
   * Constructs a first class ticket on Simple Airlines for the specified
   * flight at the specified date and time, purchased at the specified date and time.
   * 
   * @param the_flight_number The flight number.
   * @param the_flight_date The flight date and time.
   * @param the_purchase_date The purchase date and time.
   * @exception IllegalArgumentException if either date and time is null.
   */
  public FirstClassTicket(final int the_flight_number, 
                          final Date the_flight_date,
                          final Date the_purchase_date)
    throws IllegalArgumentException
  {
    super(the_flight_number, the_flight_date, the_purchase_date, TICKET_PRICE, 0);
  }
}
