package model.grid;

import java.util.List;
import java.util.Map;



/**
 * The Player interface defines all the operations
 * that are performed by a player in the dungeon.
 * A player moves across the dungeon collecting the
 * treasure present in the cave it enters.
 */
interface Player {
  /**
   * The method collects all the treasure that is present in the cave
   * * where the player is currently at.
   *
   * @param treasure list of treasure present in the cave.
   */
  void collectTreasure(List<Treasure> treasure);

  /**
   * The method returns all the treasure that the player has collected
   * at the moment the method is called.
   *
   * @return list of treasure collected
   */
  Map<String, Integer> getDescription();


  /**
   * return the name of the player.
   *
   * @return name of player.
   */
  String getName();

  /**
   * The player equips any weapons it finds in the dungeon.
   * @param weapons the list of weapons picked up by the player.
   */
  void collectWeapons(Map<Weapons,Integer>  weapons);

  /**
   * Update the arrows after the player fires them.
   */
  void updateArrows();

  /**
   * Get the number of arrows.
   * @return number of arrows.
   */
  int getArrows();


}
