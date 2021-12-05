package controller;

import controller.commands.IDungeonCommands;
import controller.commands.Move;
import controller.commands.PickArrow;
import controller.commands.PickTreasure;
import controller.commands.PlayerDesc;
import controller.commands.ShootArrow;
import model.grid.Directions;
import model.grid.TraverseGrid;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;


/**
 * This class serves as the concrete implementation for the
 * text based dungeon Adventure game. It takes the Dungeon model as the input
 * and simulates a complete game.
 */
public class Controller implements DungeonController {

  private final Appendable out;
  private final Scanner scan;
  private final Map<String, Function<Scanner, IDungeonCommands>> commands;
  private final Map<String, String> instructions;


  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public Controller(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
    commands = new HashMap<>();
    commands.put("m", scan -> new Move(scan.next()));
    commands.put("p", scan -> new PlayerDesc());
    commands.put("w", scan -> new PickArrow());
    commands.put("t", scan -> new PickTreasure());
    commands.put("s", scan -> new ShootArrow(scan.next(), scan.next()));
    instructions = new HashMap<>();
    instructions.put("m", "Enter the direction you want to move in(Ex. east).\n");
    instructions.put("s", "Enter the distance and direction you want to shoot in(Ex. 2 west).\n");

  }

  @Override
  public void playGame(TraverseGrid model) {

    int quit = 0;

    if (model == null) {
      throw new IllegalArgumentException("Invalid model");
    }
    try {
      while (model.isPlayerAlive() == 1 && !model.isDestinationReached()) {
        printMonologue(model);
        IDungeonCommands c;
        try {
          String in = scan.next();
          if (in.equalsIgnoreCase("q")) {
            out.append("Ending game\n");
            quit = 1;
            break;
          }
          if (instructions.containsKey(in)) {
            out.append(instructions.get(in));
          }
          Function<Scanner, IDungeonCommands> cmd = commands.getOrDefault(in, null);
          if (cmd == null) {
            out.append("Invalid command\n");
          } else {
            c = cmd.apply(scan);
            out.append(c.execute(model).toString()).append("\n");
          }
        } catch (NumberFormatException nfe) {
          out.append("Not a number\n");
        } catch (IllegalArgumentException e) {
          out.append(e.getMessage()).append("\n");
        }
      }
      if (!model.isDestinationReached() && quit == 0) {
        out.append(model.getPlayerDescription().keySet().toString()
                + " was torn to pieces and eaten by the Otyugh!!");
      }
    } catch (IOException e) {
      scan.close();
      throw new IllegalStateException("append failed" + e);
    }
    scan.close();
  }


  private void printMonologue(TraverseGrid model) throws IOException {

    out.append("\nYou are in a ").append(model.getLocationDetails()
            .keySet().toString()).append("\n");
    out.append(model.getSmell());
    out.append("\nThe possible directions you can exit are: \n");
    for (Map<List<Directions>, Map<String, Integer>> list : model.getLocationDetails().values()) {
      for (List<Directions> t : list.keySet()) {
        out.append(t.toString());
      }
    }
    out.append("\nThe  items present in the cave are: \n");
    for (Map<List<Directions>, Map<String, Integer>> list : model.getLocationDetails().values()) {
      for (Map<String, Integer> t : list.values()) {
        out.append(t.toString());
      }
    }

    out.append("\n\nWhat do you want to do?\n");
    out.append("1.Move(Enter m)\n2.Shoot(Enter s)\n3.Pickup(Enter w for Weapons , t for Treasure)\n"
            + "4.Player Description(Enter p)\n5.Quit game(Enter q)\n");

  }
}
