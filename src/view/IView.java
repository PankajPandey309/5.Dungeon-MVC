package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * The interface for the view class.
 * It defines the various events that the view must handle such
 * as keyboard inputs and text fields etc.
 */
public interface IView {


  /**
   * display the dungeon information.
   *
   * @param parameters the dungeon information.
   */
  void displayDungeonInfo(List<String> parameters);



  /**
   * Reset the focus on the appropriate part of the view that has the keyboard
   * listener attached to it, so that keyboard events will still flow through.
   */
  void resetFocus();

  /**
   * this is to force the view to have a method to set up the keyboard. The name
   * has been chosen deliberately. This is the same method signature to add a key
   * listener in Java Swing.
   *
   * Thus our Swing-based implementation of this interface will already have such
   * a method.
   *
   * @param listener the listener to add
   */
  void addKeyListener(KeyListener listener);

  /**
   * this is to force the view to have a method to set up actions for buttons. All
   * the buttons must be given this action listener
   *
   * Thus our Swing-based implementation of this interface will already have such
   * a method.
   *
   * @param listener the listener to add.
   */

  void addActionListener(ActionListener listener);

  /**
   * this is to force the view to have a method to set up actions for clicks. All
   * the clicks must be given this action listener
   *
   * Thus our Swing-based implementation of this interface will already have such
   * a method.
   *
   * @param e the actionevent to add.
   */
  void addActionPerformed(ActionEvent e);

  void closeTab();

}
