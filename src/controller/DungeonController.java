package controller;

import model.grid.TraverseGrid;

/**
 * Represents a Controller for text based dungeon Adventure game:
 * handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface DungeonController {

  /**
   * Execute a single game of text based dungeon Adventure game given a dungeon
   * Model. When the player reaches the end or is eaten by a monster, the game
   * is over,
   * the playGame method ends.
   *
   * @param model a non-null tic tac toe Model
   */
  void playGame(TraverseGrid model);
}
