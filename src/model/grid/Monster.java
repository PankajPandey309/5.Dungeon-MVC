package model.grid;

/**
 * The monster interface represents the characteristics of all the different
 * monsters present in the dungeon. It defines their ability to get hit , and returns
 * their health.
 */
interface Monster {

  /**
   * decreases the health of the monster when its hit.
   * @param damage the damage caused by the weapon.
   */
  void getsHit(int damage);

  /**
   * returns the health of the monster.
   * @return the health of monster.
   */
  int getHealth();


}
