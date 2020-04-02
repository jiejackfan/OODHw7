package cs3500.animator.view;

import cs3500.animator.model.IModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditView extends JFrame implements IView {

  public EditView(IModel m, int width, int height, int x, int y) {
    super("Swing View of Animation");
    setSize(width, height);
    setLocation(x, y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel p = new JPanel();
    p.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(2,2,2,2);
    c.gridx = 0;
    c.gridy = 0;
    c.ipadx = 15;
    c.ipady = 50;

    p.add(new AnimatorPanel(m));


  }

  @Override
  public void refresh() {

  }

  @Override
  public void render() {

  }

  @Override
  public void setOutputFileName(String outputFileName) {

  }

  @Override
  public void setDelay(int delay) {

  }
}
