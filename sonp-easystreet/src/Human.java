/*
 * TCSS 305 - Fall 09
 * Homework 2: Easy Street
 * Author: Son Pham
 */

import java.util.Map;

/**
 * A Human class describes properties of a human and its movements.
 * 
 * @author Son
 * @version Fall 09
 */
public class Human extends Vehicle
{
  //Static fields
  
  /**
   * Number of steps this object must skip if it's dead. 
   */
  private static final int MY_DEATH_TIME = 50;
  
  //Instance fields
  
  /**
   * The original terrain of this human.
   */
  private final Terrain my_original_terrain;
  
  //Constructor
  
  /**
   * Constructs a human object with specified x_coordinate, y_coordinate, direction
   * and terrain.
   * 
   * @param the_x the x_coordinate
   * @param the_y the y_coordinate
   * @param the_direction the direction
   * @param the_terrain the terrain
   */
  public Human(final int the_x, final int the_y, final Direction the_direction, 
               final Terrain the_terrain)
  {
    super(the_x, the_y, the_direction, the_terrain, MY_DEATH_TIME);
    my_original_terrain = the_terrain;
  }
  
  //Instance methods
  
  /**
   * Returns the direction the human would like to move, based on the
   * given map of the terrain of its neighbors and the given status of the
   * street lights.
   * 
   * @param the_neighbors The map of neighboring terrain.
   * @param the_light The light status.
   * @return the direction this human would like to move.
   */
  public Direction chooseDirection(final Map<Direction, Terrain> the_neighbors, 
                                   final Light the_light)
  {
    //final Terrain the_current_terrain = the_neighbors.get(Direction.CENTER);
    Direction the_new_direction = Direction.random();
    Terrain the_new_terrain = the_neighbors.get(the_new_direction);
    
    switch(my_original_terrain)
    {
      case STREET:
      case LIGHT:
        while (the_new_terrain != Terrain.STREET && the_new_terrain != Terrain.LIGHT)
        {
          the_new_direction = Direction.random();
          the_new_terrain = the_neighbors.get(the_new_direction);
        }
        break;
      case WALL:
      case GRASS:
      case TRAIL:
        while (!(the_new_terrain.equals(my_original_terrain)))
        {
          the_new_direction = Direction.random();
          the_new_terrain = the_neighbors.get(the_new_direction);
        }
        break;
      default: 
        break;
    }
    return the_new_direction;
  }
  
  /**
   * Returns whether or not the human may move onto the given type of
   * terrain, when the street lights are the given color.
   * 
   * @param the_terrain The terrain.
   * @param the_light The light color.
   * @return whether or not this human may move onto the given type of terrain
   * when the street lights are the given color. 
   */
  public boolean canPass(final Terrain the_terrain, final Light the_light)
  {
    return true;
  }
}
