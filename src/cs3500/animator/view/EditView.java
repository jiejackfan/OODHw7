package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyModel;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditView extends JFrame implements IView {

  public EditView(ReadOnlyModel m, int width, int height, int x, int y) {
    super("Swing View of Animation");
    setSize(width + 200, height);
    setLocation(x, y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel p = new JPanel();
    BoxLayout boxLayout = new BoxLayout(p, BoxLayout.X_AXIS);
    p.setLayout(boxLayout);
    //p.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));

    AnimatorPanel animatorPanel = new AnimatorPanel(m);
    animatorPanel.setPreferredSize(new Dimension(width, height));
    p.add(animatorPanel);

    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
    controlPanel.add(new JButton("Play"));
    controlPanel.add(new JButton("Pause/Resume"));
    controlPanel.add(new JButton("Restart"));
    controlPanel.add(new JButton("Speed Up"));
    controlPanel.add(new JButton("Slow Down"));
    controlPanel.add(new JCheckBox("repeat"));
    p.add(controlPanel);

    JPanel editPanel = new JPanel();
    editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
    editPanel.add(new JTextField(1));
    editPanel.add(new JTextField(1));
    editPanel.add(new JTextField(1));
    editPanel.add(new JTextField(1));
    editPanel.add(new JTextField(1));
    editPanel.add(new JTextField(1));
    editPanel.add(new JButton("Modify"));
    p.add(editPanel);

    JPanel insertPanel = new JPanel();
    insertPanel.setLayout(new BoxLayout(insertPanel, BoxLayout.Y_AXIS));
    insertPanel.add(new JTextField(1));
    insertPanel.add(new JTextField(1));
    insertPanel.add(new JButton("Insert"));
    insertPanel.add(new JButton("Delete"));
    p.add(insertPanel);

    add(p);
    pack();

  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void render() {
    this.setVisible(true);

  }

  @Override
  public void setOutputFileName(String outputFileName) {
    setTitle(outputFileName);
  }

  @Override
  public void setDelay(int delay) {

  }
}
