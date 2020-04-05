package cs3500.animator.model;

import java.awt.*;


public class Keyframe {

  // time.
  private int time;

  // positon.
  private Position2D position;

  // width height. They are assigned double as per assignment.
  private double width;
  private double height;

  // color. Using the awt color library.
  private Color color;

  /**
   * Public constructor 1 for motion. This is used when model wants to enter a new motion into a
   * shape's list of motions. The following parameters will be given by the input file.
   *
   * @param startTime     starting time.
   * @param startPosition starting position.
   * @param startWidth    starting width.
   * @param startHeight   starting height.
   * @param startColor    starting color.
   * @param endTime       ending time.
   * @param endPosition   ending position.
   * @param endWidth      ending width.
   * @param endHeight     ending height.
   * @param endColor      ending color.
   * @throws IllegalArgumentException if starting and ending times are less than 1.
   * @throws IllegalArgumentException if starting time is greater than ending time.
   * @throws IllegalArgumentException if any width or height are negative.
   */
  public Keyframe(int time, Position2D position, double width, double height,
                  Color color) {
    // Check whether the given times are valid.
    if (time < 0) {
      throw new IllegalArgumentException("Invalid time.");
    }
    // Check whether the given sizes are valid.
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("The size must be positive.");
    }

    this.time = time;
    this.position = position;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  /**
   * Copy constructor of a motion. This will be used when we want to create a copy of this motion in
   * the model.
   *
   * @param motion a motion that we want to copy in the model.
   */
  public Keyframe(Keyframe kf) {

    this.time = kf.time;
    this.position = kf.position;
    this.width = kf.width;
    this.height = kf.height;
    this.color = kf.color;
  }

  @Override
  public String toString() {
    String output;
    output = String.join(" ", Integer.toString(time),
            Double.toString(position.getX()),
            Double.toString(position.getY()),
            Double.toString(width), Double.toString(height),
            Integer.toString(color.getRed()),
            Integer.toString(color.getGreen()),
            Integer.toString(color.getBlue()));
    return output;
  }

  /**
   * Getter function to get start time.
   *
   * @return start time.
   */
  public int getTime() {
    return time;
  }

  /**
   * Getter function to get start position.
   *
   * @return start position.
   */
  public Position2D getPosition() {
    return position;
  }

  /**
   * Getter function to get start width.
   *
   * @return start width.
   */
  public double getWidth() {
    return width;
  }


  /**
   * Getter function to get start height.
   *
   * @return start height.
   */
  public double getHeight() {
    return height;
  }


  /**
   * Getter function to get start color.
   *
   * @return start color.
   */
  public Color getColor() {
    return color;
  }


  /**
   * Function to change start time. Not used currently in our implementation.
   *
   * @param time the user wants to change as the new start time.
   */
  public void setTime(int time) {
    this.time = time;
  }


}
