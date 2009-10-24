/*
 * Autumn 2009 TCSS 305 Midterm Exam
 * Question 3 - Ticket Class Refactoring
 */

package simpleairlinesprovided;

import java.util.Date;

/**
 * A first class ticket on Simple Airlines.
 * 
 * @author Daniel M. Zimmerman
 * @author (Your Name Here)
 * @version Autumn 2009
 */
public class FirstClassTicket
{
  // Static Field
  
  /**
   * The price of a first class ticket.
   */
  public static final double TICKET_PRICE = 400.00; 

  // Instance Fields
  
  /**
   * The flight number for which this ticket was purchased.
   */
  private final int my_flight_number;
  
  /**
   * The flight date and time for which this ticket was purchased.
   */
  private final Date my_flight_date;
  
  /**
   * The date and time at which this ticket was purchased.
   */
  private final Date my_purchase_date;
  
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
    if (the_flight_date == null || the_purchase_date == null)
    {
      throw new IllegalArgumentException("null date and time not allowed");
    }
    my_flight_number = the_flight_number;
    
    // Date objects are mutable, so we store clones
    my_flight_date = (Date) the_flight_date.clone();
    my_purchase_date = (Date) the_purchase_date.clone();
  }
  
  // Instance Methods
  
  /**
   * @return What is your flight number?
   */
  public int flightNumber() 
  { 
    return my_flight_number; 
  }
  
  /**
   * @return What is your flight date and time?
   */
  public Date flightDate() 
  { 
    // Date objects are mutable, so we return a clone
    return (Date) my_flight_date.clone(); 
  }
  
  /**
   * @return What is your purchase date and time?
   */
  public Date purchaseDate()
  {
    // Date objects are mutable, so we return a clone
    return (Date) my_purchase_date.clone();
  }
  
  /**
   * @return What is your price?
   */
  public double price() 
  { 
    return TICKET_PRICE; 
  }
  
  /**
   * {@inheritDoc}
   */
  public String toString()
  {
    final StringBuilder sb = new StringBuilder();
    sb.append(getClass().getName());
    sb.append(", flight ").append(flightNumber());
    sb.append(", date/time ").append(flightDate()); 
    sb.append(", price ").append(price());
    return sb.toString();
  }
}


