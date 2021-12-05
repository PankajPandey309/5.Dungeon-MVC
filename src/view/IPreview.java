package view;

import java.awt.event.ActionListener;
import java.util.List;

public interface IPreview {
  /**
   * Get the string from the text field and return it.
   *
   * @return the input string
   */
  List<String> getDungeonParameters();

  /**
   * this is to force the view to have a method to set up actions for buttons. All
   * the buttons must be given this action listener
   *
   * Thus our Swing-based implementation of this interface will already have such
   * a method.
   *
   * @param listener the listener to add
   */

  void addActionListener(ActionListener listener);

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard
   * listener attached to it, so that keyboard events will still flow through.
   */
  void resetFocus();
}
