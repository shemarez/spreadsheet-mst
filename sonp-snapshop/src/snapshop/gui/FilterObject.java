package snapshop.gui;
/*
 * Filter Object Class
 * Autumn 2009 TCSS 305
 */

import snapshop.filters.Filter;
import snapshop.image.PixelImage;

/**
 * A class that stores a name, and prints that name and applies
 * a filter onto the image when the doFilter method is called
 * 
 * @author Son Pham
 * @version 1.0
 */
public class FilterObject
{
  private String my_name;
  private Filter my_filter;
  
  public FilterObject(String the_name, Filter the_filter)
  {
    my_name = the_name;
    my_filter = the_filter;
  }
  
  public String toString()
  {
    return my_name;
  }
  
  public void doFilter(PixelImage the_image)
  {
    my_filter.filter(the_image);
  }
}
