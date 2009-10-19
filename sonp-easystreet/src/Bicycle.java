/*
 * TCSS 305 - Fall 09
 * Homework 2: Easy Street
 * Author: Son Pham
 */

import java.util.Map;

/**
 * A Bicycle class
 * 
 * @author Son Pham
 * @version 1.0
 *
 */
public class Bicycle extends Vehicle
{
  //Static fields
  
  private static final int MY_DEATH_TIME = 20;
  
  //Constructor
  
  public Bicycle(int the_x, int the_y, Direction the_direction, Terrain the_terrain)
  {
    super(the_x, the_y, the_direction, the_terrain, MY_DEATH_TIME);
  }
  
  //Instance methods
  
  /**
   * Returns the direction the bicycle would like to move, based on the
   * given map of the terrain of its neighbors and the given status of the
   * street lights.
   * 
   * @param the_neighbors The map of neighboring terrain.
   * @param the_light The light status.
   * @return the direction this bicycle would like to move.
   */
  public Direction chooseDirection(final Map<Direction, Terrain> the_neighbors, final Light the_light)
  {    
    Direction the_direction = getDirection();
    
    //Set a new random direction if the current direction is CENTER
    if (the_direction == Direction.CENTER)
    {
      the_direction = Direction.random();
    }
    
    Direction the_new_direction = the_direction;
    Terrain the_terrain_ahead = the_neighbors.get(the_direction);
    Terrain the_terrain_left = the_neighbors.get(the_direction.left());
    Terrain the_terrain_right = the_neighbors.get(the_direction.right());
    Terrain the_terrain_reverse = the_neighbors.get(the_direction.reverse());
    
    switch(the_terrain_ahead)
    {
      /*case LIGHT:
      case TRAIL:
        the_new_direction = the_direction;
        break;*/
      case GRASS:
      case WALL:
        //If it can turn left, turn left
        if (the_terrain_left != Terrain.GRASS && the_terrain_left != Terrain.WALL)
        {
          the_new_direction = the_direction.left();
          break;
        }
        //If not turn right as possible
        else
        {          
          if (the_terrain_right != Terrain.GRASS && the_terrain_right != Terrain.WALL)
          {
            the_new_direction = the_direction.right();
            break;
          }
          //If not turn around as possible
          else
          {            
            if (the_terrain_reverse != Terrain.GRASS && the_terrain_reverse != Terrain.WALL)
            {
              the_new_direction = the_direction.reverse();
              break;
            }
            //If not, it stands still
            /*else
            {
              the_new_direction = the_direction;
              break;
            }*/
          }
        }
      case STREET:
        if (the_terrain_left == Terrain.TRAIL)
        {
          the_new_direction = the_direction.left();
          break;
        }
        else if (the_terrain_right == Terrain.TRAIL)
        {
          the_new_direction = the_direction.right();
          break;
        }
        /*else
        {
          the_new_direction = the_direction;
          break;
        }*/
      default:
        break;
    }
    return the_new_direction;
      
  }
  
  /**
   * Returns whether or not the bicycle may move onto the given type of
   * terrain, when the street lights are the given color.
   * 
   * @param the_terrain The terrain.
   * @param the_light The light color.
   * @return whether or not this bicycle may move onto the given type of terrain
   * when the street lights are the given color. 
   */
  public boolean canPass(final Terrain the_terrain, final Light the_light)
  {
    if (the_light == Light.GREEN)
    {
      if (the_terrain != Terrain.GRASS && the_terrain != Terrain.WALL)
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    else
    {
      if (the_terrain != Terrain.GRASS && the_terrain != Terrain.WALL && the_terrain != Terrain.LIGHT)
      {
        return true;
      }
      else
      {
        return false;
      }
    }
  }
}
