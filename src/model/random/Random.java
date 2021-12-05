package model.random;

/**
 * interface for random number generator.
 * It returns a random value in the range [min-max).
 * The random value returned decides the fate of the battle.
 */
public interface Random {

  /**
   * Method to generate random number.
   *
   * @return random number;
   */
  int getRandomNumber(int min, int max);


  @Override
  String toString();

}
