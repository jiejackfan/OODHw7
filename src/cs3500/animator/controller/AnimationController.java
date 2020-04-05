package cs3500.animator.controller;

import cs3500.animator.model.IModel;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.SwingView;
import cs3500.animator.view.TextView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;
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
      if (m.getCurrentTick() < m.getMaxTick()) {
        currentTick = currentTick + 1;
        m.setTick(currentTick);
      }
      else if (m.getCurrentTick() == m.getMaxTick()) {
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
    v.addActionListener(this);
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
    }
  }



}
