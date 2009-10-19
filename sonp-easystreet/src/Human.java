import java.util.Map;


public class Human extends Vehicle
{
  //Static fields
  private static final int MY_DEATH_TIME = 50;
  
  //Constructor
  public Human(int the_x, int the_y, Direction the_direction, Terrain the_terrain)
  {
    super(the_x, the_y, the_direction, the_terrain, MY_DEATH_TIME);
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
  public Direction chooseDirection(final Map<Direction, Terrain> the_neighbors, final Light the_light)
  {
    Terrain the_current_terrain = the_neighbors.get(Direction.CENTER);
    Direction the_new_direction = Direction.CENTER;
    /*Terrain the_new_terrain = the_neighbors.get(the_new_direction);
    
    switch(the_current_terrain)
    {
      case STREET:
      case LIGHT:
        while (the_new_terrain != Terrain.STREET || the_new_terrain != Terrain.LIGHT)
        {
          the_new_direction = Direction.random();
          the_new_terrain = the_neighbors.get(the_new_direction);
        }
        break;
      default:
        while (the_new_terrain != the_current_terrain)
        {
          the_new_direction = Direction.random();
          the_new_terrain = the_neighbors.get(the_new_direction);
        }
        break;
    }*/
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
  public boolean canPass(Terrain the_terrain, Light the_light)
  {
    return true;
  }
}
