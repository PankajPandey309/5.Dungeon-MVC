package controller.commands;


import model.grid.TraverseGrid;

/**
 * The ShootArrow shoots an arrow
 * by calling the shootArrow() method.
 */
public class ShootArrow implements IDungeonCommands<String> {

  private final int dist;
  private final String direction;

  /**
   * The constructor for the class.
   *
   * @param dist      the distance to which the arrow must be thrown.
   * @param direction the direction it needs to be thrown in.
   */
  public ShootArrow(String dist, String direction) {


    this.dist = Integer.parseInt(dist);

    this.direction = direction;
  }

  @Override
  public String execute(TraverseGrid model) {
    return model.shootArrow(dist, direction);
  }
}
