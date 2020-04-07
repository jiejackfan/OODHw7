package cs3500.animator.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public interface IEditView extends IView {

  /**
   * this is to force the view to have a method to set up actions for buttons.
   * All the buttons must be given this action listener
   * <p>
   * Thus our Swing-based implementation of this interface will already have such a
   * method.
   *
   * @param listener
   */

  void addActionListener(ActionListener listener);

  boolean getCheckState();
  void changeResumeButtonColor(Color color);
  List<String> getShapePanelInput();
  List<String> getInsertPanelInput();
  List<String> getEditPanelInput();
  void showErrorMsg(String string);
  File getLoadLocation();
  File getSaveLocation();

}
