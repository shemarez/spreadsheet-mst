/*
 * TCSS 305 - Fall 09
 * Homework 2: Easy Street
 * Author: Son Pham
 */

import java.util.Map;

/**
 * An abstract class for all 4 kinds of vehicle (bicycle, car, truck and human).
 * 
 * @author Son Pham
 * @version Fall 09
 */
public abstract class Vehicle implements Movable
{
  //Instance fields
  
  /**
   * The current x coordinate.
   */
  private int my_x;
  
  /**
   * The current y coordinate.
   */
  private int my_y;
  
  /**
   * The current direction.
   */
  private Direction my_direction;
  
  /**
   * The current terrain.
   */
  private Terrain my_terrain;
  
  /**
   * The original x coordinate.
   */
  private final int my_original_x;
  
  /**
   * The original y coordinate.
   */
  private final int my_original_y;
  
  /**
   * The original direction.
   */
  private final Direction my_original_direction;
  
  /**
   * The original terrain.
   */
  private final Terrain my_original_terrain;
  
  /**
   * The death time of this Vehicle.
   */
  private final int my_death_time;
  
  /**
   * The boolean value of isAlive.
   */
  private boolean my_is_alive = true;
  
  /**
   * The waiting time of this vehicle until it's revived.
   */
  private int my_waiting_time;
  
  //Constructor
  
  /**
   * Constructs a Vehicle object with specified x_coordinate, y_coordinate, direction,
   * terrain and death time.
   * 
   * @param the_x the x_coordinate
   * @param the_y the y_coordinate
   * @param the_direction the direction
   * @param the_terrain the terrain
   * @param the_death_time the death time of the object
   */
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
  
  //Abstract methods
  
  /**
   * Returns whether or not this object may move onto the given type of
   * terrain, when the street lights are the given color.
   * 
   * @param the_terrain The terrain.
   * @param the_light The light color.
   * @return whether or not this object may move onto the given type of terrain
   * when the street lights are the given color. 
   */
  public abstract boolean canPass(Terrain the_terrain, Light the_light);
  
  /**
   * Returns the direction this object would like to move, based on the
   * given map of the terrain of its neighbors and the given status of the
   * street lights.
   * 
   * @param the_neighbors The map of neighboring terrain.
   * @param the_light The light status.
   * @return the direction this object would like to move.
   */
  public abstract Direction chooseDirection(Map<Direction, Terrain> the_neighbors,
                                            Light the_light);
  
  //Instance methods
  
  /**
   * Returns the number of updates between this vehicle's death and when it
   * should be revived.
   * 
   * @return the number of updates.
   */
  public int getDeathTime()
  {
    return my_death_time;
  }
  
  /**
   * Called when this Vehicle object collides with the given other Vehicle
   * object.
   * 
   * @param the_other The other object.
   */
  public void collide(final Movable the_other)
  {
    if (isAlive())
    {
      if (the_other.isAlive())
      {
        if (my_death_time <= the_other.getDeathTime())
        {
          my_is_alive = true;
        }
        else
        {
          my_is_alive = false;
          my_waiting_time = my_death_time;
        }
      }
      else
      {
        my_is_alive = true;
      }
    }
  }
  
  /**
   * Returns the file name of the image for this Vehicle object, such as
   * "car.gif".
   * 
   * @return the file name.
   */
  public String getImageFileName()
  {
    final String the_class_name = getClass().getName().toLowerCase();
    if (my_is_alive)
    {
      return the_class_name + ".gif";
    }
    else
    {
      return the_class_name + "_dead.gif";
    }
  }
  
  /** 
   * Returns this Vehicle object's direction. 
   * 
   * @return the direction.
   */
  public Direction getDirection()
  {
    return my_direction;
  }
  
  /**
   * Returns this Vehicle object's current terrain.
   * 
   * @return the terrain
   */
  public Terrain getTerrain()
  {
    return my_terrain;
  }
  
  /** 
   * Returns this Vehicle object's x-coordinate. 
   * 
   * @return the x-coordinate.
   */
  public int x()
  {
    return my_x;
  }
  
  /** 
   * Returns this Vehicle object's y-coordinate. 
   * 
   * @return the y-coordinate.
   */
  public int y()
  {
    return my_y;
  }
  
  /**
   * Returns whether this Vehicle object is alive and should move on the
   * map.
   * 
   * @return true if the object is alive, false otherwise.
   */
  public boolean isAlive()
  {
    return my_is_alive;
  }
  
  /**
   * Called by the UI to notify a dead vehicle that 1 movement round has
   * passed, so that it will become 1 move closer to revival.
   */
  public void poke()
  {
    my_waiting_time = my_waiting_time - 1;
    if (my_waiting_time == 0)
    {
      my_is_alive = true;
      setDirection(Direction.random()); //?
    }
  }
  
  /** 
   * Moves this vehicle back to its original position. 
   */
  public void reset()
  {
    setX(my_original_x);
    setY(my_original_y);
    setDirection(my_original_direction);
    my_terrain = my_original_terrain;
    my_is_alive = true;
    my_waiting_time = 0;
  }
  
  /** 
   * Sets this object's facing direction to the given value. 
   * 
   * @param the_direction The new direction.
   */
  public void setDirection(final Direction the_direction)
  {
    my_direction = the_direction;
  }

  /** 
   * Sets this object's x-coordinate to the given value. 
   *
   * @param the_x The new x-coordinate.
   */
  public void setX(final int the_x)
  {
    my_x = the_x;
  }
  
  /** 
   * Sets this object's y-coordinate to the given value.
   * 
   * @param the_y The new y-coordinate.
   */
  public void setY(final int the_y)
  {
    my_y = the_y;
  }
}
