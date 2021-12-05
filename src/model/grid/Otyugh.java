package model.grid;

class Otyugh implements Monster {

  private int health;

  public Otyugh(int health) {
    this.health = health;
  }

  @Override
  public void getsHit(int damage) {
    health = health - damage;

  }

  @Override
  public int getHealth() {
    return health;
  }
}
