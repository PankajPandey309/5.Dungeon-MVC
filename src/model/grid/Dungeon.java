package model.grid;


import model.random.Random;
import model.random.RandomNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/**
 * The Dungeon class implements the Grid interface.
 * A Dungeon class represents a single dungeon which could be
 * a wrapping or non wrapping dungeon. The dungeon has a certain
 * percentage(value passed by the user) of treasure in the caves  which
 * is assigned to player when it enters that cave.
 */
class Dungeon implements Grid {

  //Some variables are declared as package private because there being
  //accessed by the TraverseGridImpl class which is extending the Dungeon
  //class.

  final int rows;
  final int columns;
  private final boolean wrapping;
  private final int interconnectivity;
  private final int treasurePercentage;
  int[][] gridTracker;
  Random r;
  private final List<List<Integer>> possiblePaths = new ArrayList<>();
  private final List<List<Integer>> leftOverEdge = new ArrayList<>();
  private final List<List<Integer>> setOfPaths = new ArrayList<>();
  private final Map<Integer, List<Integer>> adjacencyMatrix = new HashMap<>();
  Map<Integer, Location> locationMap = new HashMap<>();
  Location[][] grid;
  private int diamonds;
  private int sapphires;
  private int rubies;
  int caves;
  Location source;
  Location destination;
  Location currentLocation;
  Set<Location> startList;
  List<Set<Location>> possibleList;
  private final int monstersInDungeon;


  /**
   * The constructor initialises all required fields which are to be used in the program.
   * The create dungeon initializes the dungeon based on Kruskal's algorithm.
   * It generates the possible paths based on
   * whether the dungeon is a wrapping or a non wrapping dungeon.
   * After the possible paths have been generated , we generate a
   * minimum spanning tree with interconnectivity one and a list
   * of leftOver edges.
   * The next step is to set the interconnectivity of the dungeon as
   * specified by the user. For an interconnectivity 'n' , we select
   * 'n' random edges from the leftOverEdge list and include it in our dungeon.
   *
   * @param rows               the number of rows in the dungeon.
   * @param columns            the number of columns in the dungeon.
   * @param wrapping           boolean which signifies whether the dungeon is wrapping or not.
   * @param interconnectivity  the interconnectivity of the dungeon.
   * @param treasurePercentage the percentage of caves which must have treasure.
   * @param monstersInDungeon  the number of monster present in the dungeon.
   * @param seed               the seed value of the random number generator.
   * @throws IllegalArgumentException if any values is null or invalid.
   */
  public Dungeon(int rows, int columns, boolean wrapping,
                 int interconnectivity, int treasurePercentage,
                 int monstersInDungeon, int seed) throws IllegalArgumentException {
    if (rows < 5 || columns < 5 || interconnectivity < 0 || monstersInDungeon < 1
            || treasurePercentage < 0) {
      throw new
              IllegalArgumentException("Invalid row/column/interconnectivity/"
              + "treasure percentage values");
    }
    this.rows = rows;
    this.columns = columns;
    this.wrapping = wrapping;
    this.interconnectivity = interconnectivity;
    this.treasurePercentage = treasurePercentage;
    gridTracker = new int[rows][columns];
    r = new RandomNumber(seed);
    grid = new Location[rows][columns];
    this.diamonds = 0;
    this.sapphires = 0;
    this.rubies = 0;
    this.caves = 0;
    this.monstersInDungeon = monstersInDungeon;
    startList = new HashSet<>();
    possibleList = new ArrayList<>();
    createDungeon();
  }


  private void createDungeon() {
    int counter = 1;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {

        gridTracker[i][j] = counter++;

      }
    }
    createPossiblePaths();
    generateLeftoverEdgeList();
    updateInterconnectivity();
    assignLocation();
    assignTreasure();
    defineStartAndEnd();
    assignMonsters();
    assignWeapons();

  }

  private void createPossiblePaths() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        List<Integer> path = new ArrayList<>();
        if (gridTracker[i][j] % columns == 0) {
          if (wrapping && columns != 1) {
            path.add(gridTracker[i][0]);
            path.add(gridTracker[i][j]);
            if (!possiblePaths.contains(path)) {
              possiblePaths.add(path);
            }
            path = new ArrayList<>();
          }
        } else {
          path.add(gridTracker[i][j]);
          path.add(gridTracker[i][j + 1]);
          possiblePaths.add(path);
          path = new ArrayList<>();
        }
        if (gridTracker[i][j] + columns > rows * columns) {
          if (wrapping && rows != 1) {
            path.add(gridTracker[0][j]);
            path.add(gridTracker[i][j]);
            if (!possiblePaths.contains(path)) {
              possiblePaths.add(path);
            }
          }
        } else {
          path.add(gridTracker[i][j]);
          path.add(gridTracker[i + 1][j]);
          possiblePaths.add(path);
        }
      }
    }

  }

  private void generateLeftoverEdgeList() {
    int rand;

    while (possiblePaths.size() != 0) {

      rand = r.getRandomNumber(0, possiblePaths.size());

      List<Integer> path = possiblePaths.get(rand);

      possiblePaths.remove(rand);
      createSet(path);

    }
  }

  private void createSet(List<Integer> path) {

    List<Integer> index1 = new ArrayList<>();
    List<Integer> index2 = new ArrayList<>();

    if (setOfPaths.isEmpty()) {
      setOfPaths.add(path);
      updateAdjacencyMatrix(path);
    } else {
      for (List<Integer> list : setOfPaths) {
        if (list.contains(path.get(0))) {
          index1 = list;


        }
        if (list.contains(path.get(1))) {
          index2 = list;


        }
      }
      if (index1.equals(index2) && !index1.isEmpty()) {

        leftOverEdge.add(path);

      } else {
        updateAdjacencyMatrix(path);

        if (index1.isEmpty() && !index2.isEmpty()) {
          index2.add(path.get(0));
        } else if (!index1.isEmpty() && index2.isEmpty()) {
          index1.add(path.get(1));
        } else if (index1.isEmpty()) {
          setOfPaths.add(path);
        } else {
          index1.addAll(index2);
          setOfPaths.remove(index2);
        }
      }
    }


  }


  private void updateAdjacencyMatrix(List<Integer> path) {
    List<Integer> pathTo1 = new ArrayList<>();
    List<Integer> pathTo2 = new ArrayList<>();
    if (adjacencyMatrix.get(path.get(0)) != null) {
      pathTo1 = adjacencyMatrix.get(path.get(0));
    }
    pathTo1.add(path.get(1));
    adjacencyMatrix.put(path.get(0), pathTo1);
    if (adjacencyMatrix.get(path.get(1)) != null) {
      pathTo2 = adjacencyMatrix.get(path.get(1));
    }
    pathTo2.add(path.get(0));
    adjacencyMatrix.put(path.get(1), pathTo2);
  }


  private void updateInterconnectivity() {
    if (interconnectivity > leftOverEdge.size()) {
      throw new IllegalArgumentException("Interconnectivity is not feasible for the dungeon");
    }
    int bk = interconnectivity;
    while (bk > 0) {
      int rand = r.getRandomNumber(0, leftOverEdge.size());
      List<Integer> path = leftOverEdge.get(rand);
      leftOverEdge.remove(rand);
      updateAdjacencyMatrix(path);
      bk--;
    }

  }

  @Override
  public void assignLocation() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        grid[i][j] = new Cave(gridTracker[i][j], adjacencyMatrix.get(gridTracker[i][j]), columns);
        locationMap.put(gridTracker[i][j], grid[i][j]);
        if (adjacencyMatrix.get(gridTracker[i][j]).size() != 2) {
          caves++;
        }
      }
    }
  }

  @Override
  public void assignTreasure() {
    int bk = caves * treasurePercentage / 100;
    if (treasurePercentage != 0 && treasurePercentage != 100) {
      bk += r.getRandomNumber(0, caves - bk);
    }
    while (bk != 0) {
      int row = r.getRandomNumber(0, rows);
      int cols = r.getRandomNumber(0, columns);
      if (grid[row][cols].hasTreasure()) {
        continue;
      } else {
        assignTreasureToCell(grid[row][cols]);
      }
      bk--;
    }

  }

  private void assignTreasureToCell(Location cell) {
    List<Treasure> treasureList = new ArrayList<>();
    Treasure[] treasures = Treasure.values();
    List<Treasure> availableTreasure = new ArrayList<>();
    Collections.addAll(availableTreasure, treasures);

    int rand;
    if (diamonds == 0) {
      treasureList.add(Treasure.DIAMONDS);
      diamonds++;
    } else if (sapphires == 0) {
      treasureList.add(Treasure.SAPPHIRES);
      sapphires++;
    } else if (rubies == 0) {
      treasureList.add(Treasure.RUBIES);
      rubies++;
    } else {
      int x = r.getRandomNumber(1, (availableTreasure.size() + 1));
      for (int i = 0; i < x; i++) {
        rand = r.getRandomNumber(0, availableTreasure.size());
        treasureList.add(availableTreasure.get(rand));
        availableTreasure.remove(rand);
      }
    }
    cell.getTreasure(treasureList);
  }

  @Override
  public Location[][] getGrid() {

    return Arrays.stream(grid)
            .map(Location[]::clone)
            .toArray(Location[][]::new);
  }

  private void defineStartAndEnd() throws IllegalArgumentException {
    int startX;
    int startY;
    startX = r.getRandomNumber(0, rows);
    startY = r.getRandomNumber(0, columns);
    int endX;
    int endY;
    endX = r.getRandomNumber(0, rows);
    endY = r.getRandomNumber(0, columns);
    if (caves == 0) {
      throw new IllegalStateException("No caves present in dungeon");
    }
    if (grid[startX][startY].equals(grid[endX][endY])
            || grid[startX][startY].isTunnel() || grid[endX][endY].isTunnel()) {
      defineStartAndEnd();
    } else {
      source = grid[startX][startY];
      currentLocation = source;
      destination = grid[endX][endY];
      if (checkMinDistance(source).get(destination) < 5) {
        startList = new HashSet<>();
        startList.add(source);
        startList.add(destination);
        possibleList.add(startList);
        if (possibleList.size() == caves * (caves - 1)) {
          throw new IllegalArgumentException("There is not combination of "
                  + "start and end caves where the min distance is 5 or greater.");
        } else {
          defineStartAndEnd();
        }
      }
    }
  }

  Map<Location, Integer> checkMinDistance(Location startNode) {
    Map<Location, Integer> distanceTracker = new HashMap<>();
    List<Location> visited = new ArrayList<>();
    List<Location> queue = new ArrayList<>();
    visited.add(startNode);
    queue.add(startNode);
    distanceTracker.put(startNode, 0);
    while (queue.size() != 0) {
      Location currentNode = queue.remove(0);
      for (Integer i : currentNode.getExitAndDirections().keySet()) {
        if (!visited.contains(locationMap.get(i))) {
          queue.add(locationMap.get(i));
          distanceTracker.put(locationMap.get(i), distanceTracker.get(currentNode) + 1);
          visited.add(locationMap.get(i));
        }
      }
      if (currentNode.equals(destination)) {
        return distanceTracker;
      }
    }
    return distanceTracker;
  }

  private void assignMonsters() {
    List<Location> cavesWithMonster = new ArrayList<>();
    if (monstersInDungeon > caves) {
      throw new IllegalArgumentException("Monsters amount not feasable");
    }
    destination.assignMonster();
    cavesWithMonster.add(destination);
    int i = 0;
    while (i < monstersInDungeon - 1) {
      int x;
      int y;
      x = r.getRandomNumber(0, rows);
      y = r.getRandomNumber(0, columns);
      if (grid[x][y].equals(source) || grid[x][y].isTunnel()) {
        continue;
      } else if (!cavesWithMonster.contains(grid[x][y])) {
        grid[x][y].assignMonster();
        i++;
        cavesWithMonster.add(grid[x][y]);
      }
    }
  }

  @Override
  public void assignWeapons() {
    int bk = (int) Math.ceil(rows * columns * treasurePercentage / 100);
    List<Location> locationsWithWeapons = new ArrayList<>();
    while (bk != 0) {
      Map<Weapons, Integer> weaponsIntegerMap = new TreeMap<>();
      int row = r.getRandomNumber(0, rows);
      int cols = r.getRandomNumber(0, columns);
      if (locationsWithWeapons.contains(grid[row][cols])) {
        continue;
      } else {
        for (Weapons w : Weapons.values()) {
          weaponsIntegerMap.put(w, r.getRandomNumber(1, 4));
        }
        grid[row][cols].addWeapons(weaponsIntegerMap);
        locationsWithWeapons.add(grid[row][cols]);
        bk--;
      }
    }
  }
}









