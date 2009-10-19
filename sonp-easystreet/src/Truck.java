/*
 * TCSS 305 - Fall 09
 * Homework 2: Easy Street
 * Author: Son Pham
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A Truck class describes properties of a truck and its movements.
 * 
 * @author Son
 * @version Fall 09
 */
public class Truck extends Vehicle
{
  //Static fields
  
  /**
   * Number of steps this object must skip if it's dead. 
   */
  private static final int MY_DEATH_TIME = 0;
  
  //Constructor
  
  /**
   * Constructs a truck object with specified x_coordinate, y_coordinate, direction
   * and terrain.
   * 
   * @param the_x the x_coordinate
   * @param the_y the y_coordinate
   * @param the_direction the direction
   * @param the_terrain the terrain
   */
  public Truck(final int the_x, final int the_y, final Direction the_direction, 
               final Terrain the_terrain)
  {
    super(the_x, the_y, the_direction, the_terrain, MY_DEATH_TIME);
  }
  
  //Instance methods
  
  /**
   * Returns the direction the truck would like to move, based on the
   * given map of the terrain of its neighbors and the given status of the
   * street lights.
   * 
   * @param the_neighbors The map of neighboring terrain.
   * @param the_light The light status.
   * @return the direction this truck would like to move.
   */
  public Direction chooseDirection(final Map<Direction, Terrain> the_neighbors, 
                                   final Light the_light)
  {
    final Random rand = new Random();
    Direction the_direction = getDirection();
    
    //Set a new random direction if the current direction is CENTER
    if (the_direction == Direction.CENTER)
    {
      the_direction = Direction.random();
    }
    
    //Direction the_new_direction = the_direction;
    
    final Direction[] the_direction_array = {the_direction, the_direction.left(), 
                                            the_direction.right()};
    final List<Direction> the_possible_direction_array =  new ArrayList<Direction>();
    
    //Create the possible direction array for the truck
    for (int i = 0; i < the_direction_array.length; i++)
    {
      final Terrain the_new_terrain = the_neighbors.get(the_direction_array[i]);
      if (the_new_terrain == Terrain.STREET || the_new_terrain == Terrain.LIGHT)
      {
        the_possible_direction_array.add(the_direction_array[i]);
      }
    }
    
    if (the_possible_direction_array.isEmpty())
    {
      return the_direction.reverse();
    }
    else
    {
      final int the_random_index = rand.nextInt(the_possible_direction_array.size());
      return the_possible_direction_array.get(the_random_index);
    }
    //return the_new_direction;
  }
  
  /**
   * Returns whether or not the truck may move onto the given type of
   * terrain, when the street lights are the given color.
   * 
   * @param the_terrain The terrain.
   * @param the_light The light color.
   * @return whether or not this truck may move onto the given type of terrain
   * when the street lights are the given color. 
   */
  public boolean canPass(final Terrain the_terrain, final Light the_light)
  {
    return the_terrain == Terrain.STREET || the_terrain == Terrain.LIGHT;
  }
}
