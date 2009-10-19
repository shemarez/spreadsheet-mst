import java.util.Map;

/*
 * TCSS 305 - Fall 09
 * Homework 2: Easy Street
 * Author: Son Pham
 */

/**
 * An abstract class for all 4 kinds of vehicle (bicycle, car, truck and human)
 * 
 * @author Son Pham
 * @version 1.0
 */
public abstract class Vehicle implements Movable
{
  private int my_x;
  private int my_y;
  private Direction my_direction;
  private Terrain my_terrain;
  private int my_original_x;
  private int my_original_y;
  private Direction my_original_direction;
  private Terrain my_original_terrain;
  private int my_death_time;
  private boolean my_isAlive = true;
  private int my_waiting_time = 0;
  
  public Vehicle(final int the_x, final int the_y, final Direction the_direction, 
                 final Terrain the_terrain, final int the_death_time)
  {
    my_original_x = the_x;
    my_original_y = the_y;
    my_original_direction = the_direction;
    my_original_terrain = the_terrain;
    my_x = my_original_x;
    my_y = my_original_y;
    my_direction = my_original_direction;
    my_terrain = my_original_terrain;
    my_death_time = the_death_time;
  }
  
  public abstract boolean canPass(Terrain the_terrain, Light the_light);
  
  public abstract Direction chooseDirection(Map<Direction, Terrain> the_neighbors,
                                            Light the_light);
  
  public int getDeathTime()
  {
    return my_death_time;
  }
  
  public void collide(final Movable the_other)
  {
    if (isAlive())
    {
      if (the_other.isAlive())
      {
        if (my_death_time <= the_other.getDeathTime())
        {
          my_isAlive = true;
        }
        else
        {
          my_isAlive = false;
          my_waiting_time = my_death_time;
        }
      }
      else
      {
        my_isAlive = true;
      }
    }
  }
  
  public String getImageFileName()
  {
    final String the_class_name = getClass().getName().toLowerCase();
    if (my_isAlive)
    {
      return the_class_name + ".gif";
    }
    else
    {
      return the_class_name + "_dead.gif";
    }
  }
  
  public Direction getDirection()
  {
    return my_direction;
  }
  
  public int x()
  {
    return my_x;
  }
  
  public int y()
  {
    return my_y;
  }
  
  public boolean isAlive()
  {
    return my_isAlive;
  }
  
  public void poke()
  {
    my_waiting_time = my_waiting_time - 1;
    if (my_waiting_time == 0)
    {
      my_isAlive = true;
      setDirection(Direction.random()); //?
    }
  }
  
  public void reset()
  {
    setX(my_original_x);
    setY(my_original_y);
    setDirection(my_original_direction);
    my_terrain = my_original_terrain;
    my_isAlive = true;
    my_waiting_time = 0;
  }
  
  public void setDirection(final Direction the_direction)
  {
    my_direction = the_direction;
  }

  public void setX(final int the_x)
  {
    my_x = the_x;
  }
  
  public void setY(final int the_y)
  {
    my_y = the_y;
  }
}
