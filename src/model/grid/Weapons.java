package model.grid;

/**
 * The enum represents the various weapons in the dungeon and
 * returns their damage.
 */
public enum Weapons {
  ARROWS(1);

  private final int damage;

  Weapons(int damage) {
    this.damage = damage;
  }

  /**
   * return the damage of the weapon.
   *
   * @return weapon damage.
   */
  public int getDamage() {
    return damage;
  }
}
