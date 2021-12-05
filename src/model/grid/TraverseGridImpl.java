package model.grid;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class implements the TraverseGrid interface.
 * It implements the method which define and control the
 * players movement through the dungeon such as the path taken
 * and the treasure collected.
 */
public class TraverseGridImpl extends Dungeon implements TraverseGrid {


  private final Player player;
  private final List<Location> pathTravelled;
  private boolean playerAlive;


  /**
   * The constructor passes all values to the dungeon class
   * which initialises all required fields which are to be used in the program.
   *
   * @param rows               the number of rows in the dungeon.
   * @param columns            the number of columns in the dungeon.
   * @param wrapping           boolean which signifies whether the dungeon is wrapping or not.
   * @param interconnectivity  the interconnectivity of the dungeon.
   * @param treasurePercentage the percentage of caves which must have treasure.
   * @param seed               the seed value of the random number generator.
   * @throws IllegalArgumentException if any values is null or invalid.
   */
  public TraverseGridImpl(int rows, int columns, boolean wrapping,
                          int interconnectivity, int treasurePercentage,
                          int monstersInDungeon, String name, int seed)
          throws IllegalArgumentException {
    super(rows, columns, wrapping, interconnectivity, treasurePercentage, monstersInDungeon, seed);
    this.player = new PlayerImpl(name);
    this.pathTravelled = new ArrayList<>();
    this.playerAlive = true;

  }


  @Override
  public int move(String string) {
    Directions direction = getDirection(string);

    if (!currentLocation.getExitAndDirections().containsValue(direction)) {
      return -1;
    }
    if (playerAlive) {
      for (int i : currentLocation.getExitAndDirections().keySet()) {
        if (currentLocation.getExitAndDirections().get(i) == direction) {
          pathTravelled.add(currentLocation);
          currentLocation = locationMap.get(i);
          if (currentLocation.isMonsterPresent()
                  && currentLocation.getMonster().getHealth() == 2) {
            playerAlive = false;
            return 0;
          } else if (currentLocation.isMonsterPresent()
                  && currentLocation.getMonster().getHealth() == 1) {
            int ret = r.getRandomNumber(0, 2);
            playerAlive = ret == 1;
            return ret;
          }
          if (currentLocation.equals(destination)) {
            pathTravelled.add(currentLocation);
          }
        }
      }

    } else {
      throw new IllegalArgumentException("Player is dead");
    }
    return 1;
  }

  @Override
  public Map<String, Map<List<Directions>, Map<String, Integer>>> getLocationDetails() {
    Map<List<Directions>, Map<String, Integer>> directionAndTreasure = new HashMap<>();
    Map<String, Map<List<Directions>, Map<String, Integer>>> locationData = new HashMap<>();
    directionAndTreasure.put(new ArrayList<>(currentLocation.getExitAndDirections().values()),
            currentLocation.returnLocationInfo());
    locationData.put(currentLocation.toString(), directionAndTreasure);
    return locationData;
  }

  @Override
  public boolean pickUpTreasure() {
    if (playerAlive) {
      if (currentLocation.hasTreasure() && !currentLocation.isTunnel()) {
        player.collectTreasure(currentLocation.returnTreasure());
        currentLocation.removePickedTreasure();
        return true;
      }
      return false;
    } else {
      throw new IllegalArgumentException("Player is dead ");
    }
  }

  @Override
  public Map<String, Map<String, Integer>> getPlayerDescription() {

    Map<String, Map<String, Integer>> playerDetails = new HashMap<>();
    playerDetails.put(player.getName(), player.getDescription());
    return playerDetails;
  }

  @Override
  public List<Location> getPathTravelled() {
    return new ArrayList<>(pathTravelled);
  }

  @Override
  public String toString() {
    String gridOutput = "";
    for (int i = 0; i < rows; i++) {
      StringBuilder n = new StringBuilder();
      String e;
      String w;
      StringBuilder s = new StringBuilder();
      String x;
      StringBuilder xx = new StringBuilder();
      for (int j = 0; j < columns; j++) {
        String g;
        if (source != null && destination != null) {
          if (grid[i][j].equals(source)) {
            g = "S";
          } else if (grid[i][j].equals(destination)) {
            g = "D";
          } else if (grid[i][j].isTunnel()) {
            g = "T";
          } else if (grid[i][j].isMonsterPresent()) {
            g = "O";
          } else {
            g = "C";
          }
        } else {
          if (grid[i][j].isTunnel()) {
            g = "T";
          } else {
            g = "C";
          }
        }
        List<Directions> directions = new ArrayList<>(grid[i][j].getExitAndDirections().values());
        if (directions.contains(Directions.NORTH)) {
          n.append("  |  ");
        } else {
          n.append("     ");
        }
        if (directions.contains(Directions.EAST)) {
          e = "--";
        } else {
          e = "  ";
        }
        if (directions.contains(Directions.SOUTH)) {
          s.append("  |  ");
        } else {
          s.append("     ");
        }
        if (directions.contains(Directions.WEST)) {
          w = "--";
        } else {
          w = "  ";
        }
        xx.append(w).append(g).append(e);
      }

      x = String.format("%s\n%s\n%s", n, xx, s);
      gridOutput = String.format("%s\n%s", gridOutput, x);
    }

    return gridOutput;
  }

  @Override
  public String getSource() throws IllegalArgumentException {
    if (source == null) {
      throw new IllegalStateException("Source has not been assigned");
    }
    return source.toString();
  }

  @Override
  public String getDestination() throws IllegalArgumentException {
    if (destination == null) {
      throw new IllegalStateException("destination has not been assigned");
    }
    return destination.toString();
  }

  @Override
  public boolean isDestinationReached() {
    return currentLocation.equals(destination) && destination.getMonster()==null;
  }

  @Override
  public boolean pickUpWeapons() {
    if (playerAlive) {
      if (currentLocation.getWeapons().get(Weapons.ARROWS) != null
              && currentLocation.getWeapons().get(Weapons.ARROWS) > 0) {
        player.collectWeapons(currentLocation.getWeapons());
        currentLocation.removeWeapons();
      } else {
        return false;
      }
      return true;
    } else {
      throw new IllegalArgumentException("Player is dead ");
    }
  }

  @Override
  public String getSmell() {
    Map<Location, Integer> distanceTracker = checkMinDistance(currentLocation);
    Map<Integer, Integer> monsterMap = new HashMap<>();
    for (Location l : distanceTracker.keySet()) {
      if (distanceTracker.get(l) == 1 && l.isMonsterPresent()) {
        monsterMap.merge(1, 1, (a, b) -> monsterMap.get(b) + b);
      } else if (distanceTracker.get(l) == 2 && l.isMonsterPresent()) {
        monsterMap.merge(2, 1, Integer::sum);
      }
    }
    if (monsterMap.get(1) != null && monsterMap.get(1) >= 1
            || monsterMap.get(2) != null && monsterMap.get(2) >= 2) {
      return "You smell something terrible nearby";
    } else if (monsterMap.get(1) == null && monsterMap.get(2) != null && monsterMap.get(2) == 1) {
      return "You smell a faint putrid odour";
    } else {
      return "You smell nothing but the faint musty smell of the dungeon";
    }
  }

  @Override
  public String shootArrow(int dist, String dir) {
    Directions direction = getDirection(dir);
    if (dist < 1) {
      throw new IllegalArgumentException("Invalid distance");
    }

    if (player.getArrows() > 0) {
      player.updateArrows();
      if (currentLocation.isTunnel()) {
        for (int i : currentLocation.getExitAndDirections().keySet()) {
          if (currentLocation.getExitAndDirections().get(i) == direction) {
            return validateArrow(dist - 1, locationMap.get(i), direction);
          }
        }
      }
      return validateArrow(dist, currentLocation, direction);
    } else {
      return "Your quiver is empty!!!";
    }

  }

  private String validateArrow(int dist, Location node, Directions direction) {
    if (dist == 0 && !node.isTunnel()) {
      if (node.isMonsterPresent()) {
        node.getMonster().getsHit(Weapons.ARROWS.getDamage());
        if (node.getMonster().getHealth() == 0) {
          node.removeMonster();
          return "You have successfully slayed the Otyugh!!!!!";
        }
        return "You have struck the monster!!";
      } else {
        return "The arrow was lost in the darkness.";
      }
    } else {
      if (node.getExitAndDirections().containsValue(direction)) {
        for (int i : node.getExitAndDirections().keySet()) {
          if (node.getExitAndDirections().get(i) == direction && !node.isTunnel()) {
            return validateArrow(dist - 1, locationMap.get(i), direction);
          } else if (node.getExitAndDirections().get(i) == direction && node.isTunnel()) {
            return validateArrow(dist, locationMap.get(i), direction);
          }
        }
      } else if (node.isTunnel()) {
        for (int i : node.getExitAndDirections().keySet()) {
          if (!(node.getExitAndDirections().get(i).equals(direction.getOpposite()))) {
            return validateArrow(dist, locationMap.get(i), node.getExitAndDirections().get(i));
          }
        }
      }

      return "The arrow was lost in the darkness.";
    }
  }

  @Override
  public int isPlayerAlive() {

    if (currentLocation.isMonsterPresent() && playerAlive) {
      if (currentLocation.getMonster().getHealth() == 1) {
        return r.getRandomNumber(0, 2);
      }
    }
    if (playerAlive) {
      return 1;
    } else {
      return 0;
    }
  }

  private Directions getDirection(String string) {
    for (Directions d : Directions.values()) {
      if (d.toString().equalsIgnoreCase(string)) {
        return d;
      }
    }
    throw new IllegalArgumentException("Illegal direction input");
  }
}
