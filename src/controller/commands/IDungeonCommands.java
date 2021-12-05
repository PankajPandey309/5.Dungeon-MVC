package controller.commands;

import model.grid.TraverseGrid;

/**
 * The interface defines the functionalities offered by each command.
 * In this case eah command takes in the model and executes a
 * particular method.
 * @param <T> generic return type of method.
 */
public interface IDungeonCommands<T> {

  /**
   * The method executes the appropriate method from the model.
   * @param model the model object instance.
   * @return the data returned by the model.
   */
  T execute(TraverseGrid model);
}
