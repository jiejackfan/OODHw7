package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyModel;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditView extends JFrame implements IView {

  protected JButton playButton, resumeButton, restartButton, speedUpButton, slowDownButton;
  protected JCheckBox repeatBox;
  protected JPanel controlPanel, editPanel, insertPanel, p;

  public EditView(ReadOnlyModel m, int width, int height, int x, int y) {
    super("Swing View of Animation");
    setSize(width + 200, height);
    setLocation(x, y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    p = new JPanel();
    BoxLayout boxLayout = new BoxLayout(p, BoxLayout.X_AXIS);
    p.setLayout(boxLayout);
    //p.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));

    AnimatorPanel animatorPanel = new AnimatorPanel(m);
    animatorPanel.setPreferredSize(new Dimension(width, height));
    p.add(animatorPanel);

    controlPanel = new JPanel();
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
    playButton = new JButton("Play");
    playButton.setActionCommand("Play Button");
    controlPanel.add(playButton);
    resumeButton = new JButton("Pause/Resume");
    resumeButton.setActionCommand("Resume Button");
    controlPanel.add(resumeButton);
    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart Button");
    controlPanel.add(restartButton);
    speedUpButton = new JButton("Speed Up");
    speedUpButton.setActionCommand("Speed Up Button");
    controlPanel.add(speedUpButton);
    slowDownButton = new JButton("Slow Down");
    slowDownButton.setActionCommand("Slow Down Button");
    controlPanel.add(slowDownButton);
    repeatBox = new JCheckBox("repeat");
    repeatBox.setActionCommand("Repeat Box");
    controlPanel.add(repeatBox);
    p.add(controlPanel);

    editPanel = new JPanel();
    editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
    //how to minimize the textbox.
    //https://stackoverflow.com/questions/18405660/how-to-set-component-size-
    //inside-container-with-boxlayout
    editPanel.add(new JTextField(1));
    editPanel.add(new JTextField(1));
    editPanel.add(new JTextField(1));
    editPanel.add(new JTextField(1));
    editPanel.add(new JTextField(1));
    editPanel.add(new JTextField(1));
    editPanel.add(new JButton("Modify"));
    editPanel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
    p.add(editPanel);

    insertPanel = new JPanel();
    insertPanel.setLayout(new BoxLayout(insertPanel, BoxLayout.Y_AXIS));
    insertPanel.add(new JLabel("Shape name:"));
    JTextField box = new JTextField(1);
    box.setMaximumSize(new Dimension(Integer.MAX_VALUE, box.getMinimumSize().height));
    insertPanel.add(box);
    insertPanel.add(new JLabel("Time:"));
    insertPanel.add(new JTextField(1));
    insertPanel.add(new JButton("Insert"));
    insertPanel.add(new JButton("Delete"));
    insertPanel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
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

  @Override
  public void addActionListener(ActionListener actionListener) {
    playButton.addActionListener(actionListener);
    resumeButton.addActionListener(actionListener);
    restartButton.addActionListener(actionListener);
    speedUpButton.addActionListener(actionListener);
    slowDownButton.addActionListener(actionListener);
    repeatBox.addActionListener(actionListener);
  }
}
