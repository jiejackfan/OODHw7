import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IModel;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;
import org.junit.Test;

public class AnimationControllerTest {

  // Helper function to help setup model controller view objects so tests can
  //  manipulate those objects.
  IModel m = new AnimationModel();
  IEditView v = new EditView(m, 800, 800, 0, 0);
  IController c = new AnimationController(v, m);

  @Test
  public void testModelBuild() {

  }
}
