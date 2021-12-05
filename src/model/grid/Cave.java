package model.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * The Cve class implements the Location interface.
 * It stores information such as the number of exits from he location , the direction
 * and the caves to which it travels.
 * It also stores the treasure that is alloted to the cave.
 */
public class Cave implements Location {
  private final Map<Integer, Directions> exitDirections;
  private List<Treasure> treasures;
  private final List<Integer> locationExits;
  private Monster monster;
  private final int locationNumber;
  private final int column;
  private Map<Weapons, Integer> weaponsInCave;

  /**
   * The constructor initialises all the variable needed in the class.
   *
   * @param locationNumber the number assigned to the location.
   * @param locationExits  the numbers of the caves to which the location is connected.
   * @param column         the columns in the dungeon.
   */
  public Cave(int locationNumber, List<Integer> locationExits, int column) {
    this.locationExits = locationExits;
    this.locationNumber = locationNumber;
    treasures = new ArrayList<>();
    this.column = column;
    this.weaponsInCave = new TreeMap<>();
    weaponsInCave.put(Weapons.ARROWS, 0);
    this.exitDirections = new HashMap<>();
    assignDirection();

  }

  private void assignDirection() {
    for (int i : locationExits) {
      if (i < locationNumber) {
        if ((locationNumber - column) == i) {
          exitDirections.put(i, Directions.NORTH);
        } else if (locationNumber % column == i % column) {
          if (column == 2) {
            exitDirections.put(i, Directions.NORTH);
          } else {
            exitDirections.put(i, Directions.SOUTH);
          }
        } else if ((locationNumber - i) == 1) {
          exitDirections.put(i, Directions.WEST);
        } else {
          exitDirections.put(i, Directions.EAST);
        }
      } else {
        if ((i - locationNumber) == 1) {
          if (column == 1) {
            exitDirections.put(i, Directions.SOUTH);
          } else {
            exitDirections.put(i, Directions.EAST);
          }
        } else if ((locationNumber + column) == i) {
          exitDirections.put(i, Directions.SOUTH);
        } else if (i % column == locationNumber % column) {
          exitDirections.put(i, Directions.NORTH);
        } else if (i - locationNumber == column - 1) {
          exitDirections.put(i, Directions.WEST);
        }
      }

    }
  }

  @Override
  public Map<Integer, Directions> getExitAndDirections() {
    return new HashMap<>(exitDirections);
  }

  @Override
  public boolean isTunnel() {
    return (this.locationExits.size() == 2);
  }

  @Override
  public boolean hasTreasure() {
    if (this.treasures.isEmpty()) {
      return (this.isTunnel());
    } else {
      return true;
    }
  }

  @Override
  public void getTreasure(List<Treasure> treasures) {
    this.treasures = treasures;

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cave cave = (Cave) o;
    return locationNumber == cave.locationNumber
            && Objects.equals(exitDirections, cave.exitDirections)
            && Objects.equals(treasures, cave.treasures)
            && Objects.equals(locationExits, cave.locationExits);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exitDirections, treasures, locationExits, locationNumber);
  }

  @Override
  public String toString() {
    if (this.isTunnel()) {
      return "tunnel";
    } else {
      return "cave";
    }
  }

  @Override
  public Map<String, Integer> returnLocationInfo() {
    Map<String, Integer> info = new TreeMap<>();
    for (Treasure t : treasures) {
      info.put(t.toString(), 1);
    }
    for (Weapons w : weaponsInCave.keySet()) {
      info.put(w.toString(), weaponsInCave.get(w));
    }
    return info;
  }

  @Override
  public void removePickedTreasure() {
    this.treasures.clear();
  }


  @Override
  public void assignMonster() {
    monster = new Otyugh(2);
  }

  @Override
  public boolean isMonsterPresent() {
    return (monster != null);
  }

  @Override
  public void addWeapons(Map<Weapons, Integer> weaponsInCave) {
    this.weaponsInCave = weaponsInCave;
  }

  @Override
  public Map<Weapons, Integer> getWeapons() {
    return new TreeMap<>(this.weaponsInCave);
  }

  @Override
  public void removeWeapons() {
    this.weaponsInCave.clear();
  }

  @Override
  public List<Treasure> returnTreasure() {
    return new ArrayList<>(treasures);
  }

  @Override
  public Monster getMonster() {
    return monster;
  }

  @Override
  public void removeMonster() {
    monster = null;
  }
}
