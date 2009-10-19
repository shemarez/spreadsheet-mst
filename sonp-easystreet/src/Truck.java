import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Truck extends Vehicle
{
  //Static fields
  private static final int MY_DEATH_TIME = 0;
  
  //Constructor
  public Truck(int the_x, int the_y, Direction the_direction, Terrain the_terrain)
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
  public Direction chooseDirection(final Map<Direction, Terrain> the_neighbors, final Light the_light)
  {
    Random rand = new Random();
    Direction the_direction = getDirection();
    
    //Set a new random direction if the current direction is CENTER
    if (the_direction == Direction.CENTER)
    {
      the_direction = Direction.random();
    }
    
    //Direction the_new_direction = the_direction;
    
    Direction[] the_direction_array = {the_direction, the_direction.left(), the_direction.right()};
    List<Direction> the_possible_direction_array =  new ArrayList<Direction>();
    
    for (int i = 0; i < the_direction_array.length; i++)
    {
      Terrain the_new_terrain = the_neighbors.get(the_direction_array[i]);
      if (the_new_terrain == Terrain.STREET || the_new_terrain == Terrain.LIGHT)
      {
        the_possible_direction_array.add(the_direction_array[i]);
      }
    }
    
    if (the_possible_direction_array.size() > 0)
    {
      int the_random_index = rand.nextInt(the_possible_direction_array.size());
      return the_possible_direction_array.get(the_random_index);
    }
    else
    {
      return the_direction.reverse();
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
  public boolean canPass(Terrain the_terrain, Light the_light)
  {
    if (the_terrain != Terrain.STREET && the_terrain != Terrain.LIGHT)
    {      
      return false;
    }
    else
    {
      return true;
    }
  }
}
