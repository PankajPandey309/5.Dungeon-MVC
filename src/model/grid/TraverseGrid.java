package model.grid;


import java.util.List;
import java.util.Map;


/**
 * The interface defines the functionalities of a player moving through the dungeon.
 */
public interface TraverseGrid extends ReadOnlyDungeonModel {

  /**
   * Move the player from current cave to the
   * cave in the specified position.
   *
   * @param string the direction in which the player must move.
   * @return an integer depicting the state after the move.
   */
  int move(String string);

  /**
   * Get the possible directions in which the player can move.
   *
   * @return the  details related to that location.
   */
  Map<String, Map<List<Directions>, Map<String, Integer>>> getLocationDetails();

  /**
   * The method assigns the treasure present in the cave to the Player.
   *
   * @return whether the treasure are picked up or not.
   */
  boolean pickUpTreasure();

  /**
   * description of the player that, at a minimum,
   * includes a description of what treasure the player has collected.
   */
  Map<String, Map<String, Integer>> getPlayerDescription();

  /**
   * The method returns the path that a player has travelled so far.
   *
   * @return the list of caves visited.
   */
  List<Location> getPathTravelled();

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
   * The method assigns the weapons present in the cave to the Player.
   *
   * @return whether the weapons are picked up or not.
   */
  boolean pickUpWeapons();

  /**
   * The method returns a message formulated
   * on the basis of a monsters proximity to the
   * current location of the player.
   *
   * @return a string message.
   */
  String getSmell();

  /**
   * shhot an arrow at a distance of a specified number of caves and retrun if
   * the monster is hit or not.
   *
   * @param dist       the number of caves it must travel.
   * @param directions the direction in which the arrow is shot.
   * @return whether the hit was successful or not.
   */
  String shootArrow(int dist, String directions);

  /**
   * Returns whether the player is alive or not.
   *
   * @return whether player is alive or not.
   */
  int isPlayerAlive();
}
