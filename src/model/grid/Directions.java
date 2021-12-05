package model.grid;

/**
 * The enum represents the various directions
 * in which a player can move.
 */
public enum Directions {
  NORTH, SOUTH, EAST, WEST;

  private Directions opposite;

  //https://stackoverflow.com/questions/18883646/java-enum-methods-return-opposite-direction-enum/18883717
  static {
    NORTH.opposite = SOUTH;
    SOUTH.opposite = NORTH;
    EAST.opposite = WEST;
    WEST.opposite = EAST;
  }

  /**
   * returns the opposite direction of the current direction.
   *
   * @return opposite direction.
   */
  public Directions getOpposite() {
    return opposite;
  }

}
