package model.random;


import java.util.Random;

/**
 * class to create random number.
 */
public class RandomNumber implements model.random.Random {

  private final Random r;

  /**
   * Constructor for class.
   *
   * @param seed seed to create random number.
   */
  public RandomNumber(int seed) {
    this.r = new Random(seed);
  }


  /**
   * send a random number.
   *
   * @param min min range.
   * @param max max range.
   * @return random number.
   * @throws IllegalArgumentException if min value is more than max value.
   */
  public int getRandomNumber(int min, int max) throws IllegalArgumentException {
    if (max < min) {
      throw new IllegalArgumentException("MAx cannot be less than min");
    }
    return (r.nextInt(max - min) + min);
  }

  @Override
  public String toString() {
    return "RandomObjectCreated";
  }
}
