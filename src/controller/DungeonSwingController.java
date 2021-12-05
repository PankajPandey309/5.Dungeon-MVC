package controller;

import model.grid.TraverseGrid;
import model.grid.TraverseGridImpl;
import model.random.Random;
import model.random.RandomNumber;
import view.ButtonListener;
import view.DungeonMainView;
import view.DungeonPreviewView;
import view.IPreview;
import view.IView;
import view.KeyboardListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of our GUI controller.
 */
public class DungeonSwingController implements SwingController {

  private TraverseGrid model;
  private IPreview preview;
  private IView view;
  private Random r;
  private List<String> parameters;

  /**
   * Constructor to initialise the controller.
   */
  public DungeonSwingController() {
    parameters = new ArrayList<>();
    r = new RandomNumber(0);
    r = new RandomNumber(r.getRandomNumber(0, 1000));

  }

  @Override
  public void setModel(TraverseGrid model) {
    this.model = model;

  }


  @Override
  public void setPreview(IPreview preview) {
    this.preview = preview;
    configurePreViewButtonListener();

  }

  /**
   * Creates and sets a keyboard listener for the view In effect it creates
   * snippets of code as Runnable object, one for each time a key is typed,
   * pressed and released, only for those that the program needs.
   *
   * <p>In this example, we need to toggle color when user TYPES 'd', make the
   * message all caps when the user PRESSES 'c' and reverts to the original string
   * when the user RELEASES 'c'. Thus we create three snippets of code and put
   * them in the appropriate map. This example shows how to create these snippets
   * of code using lambda functions, a new feature in Java 8.
   *
   * <p>For more information on Java 8's syntax of lambda expressions, go here:
   * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
   *
   * <p>The above tutorial has specific example for GUI listeners.
   *
   * <p>Last we create our KeyboardListener object, set all its maps and then give it
   * to the view.
   */
  private void configureKeyBoardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

//    keyTypes.put('d', () -> view.toggleColor()); // the contents of ToggleColor below
//
//    keyPresses.put(KeyEvent.VK_C, () -> { // the contents of MakeCaps below
//              String text = model.getString();
//              text = text.toUpperCase();
//              view.setEchoOutput(text);
//            }
//
//    );
//    keyReleases.put(KeyEvent.VK_C, () -> { // the contents of MakeOriginal below
//              String text = model.getString();
//              view.setEchoOutput(text);
//            }
//
//    );

    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    view.addKeyListener(kbd);
  }

  /**
   * Setting up the button listeners.
   */
  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Dungeon Information", () -> {
      view.displayDungeonInfo(parameters);
    });

    buttonClickedMap.put("Quit Game", () -> System.exit(0));

    buttonClickedMap.put("New Game", () -> {
      this.view.closeTab();
      this.setPreview(new DungeonPreviewView(""));
      this.view.resetFocus();
      this.preview.resetFocus();
    });

    buttonClickedMap.put("Restart Game", () -> {
      this.view.closeTab();
      this.view = new DungeonMainView("Stay your blades, adventurers!",model);
      this.view.resetFocus();
    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  /**
   * Setting up the button listeners.
   */
  private void configurePreViewButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Build Dungeon", () -> {
      parameters = preview.getDungeonParameters();
      this.setModel(new TraverseGridImpl(Integer.parseInt(parameters.get(0)),
              Integer.parseInt(parameters.get(1)),
              Boolean.parseBoolean(parameters.get(2)),
              Integer.parseInt(parameters.get(3)),
              Integer.parseInt(parameters.get(4)),
              Integer.parseInt(parameters.get(5)),
              parameters.get(6),
              r.getRandomNumber(0, 1000)));
      this.view = new DungeonMainView("Stay your blades, adventurers!",model);
      configureButtonListener();
      this.preview.resetFocus();

    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.preview.addActionListener(buttonListener);


  }
}
