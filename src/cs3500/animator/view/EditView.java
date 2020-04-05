package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditView extends JFrame implements IEditView {

  //variable for contorl panel
  protected JButton playButton, resumeButton, restartButton, speedUpButton, slowDownButton;
  protected JCheckBox repeatBox;

  //variable for all panels in the frame
  protected JPanel controlPanel, editPanel, insertPanel, p, shapePanel;

  //variable for modifying keyframes of a shape. For edit panel.
  protected JButton modifyKeyframeButton;
  protected JTextField modifyKeyframeName, modifyKeyframeTime, modifyKeyframePosition,
    modifyKeyframeWidth, modifyKeyframeHeight, modifyKeyframeColor;

  //variables for creating and deleting keyframs of a shape
  protected JTextField keyframeInsertDeleteName, keyframeInsertDeleteTime;
  protected JButton insertKeyframeButton, deleteKeyframeButton;

  //variables for creating and deleting shapes
  protected JButton insertShapeButton, deleteShapeButton;
  protected JTextField shapeShapeName, shapeShapeType;

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
    resumeButton.setBackground(Color.GREEN);
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
    editPanel.add(new JLabel("Shape name:"));
    modifyKeyframeName = new JTextField(1);
    modifyKeyframeName.setMaximumSize(new Dimension(Integer.MAX_VALUE,
        modifyKeyframeName.getMinimumSize().height));
    editPanel.add(modifyKeyframeName);
    editPanel.add(new JLabel("Shape at time:"));
    modifyKeyframeTime = new JTextField(1);
    modifyKeyframeTime.setMaximumSize(new Dimension(Integer.MAX_VALUE,
        modifyKeyframeTime.getMinimumSize().height));
    editPanel.add(modifyKeyframeTime);
    editPanel.add(new JLabel("Shape position: x y"));
    modifyKeyframePosition = new JTextField(1);
    modifyKeyframePosition.setMaximumSize(new Dimension(Integer.MAX_VALUE,
        modifyKeyframePosition.getMinimumSize().height));
    editPanel.add(modifyKeyframePosition);
    editPanel.add(new JLabel("Shape width:"));
    modifyKeyframeWidth = new JTextField(1);
    modifyKeyframeWidth.setMaximumSize(new Dimension(Integer.MAX_VALUE,
        modifyKeyframeWidth.getMinimumSize().height));
    editPanel.add(modifyKeyframeWidth);
    editPanel.add(new JLabel("Shape height:"));
    modifyKeyframeHeight = new JTextField(1);
    modifyKeyframeHeight.setMaximumSize(new Dimension(Integer.MAX_VALUE,
        modifyKeyframeHeight.getMinimumSize().height));
    editPanel.add(modifyKeyframeHeight);
    editPanel.add(new JLabel("Shape color: r g b"));
    modifyKeyframeColor = new JTextField(1);
    modifyKeyframeColor.setMaximumSize(new Dimension(Integer.MAX_VALUE,
        modifyKeyframeColor.getMinimumSize().height));
    editPanel.add(modifyKeyframeColor);
    modifyKeyframeButton = new JButton("Modify");
    modifyKeyframeButton.setActionCommand("Modify Keyframe");
    editPanel.add(modifyKeyframeButton);
    editPanel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
    p.add(editPanel);

    insertPanel = new JPanel();
    insertPanel.setLayout(new BoxLayout(insertPanel, BoxLayout.Y_AXIS));
    insertPanel.add(new JLabel("Shape name:"));
    keyframeInsertDeleteName = new JTextField(1);
    keyframeInsertDeleteName.setMaximumSize(new Dimension(Integer.MAX_VALUE,
        keyframeInsertDeleteName.getMinimumSize().height));
    insertPanel.add(keyframeInsertDeleteName);
    insertPanel.add(new JLabel("Time:"));
    keyframeInsertDeleteTime = new JTextField(1);
    keyframeInsertDeleteTime.setMaximumSize(new Dimension(Integer.MAX_VALUE,
        keyframeInsertDeleteTime.getMinimumSize().height));
    insertPanel.add(keyframeInsertDeleteTime);
    insertKeyframeButton = new JButton("Insert");
    insertKeyframeButton.setActionCommand("Insert Keyframe");
    deleteKeyframeButton = new JButton("Delete");
    deleteKeyframeButton.setActionCommand("Delete Keyframe");
    insertPanel.add(insertKeyframeButton);
    insertPanel.add(deleteKeyframeButton);
    insertPanel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
    p.add(insertPanel);

    shapePanel = new JPanel();
    shapePanel.setLayout(new BoxLayout(shapePanel, BoxLayout.Y_AXIS));
    shapePanel.add(new JLabel("Shape Type:"));
    shapeShapeType = new JTextField(1);
    shapeShapeType.setMaximumSize(new Dimension(Integer.MAX_VALUE,
        shapeShapeType.getMinimumSize().height));
    shapePanel.add(shapeShapeType);
    shapePanel.add(new JLabel("Shape Name:"));
    shapeShapeName = new JTextField(1);
    shapeShapeName.setMaximumSize(new Dimension(Integer.MAX_VALUE,
        shapeShapeName.getMinimumSize().height));
    shapePanel.add(shapeShapeName);
    insertShapeButton = new JButton("Create Shape");
    insertShapeButton.setActionCommand("Create Shape");
    deleteShapeButton = new JButton("Delete Shape");
    deleteShapeButton.setActionCommand("Delete Shape");
    shapePanel.add(insertShapeButton);
    shapePanel.add(deleteShapeButton);
    shapePanel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
    p.add(shapePanel);

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
    insertKeyframeButton.addActionListener(actionListener);
    deleteKeyframeButton.addActionListener(actionListener);
    insertShapeButton.addActionListener(actionListener);
    deleteShapeButton.addActionListener(actionListener);
    modifyKeyframeButton.addActionListener(actionListener);
  }

  @Override
  public boolean getCheckState(){
    return repeatBox.isSelected();
  }

  @Override
  public void changeResumeButtonColor(Color color) {
    resumeButton.setBackground(color);
  }

  @Override
  public List<String> getShapePanelInput() {
    List<String> tmp = new ArrayList<>();
    tmp.add(shapeShapeType.getText());
    tmp.add(shapeShapeName.getText());
    return tmp;
  }

  @Override
  public List<String> getInsertPanelInput() {
    List<String> tmp = new ArrayList<>();
    tmp.add(keyframeInsertDeleteName.getText());
    tmp.add(keyframeInsertDeleteTime.getText());
    return tmp;
  }

  @Override
  public List<String> getEditPanelInput() {
    List<String> tmp = new ArrayList<>();
    tmp.add(modifyKeyframeName.getText());
    tmp.add(modifyKeyframeTime.getText());
    tmp.add(modifyKeyframePosition.getText());
    tmp.add(modifyKeyframeWidth.getText());
    tmp.add(modifyKeyframeHeight.getText());
    tmp.add(modifyKeyframeColor.getText());
    return tmp;
  }

  @Override
  public void showErrorMsg(String string) {
    JOptionPane.showMessageDialog(new JFrame(), string, "Dialog",
        JOptionPane.WARNING_MESSAGE);
  }
}
