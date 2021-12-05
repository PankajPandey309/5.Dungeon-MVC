package model.grid;

import java.util.List;
import java.util.Map;

public interface ReadOnlyDungeonModel extends Grid {

  /**
   * Get the possible directions in which the player can move.
   *
   * @return the  details related to that location.
   */
  Map<String, Map<List<Directions>, Map<String, Integer>>> getLocationDetails();


  /**
   * description of the player that, at a minimum,
   * includes a description of what treasure the player has collected.
   */
  Map<String, Map<String, Integer>> getPlayerDescription();

  /**
   * returns the Cave which is the  start location.
   *
   * @return the string representation of start location.
   * @throws IllegalArgumentException if source is not assigned.
   */
  String getSource() throws IllegalArgumentException;

  /**
   * returns the Cave which is the  destination location.
   *
   * @return the string representation of destination location.
   * @throws IllegalArgumentException if source is not assigned.
   */
  String getDestination() throws IllegalArgumentException;

  /**
   * return whether the player has reached the destination or not.
   *
   * @return boolean whether the player is at destination or not.
   */
  boolean isDestinationReached();

  /**
   * The method returns a message formulated
   * on the basis of a monsters proximity to the
   * current location of the player.
   *
   * @return a string message.
   */
  String getSmell();

  /**
   * Returns whether the player is alive or not.
   *
   * @return whether player is alive or not.
   */
  int isPlayerAlive();
}
