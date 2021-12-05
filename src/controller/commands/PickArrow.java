package controller.commands;

import model.grid.TraverseGrid;

/**
 * The PickArrow picks the weapons present in the class
 * by calling the pickWeapons() method.
 */
public class PickArrow implements IDungeonCommands<String> {

  @Override
  public String execute(TraverseGrid model) {
    if (model.pickUpWeapons()) {
      return "You have successfully picked up the arrows.";
    } else {
      return "There are no arrows for you to pick up.";
    }
  }
}
