package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import javax.swing.*;

import model.grid.ReadOnlyDungeonModel;

/**
 * main view;
 */
public class DungeonMainView extends JFrame implements IView {

  private final JLabel display;
  private final JFrame frame;
  private final DungeonPanel panel;
  private final JMenuBar menuBar;
  private final JMenu gameSettings;
  private final JMenu lobby;
  private final JMenuItem dungeonInfo;
  private final JMenuItem quitGame;
  private final JMenuItem restartGame;
  private final JMenuItem newGame;

  public DungeonMainView(String caption, ReadOnlyDungeonModel model)  {
    super(caption);
    frame = new JFrame();
    display = new JLabel();
    menuBar = new JMenuBar();

    gameSettings = new JMenu("Settings");
    lobby = new JMenu("Lobby");

    dungeonInfo = new JMenuItem("Dungeon Information");
    quitGame = new JMenuItem("Quit Game");
    restartGame = new JMenuItem("Restart Game");
    newGame = new JMenuItem("New Game");


    gameSettings.add(dungeonInfo);
    lobby.add(newGame);
    lobby.add(restartGame);
    lobby.add(quitGame);
    gameSettings.add(lobby);

    menuBar.add(gameSettings);
    frame.setJMenuBar(menuBar);
    frame.add(display);

    frame.setSize(600, 600);
    frame.setLocation(200, 100);


    panel = new DungeonPanel(model);
    Dimension d = new Dimension(1000, 1000);
    panel.setMinimumSize(d);
    panel.setMaximumSize(d);
    panel.setPreferredSize(d);
    JScrollPane jsp = new JScrollPane(panel);
    frame.getContentPane().add(jsp);



    frame.setVisible(true);

  }


  @Override
  public void displayDungeonInfo(List<String> parameters) {

    String info = "\nRows: " + parameters.get(0)
            + "\nColumns: " + parameters.get(1)
            + "\nWrapping Status: "+ parameters.get(2)
            + "\nInterconnectivity: "+parameters.get(3)
            + "\nTreasure Percentage: "+parameters.get(4)
            + "\nMonsters :"+ parameters.get(5);
    JOptionPane.showMessageDialog(this, info,
            "DUNGEON INFORMATION",
            JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void resetFocus() {

  }

  @Override
  public void addActionListener(ActionListener listener) {
    dungeonInfo.addActionListener(listener);
    quitGame.addActionListener(listener);
    newGame.addActionListener(listener);
    restartGame.addActionListener(listener);
  }
  @Override
  public void addActionPerformed(ActionEvent e) {

  }

  @Override
  public void closeTab() {
    frame.setVisible(false);
    frame.dispose();
  }
}
