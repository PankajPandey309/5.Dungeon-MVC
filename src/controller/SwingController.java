package controller;

import model.grid.TraverseGrid;
import view.IPreview;

/**
 * Interface for the swing controller.
 * IT defines the various functionalities that must be performed when
 * certain keys or buttons are pressed.
 */
public interface SwingController {

  /**
   * Sets the preview to use.
   *
   * @param preview the view
   */
  void setPreview(IPreview preview);

  /**
   * Sets the model to use.
   *
   * @param model the model.
   */
  void setModel(TraverseGrid model);
}
