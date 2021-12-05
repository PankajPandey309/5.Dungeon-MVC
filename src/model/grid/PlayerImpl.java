package model.grid;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The PlayerImpl class represents a single player playing in the dungeon.
 * A player enters the dungeon at a cave marked as 'start' and must traverse the
 * dungeon until it reaches the cave marked as 'end' .
 */
class PlayerImpl implements Player {

  private final Map<Treasure, Integer> treasureCollectByPlayer;
  private final String name;
  private final Map<Weapons, Integer> weaponsEquipped;

  /**
   * The constructor initialises the name of the player.
   *
   * @param name player name.
   */
  public PlayerImpl(String name) {
    this.name = name;
    treasureCollectByPlayer = new TreeMap<>();
    weaponsEquipped = new TreeMap<>();
    weaponsEquipped.put(Weapons.ARROWS, 3);
    treasureCollectByPlayer.put(Treasure.DIAMONDS, 0);
    treasureCollectByPlayer.put(Treasure.SAPPHIRES, 0);
    treasureCollectByPlayer.put(Treasure.RUBIES, 0);
  }

  @Override
  public void collectTreasure(List<Treasure> treasure) {
    for (Treasure t : treasure) {
      treasureCollectByPlayer.merge(t, 1, Integer::sum);
    }
  }

  @Override
  public Map<String, Integer> getDescription() {
    Map<String, Integer> desc = new TreeMap<>();
    for (Treasure t : treasureCollectByPlayer.keySet()) {
      desc.put(t.toString(), treasureCollectByPlayer.get(t));
    }
    for (Weapons w : weaponsEquipped.keySet()) {
      desc.put(w.toString(), weaponsEquipped.get(w));
    }
    return desc;
  }


  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void collectWeapons(Map<Weapons, Integer> weapons) {
    for (Weapons w : weapons.keySet()) {
      if (weaponsEquipped.get(w) == null) {
        weaponsEquipped.put(w, 1);
      } else {
        weaponsEquipped.put(w, (weaponsEquipped.get(w) + weapons.get(w)));
      }
    }
  }

  @Override
  public void updateArrows() {
    weaponsEquipped.put(Weapons.ARROWS, weaponsEquipped.get(Weapons.ARROWS) - 1);
  }

  @Override
  public int getArrows() {
    return weaponsEquipped.get(Weapons.ARROWS);
  }
}
