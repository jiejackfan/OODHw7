package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.SwingView;
import cs3500.animator.view.TextView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Timer;


/**
 * Controller implementation that will be the communication between view and model. This controller
 * will take control of the timing (ticks per second) of our animation. It will tell the model the
 * current tick and tell view to update when a new tick happens.
 */
public class AnimationController implements IController, ActionListener {
  private IView v;
  protected IModel m;

  protected Timer timer;
  protected int currentTick = 0;

  public static int DELAY;

  /**
   * This action listener will be used by the timer initialized in playAnimation(). The
   * taskPerformer will perform an action based on the user's given speed. It will perform the
   * following action: 1. update the current tick in the model. 2. tell the view to repaint the
   * panel based on the model with new tick.
   */
  ActionListener taskPerformer = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (m.getCurrentTick() < m.returnMaxTick()) {
        currentTick = currentTick + 1;
        m.setTick(currentTick);
      }
      else if (m.getCurrentTick() == m.returnMaxTick()) {
        //if (v instanceof EditView) {
          if (((EditView) v).getCheckState()) {
            currentTick = 0;
            m.setTick(currentTick);
          }
        //}
      }
      v.refresh();
    }
  };

  /**
   * Constructor of the annimation controller. This is where the controller will gain access of the
   * model and the view that will be used for the animation.
   *
   * @param v a view object that will create animation in SVG, text, or swing format.
   * @param m a model object that stored the animations read from the input file.
   */
  public AnimationController(IView v, IModel m) {
    this.v = v;
    this.m = m;
    if (v instanceof EditView) ((EditView)v).addActionListener(this);
  }

  @Override
  public void playAnimation() {
    // check instanceof
    if (v instanceof SwingView || v instanceof EditView) {
      v.render();
      timer = new Timer(DELAY, taskPerformer);
      timer.start();
    } else if (v instanceof TextView || v instanceof SVGView) {
      v.setDelay(DELAY);
      v.render();
    }
  }

  @Override
  public void setDelay(double tickPerSecond) {
    DELAY = (int) (1000 / tickPerSecond); // convert to ms per tick
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    IEditView v = (IEditView) this.v;
    switch (e.getActionCommand()) {
      //read from the input textfield
      case "Play Button":
        timer.start();
        break;
      case "Resume Button":
        if (timer.isRunning()) {
          timer.stop();
          v.changeResumeButtonColor(Color.RED);
        }
        else {
          timer.start();
          v.changeResumeButtonColor(Color.GREEN);
        }
        break;
      case "Restart Button":
        if (currentTick >= m.getMaxTick()) {
          currentTick = 0;
          timer.start();
        }
        else {
          currentTick = 0;
          timer.restart();
        }
        break;
      case "Speed Up Button":
        int newDelay1 = DELAY - (DELAY / 5);
        DELAY = newDelay1;
        timer.setDelay(newDelay1);
        break;
      case "Slow Down Button":
        int newDelay2 = DELAY + (DELAY / 5);
        System.out.println(timer.getDelay());
        DELAY = newDelay2;
        timer.setDelay(newDelay2);
        break;
      case "Repeat Box":
        break;
      case "Load Button":
        File loadLocation = v.getLoadLocation();
        Readable inputFileContent = null;
        try {
          inputFileContent = new StringReader(
              new String(Files.readAllBytes(Paths.get(loadLocation.getAbsolutePath()))));
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        this.m = AnimationReader.parseFile(inputFileContent, new AnimationModel.Builder());
        //reset time and animation here....
        break;
      case "Save SVG Button":
        File saveLocation = v.getSaveLocation();
        System.out.println(saveLocation.getAbsolutePath());
        IView svgView = new SVGView(m, 0, 0, 0, 0);
        svgView.setOutputFileName(saveLocation.getAbsolutePath() + "out.svg");
        svgView.setDelay(DELAY);
        IController newController = new AnimationController(svgView, m);
        newController.playAnimation();
        break;
      case "Save Text Button":

        break;
      case "Modify Keyframe":
        List<String> tmp4 = v.getEditPanelInput();
        int time = 0, x = 0, y = 0, red = 0, green = 0, blue = 0;
        double width = 0.0, height = 0.0;
        try {
         String[] positions = tmp4.get(2).split(" ");
         if (positions.length != 2) {
           throw new IllegalArgumentException("input for position "
               + "must be 2 integer seperated by space.");
         }

         String[] colors = tmp4.get(5).split(" ");
         if (colors.length != 3) {
           throw new IllegalArgumentException("input for colors must be 3 integers sperated "
               + "by spaces.");
         }

         time = Integer.parseInt(tmp4.get(1));
         x = Integer.parseInt(positions[0]);
         y= Integer.parseInt(positions[1]);
         width = Integer.parseInt(tmp4.get(3));
         height = Integer.parseInt(tmp4.get(4));
         red = Integer.parseInt(colors[0]);
         green = Integer.parseInt(colors[1]);
         blue = Integer.parseInt(colors[2]);
        } catch (IllegalArgumentException nfe) {
          v.showErrorMsg(nfe.getMessage());
        }
        try {
          m.modifyKeyframe(tmp4.get(0), time, x, y, width, height, red, green, blue);
        } catch (IllegalArgumentException iae) {
          v.showErrorMsg(iae.getMessage());
        }
        break;
      case "Insert Keyframe":
        List<String> tmp2 = v.getInsertPanelInput();
        try {
          int tmpInt = Integer.parseInt(tmp2.get(1));
          m.insertKeyframe(tmp2.get(0), tmpInt);
        } catch (NumberFormatException nfe) {
          v.showErrorMsg("You must enter an int for time");
        } catch (IllegalArgumentException iae){
          v.showErrorMsg(iae.getMessage());
        }
        break;
      case "Delete Keyframe":
        List<String> tmp3 = v.getInsertPanelInput();
        try {
          int tmpInt = Integer.parseInt(tmp3.get(1));
          m.deleteKeyframe(tmp3.get(0), tmpInt);
        } catch (NumberFormatException nfe) {
          v.showErrorMsg("You must enter an int for index");
        } catch (IllegalArgumentException iae){
          v.showErrorMsg(iae.getMessage());
        }
        break;
      case "Create Shape":
        List<String> tmp = v.getShapePanelInput();
        try {
          m.createShape(tmp.get(0), tmp.get(1));
        } catch (IllegalArgumentException iae){
          v.showErrorMsg(iae.getMessage());
        }
        break;
      case "Delete Shape":
        List<String> tmp1 = v.getShapePanelInput();
        try {
          m.removeShape(tmp1.get(1));
        } catch (IllegalArgumentException iae){
          v.showErrorMsg(iae.getMessage());
        }
        break;
      default:
        v.showErrorMsg("Invalid command");
        break;
    }
  }
}
