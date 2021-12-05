package controller.commands;

import model.grid.TraverseGrid;

/**
 * The PickTreasure picks the treasure present in the class
 * by calling the pickUpTreasure() method.
 */
public class PickTreasure implements IDungeonCommands<String> {
  @Override
  public String execute(TraverseGrid model) {
    if (model.pickUpTreasure()) {
      return "You have successfully picked up the treasure.";
    } else {
      return "There is no treasure for you to pick up.";
    }
  }
}
