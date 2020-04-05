package cs3500.animator.view;

import java.awt.Color;

public interface IEditView extends IView {
  boolean getCheckState();
  void changeResumeButtonColor(Color color);
}
