import java.util.Map;


public class Car extends Vehicle
{
  //Static fields
  private static final int MY_DEATH_TIME = 10;
  
  //Constructor
  public Car(final int the_x, final int the_y, final Direction the_direction, final Terrain the_terrain)
  {
    super(the_x, the_y, the_direction, the_terrain, MY_DEATH_TIME);
  }
  
  //Instance methods
  
  /**
   * Returns the direction the car would like to move, based on the
   * given map of the terrain of its neighbors and the given status of the
   * street lights.
   * 
   * @param the_neighbors The map of neighboring terrain.
   * @param the_light The light status.
   * @return the direction this car would like to move.
   */
  public Direction chooseDirection(final Map<Direction, Terrain> the_neighbors, 
                                   final Light the_light)
  {
    Direction the_direction = getDirection();
    
    //Set a new random direction if the current direction is CENTER
    if (the_direction == Direction.CENTER)
    {
      the_direction = Direction.random();
    }
    
    Direction the_new_direction = the_direction;
    
    final Terrain the_terrain_ahead = the_neighbors.get(the_direction);
    final Terrain the_terrain_left = the_neighbors.get(the_direction.left());
    final Terrain the_terrain_right = the_neighbors.get(the_direction.right());
    
    switch(the_terrain_ahead)
    {
      case WALL:
      case GRASS:
      case TRAIL:
        if (the_terrain_left == Terrain.STREET || the_terrain_left == Terrain.LIGHT)
        {
          the_new_direction = the_direction.left();
          break;
        }
        else
        {
          if (the_terrain_right == Terrain.STREET || the_terrain_left == Terrain.LIGHT)
          {
            the_new_direction = the_direction.right();
            break;
          }
          else
          {
            the_new_direction = the_direction.reverse();
            break;
          }
        }
      default:
        break;
    }
    return the_new_direction;
  }
  
  /**
   * Returns whether or not the car may move onto the given type of
   * terrain, when the street lights are the given color.
   * 
   * @param the_terrain The terrain.
   * @param the_light The light color.
   * @return whether or not this car may move onto the given type of terrain
   * when the street lights are the given color. 
   */
  public boolean canPass(final Terrain the_terrain, final Light the_light)
  {
    boolean result = true;
    
    switch(the_terrain)
    {
      case WALL:
      case GRASS:
      case TRAIL:
        result = false;
        break;
      case LIGHT:
        if (the_light == Light.RED)
        {
          result = false;
          break;
        }
      default:
        break;
    }
    return result;
  }
}
