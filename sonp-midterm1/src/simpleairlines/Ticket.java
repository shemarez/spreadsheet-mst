package simpleairlines;

import java.util.Date;

/**
 * A Ticket class for Simple Airlines.
 * @author Son
 * @version Fall 09
 *
 */
public abstract class Ticket 
{
  // Static fields
  /**
   * The tolerance for floating point errors to determine price equivalence,
   * in ulps.
   * 
   * @see Math.ulp(double)
   */
  private static final int TOLERANCE = 5;

  /**
   * The advance purchase required for a ticket to have the normal price.
   * Because dates are stored in milliseconds, this is the number of
   * milliseconds in 14 days (14 days * 24 hours * 60 minutes * 60 seconds *
   * 1000 milliseconds).
   */
  private static final long LATE_THRESHOLD = 14 * 24 * 60 * 60 * 1000;

  // Instance field
  
  /**
   * The flight number for which this ticket was purchased.
   */
  private final int my_flight_number;
  
  /**
   * The flight date and time for which this ticket was purchased.
   */
  private final Date my_flight_date;
  
  /**
   * The date and time at which the ticket was purchased.
   */
  private final Date my_purchase_date;
  
  /**
   * The price of the ticket.
   */
  private double my_price;
  
  // Constructor
  /**
   * Constructs a ticket on Simple Airlines for the specified flight at the
   * specified date and time, purchased at the specified date and time, with a
   * specified price for both normal and late. If there is no late price,
   * input it as 0.
   * 
   * @param the_flight_number
   *            The flight number.
   * @param the_flight_date
   *            The flight date and time.
   * @param the_purchase_date
   *            The purchase date and time.
   * @param the_normal_price
   *            The normal price of this ticket.
   * @param the_late_price
   *            The late price of this ticket.
   * @exception IllegalArgumentException
   *                if either date and time is null.
   */
  public Ticket(final int the_flight_number, final Date the_flight_date,
                final Date the_purchase_date, final double the_normal_price,
                final double the_late_price) 
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
  
    my_price = the_normal_price;
  
    if (the_late_price != 0 
    		&& my_flight_date.getTime() - my_purchase_date.getTime() < LATE_THRESHOLD) 
    {
      my_price = the_late_price;
    }
  }
  
  // Instance methods
  
  /**
   * @return What is your flight number?
   */
  public int flightNumber() 
  {
    return my_flight_number;
  }
  
  /**
   * @return What is your flight date?
   */
  public Date flightDate() 
  {
    return (Date) my_flight_date.clone();
  }
  
  /**
   * @return What is your purchase date?
   */
  public Date purchaseDate() 
  {
    return (Date) my_purchase_date.clone();
  }
  
  /**
   * @return What is your price?
   */
  public double price() 
  {
    return my_price;
  }
  
  // Methods overridden from java.lang.Object
  
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
  
  /**
   * Compares this ticket with the specified object for equality.
   * 
   * @param the_other
   *            The other object.
   * @return true if the two tickets are equal within the margin of error.
   *         False otherwise.
   */
  public boolean equals(final Object the_other) 
  {
    if (the_other != null && the_other.getClass() == getClass()) 
    {
      final Ticket other_ticket = (Ticket) the_other;
      boolean result = flightNumber() == other_ticket.flightNumber();
      result = result && (flightDate() == other_ticket.flightDate());
      result = result && (purchaseDate() == other_ticket.purchaseDate());
      result = result
        && Math.abs(price() - other_ticket.price()) <= TOLERANCE * Math.ulp(price());
      return result;
    } 
    else 
    {
      return false;
    }
  }
  
  /**
   * @return a hash code for this ticket
   */
  public int hashCode() 
  {
    return toString().hashCode();
  }

}
