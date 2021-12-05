package model.grid;



/**
 * The Grid interface represents all the functionalities offered by the Dungeon.
 * It allows a user to create a wrapping or non wrapping grid and specify the degree
 * of interconnectivity.
 * The Grid also allots various types of treasures(the percentage is passed by the user)
 * to the caves in the dungeon .
 * A Player enters into the cave marked as start and must traverse the dungeon
 * until he/she reaches the cave marked as 'end'.
 */
interface Grid {

  /**
   * The method assign the properties of individual cells in the grid,
   * such as the number of exits, the directions of exits and the cells
   * to which that particular location is connected.
   */
  void assignLocation();

  /**
   * The method assign treasure to a fixed percentage(passed by the user)
   * of caves in the dungeon.
   */
  void assignTreasure();

  /**
   * The method assign weapons to a fixed percentage(passed by the user)
   * of caves/tunnels in the dungeon.
   */
  void assignWeapons();

  /**
   * Return a copy of the 2d array
   * representing the dungeon.
   *
   * @return the 2d-grid.
   */
  Location[][] getGrid();


}
