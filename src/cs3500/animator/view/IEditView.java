package cs3500.animator.view;

import java.awt.Color;
import java.util.List;

public interface IEditView extends IView {
  boolean getCheckState();
  void changeResumeButtonColor(Color color);
  List<String> getShapePanelInput();
  List<String> getInsertPanelInput();
  List<String> getEditPanelInput();
  void showErrorMsg(String string);

}
