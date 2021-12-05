

import controller.DungeonSwingController;
import controller.SwingController;
import view.DungeonPreviewView;
import view.IPreview;


/**
 * The driver class represents the jar file which will be run from the command line.
 */
public class Driver {

  /**
   * The main method is reun by the IDE to start the project.
   *
   * @param args commandline inputs
   */
  public static void main(String[] args) {

    SwingController controller = new DungeonSwingController();

    IPreview preview = new DungeonPreviewView("");

    controller.setPreview(preview);
//    try {
//      int row = Integer.parseInt(args[0]);
//      int col = Integer.parseInt(args[1]);
//      boolean wrap = Boolean.parseBoolean(args[2]);
//      int interconnectivity = Integer.parseInt(args[3]);
//      int treasurePer = Integer.parseInt(args[4]);
//      int monster = Integer.parseInt(args[5]);
//      String name = args[6];
//      int seed = Integer.parseInt(args[7]);
//      TraverseGrid dungeon = new TraverseGridImpl(
//              row, col, wrap, interconnectivity, treasurePer, monster, name, seed);
//
//      Readable reader = new InputStreamReader(System.in);
//      DungeonController controller = new Controller(reader, System.out);
//      controller.playGame(dungeon);
//    } catch (NumberFormatException e) {
//      System.out.println("Not an integer\n");
//    } catch (IllegalArgumentException e) {
//      System.out.println(e.getMessage());
//    }
//
//  }
  }


}


