import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IModel;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.junit.ComparisonFailure;
import org.junit.Test;

/**
 *
 */
public class AnimationControllerTest {

  // Helper function to help setup model controller view objects so tests can
  // manipulate those objects.
  IModel m = new AnimationModel();
  IEditView v = new EditView(m, 1000, 1000, 0, 0);
  AnimationController c = new AnimationController(v, m);

  @Test
  public void testPlayButton() {
    ActionEvent playButton = new ActionEvent(v.getPlayButton(),
            ActionEvent.ACTION_PERFORMED, "Play Button");
//    for (ActionListener l : v.getPlayButton().getActionListeners()) {
    c.playAnimation();
    c.actionPerformed(playButton);
    assertEquals("Play Button", playButton.getActionCommand());
//    }
  }

  @Test(expected = ComparisonFailure.class)
  public void testPlayButtonWrongCommand() {
    ActionEvent playButton = new ActionEvent(v.getPlayButton(),
            ActionEvent.ACTION_PERFORMED, "Play");
//    for (ActionListener l : v.getPlayButton().getActionListeners()) {
    c.playAnimation();
    c.actionPerformed(playButton);
//    }
  }

  @Test(expected = ComparisonFailure.class)
  public void testPlayButtonCommandNull() {
    ActionEvent playButton = new ActionEvent(v.getPlayButton(),
            ActionEvent.ACTION_PERFORMED, null);
    c.playAnimation();
    c.actionPerformed(playButton);
  }

}
