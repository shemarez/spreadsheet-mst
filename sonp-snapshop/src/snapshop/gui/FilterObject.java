/*
 * Homework 3 - SnapShopGUI
 * Autumn 2009 TCSS 305
 */

package snapshop.gui;

import snapshop.filters.Filter;
import snapshop.image.PixelImage;

/**
 * A class that stores a name, and prints that name and applies
 * a filter onto the image when the doFilter method is called.
 * 
 * @author Son Pham
 * @version 1.0
 */
public class FilterObject
{
  //Instance fields
  
  /**
   * Name of this filter object.
   */
  private final String my_name;
  
  /**
   * Filter type of this filter object.
   */
  private final Filter my_filter;

  //Constructor
  
  /**
   * Construct a FilterObject with specified name and filter type.
   * 
   * @param the_name The name of the object.
   * @param the_filter The filter type of the object.
   */
  public FilterObject(final String the_name, final Filter the_filter)
  {
    my_name = the_name;
    my_filter = the_filter;
  }
  
  /**
   * {@inheritDoc}
   */
  public String toString()
  {
    return my_name;
  }
  
  /**
   * Apply the filter to the specified image.
   * 
   * @param the_image The image what will be applied filter to.
   */
  public void doFilter(final PixelImage the_image)
  {
    my_filter.filter(the_image);
  }
}
