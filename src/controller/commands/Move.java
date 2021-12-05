package controller.commands;


import model.grid.TraverseGrid;

/**
 * The move command moves a player in the specified direction
 * by call the move() method.
 */
public class Move implements IDungeonCommands<String> {

  private final String directions;

  /**
   * The constructor for the class.
   *
   * @param directions the direction to move in.
   */
  public Move(String directions) {
    this.directions = directions;
  }

  @Override
  public String execute(TraverseGrid model) {
    int x = model.move(directions);
    if (x == 1) {
      if (model.isDestinationReached() && model.isPlayerAlive() == 1) {
        return "Congratulations you have reached the end cave!!!";
      }
    } else if (x == -1) {
      return "You can't move in that direction ";
    }

    return "";
  }
}
