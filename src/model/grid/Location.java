package model.grid;


import java.util.List;
import java.util.Map;


/**
 * Location interface defines the functionalities
 * of individual cells in the grid.
 * A location can be classified as either a tunnel or a cave
 * depending on the number of paths origination from the
 * location.
 * Each location which is classified as a cave can hold some
 * treasure whereas tunnels cannot do so.
 */
public interface Location {
  /**
   * The method returns the caves to which a player
   * can travel to from the current cave and the direction they must take.
   *
   * @return the cave exits and the directions.
   */
  Map<Integer, Directions> getExitAndDirections();

  /**
   * The method returns true if the location is a tunnel or not.
   * It checks if the location has only two exits, if so it returns true
   * otherwise it returns false;
   *
   * @return boolean if a location is true or not.
   */
  boolean isTunnel();

  /**
   * the function returns ture if the location has treasure,
   * false if it does not.
   *
   * @return whether there is treasure in the location or not.
   */
  boolean hasTreasure();

  /**
   * The location is assigned a random number of treasure.
   */
  void getTreasure(List<Treasure> treasures);

  /**
   * return the list of treasure present in the cave.
   *
   * @return list of treasure.
   */
  Map<String, Integer> returnLocationInfo();

  /**
   * Once The player has picked up the treasure, the treasure list must be
   * cleared.
   */
  void removePickedTreasure();

  /**
   * Assign a monster to the cave.
   */
  void assignMonster();

  /**
   * returns true if monster is present, false
   * otherwise.
   *
   * @return whether monster is present or not.
   */
  boolean isMonsterPresent();

  /**
   * The method adds the weapons assigned to cave.
   *
   * @param weaponsInCave map of weapons and their quantitites.
   */
  void addWeapons(Map<Weapons, Integer> weaponsInCave);

  /**
   * Return the various maps present in the cave.
   *
   * @return map of weapons present.
   */
  Map<Weapons, Integer> getWeapons();

  /**
   * remove the weapons from the cave once they are picked up by the player.
   */
  void removeWeapons();

  /**
   * return the treasure present in the cave.
   * @return list of treasure in cave.
   */
  List<Treasure> returnTreasure();

  /**
   * return the object of the monster stored in the cave.
   * @return monster object.
   */
  Monster getMonster();

  /**
   * remove the monster once it is killed.
   */
  void removeMonster();

}
