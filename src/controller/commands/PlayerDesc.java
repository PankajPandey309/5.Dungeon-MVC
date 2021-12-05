package controller.commands;

import model.grid.TraverseGrid;

import java.util.Map;



/**
 * The PlayerDesc returns the player description
 * by calling the getPlayerDescription() method.
 */
public class PlayerDesc implements IDungeonCommands<Map<String, Map<String, Integer>>> {

  @Override
  public Map<String, Map<String, Integer>> execute(TraverseGrid model) {
    return model.getPlayerDescription();
  }
}
