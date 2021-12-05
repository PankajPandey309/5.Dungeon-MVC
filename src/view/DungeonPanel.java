package view;

import java.awt.*;

import javax.swing.*;

import model.grid.ReadOnlyDungeonModel;
import model.grid.TraverseGrid;

/**
 * The class initialises the panel for the Dungeon game.
 */
public class DungeonPanel extends JPanel {

  private final ReadOnlyDungeonModel model;
  public static final int CELL_DIMENSION = 90;
  public static final int OFFSET = 80;
  public static final int FONT_SIZE = 50;
  public static final String FONT_TYPE = "Helvetica";


  /**
   * Constructor for DungeonPanel.
   * @param model the dungeon model.
   */
  public DungeonPanel(ReadOnlyDungeonModel model) {

    this.model = model;


  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics2D = (Graphics2D) g;
    for (int i = 1; i < 103; i++) {
      graphics2D.drawLine(i * CELL_DIMENSION + OFFSET, OFFSET,
              i * CELL_DIMENSION + OFFSET, 3 * CELL_DIMENSION + OFFSET);
      graphics2D.drawLine(OFFSET, i * CELL_DIMENSION + OFFSET,
              3 * CELL_DIMENSION + OFFSET, i * CELL_DIMENSION + OFFSET);
    }
    graphics2D.setFont(new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE));

  }


}
