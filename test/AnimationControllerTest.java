import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IModel;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;
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

    c.playAnimation();
    c.actionPerformed(playButton);
    assertEquals("Play Button", playButton.getActionCommand());
    assertEquals("Pressed play button", c.getTestString());
  }

  @Test
  public void testPlayButtonWrongCommand() {
    ActionEvent playButton = new ActionEvent(v.getPlayButton(),
            ActionEvent.ACTION_PERFORMED, "Play");
//    for (ActionListener l : v.getPlayButton().getActionListeners()) {
    c.playAnimation();
    c.actionPerformed(playButton);
    assertEquals("No such action command", c.getTestString());
//    }

  }

  @Test
  public void testPlayButtonCommandNull() {
    ActionEvent playButton = new ActionEvent(v.getPlayButton(),
            ActionEvent.ACTION_PERFORMED, null);
    c.playAnimation();
    c.actionPerformed(playButton);
    assertEquals("Action command is null", c.getTestString());
  }

  @Test
  public void testTimer() throws InterruptedException {
    c.setDelay(1);
    int currentTick = c.getCurrentTick();
    c.playAnimation();
    TimeUnit.SECONDS.sleep(3);
    assertNotEquals(c.getCurrentTick(), currentTick);
  }

}
